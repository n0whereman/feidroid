package sk.stuba.fei.feidroid.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sk.stuba.fei.feidroid.analysis.AnalysisConfiguration;
import sk.stuba.fei.feidroid.analysis.AnalysisHelper;
import sk.stuba.fei.feidroid.analysis.AnalysisResult;
import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.simpleanalyzer.SimpleAnalysisResult;
import sk.stuba.fei.feidroid.analysis.simpleanalyzer.SimpleApplicationAnalyzer;
import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.ApplicationCategory;
import sk.stuba.fei.feidroid.entities.Permission;
import sk.stuba.fei.feidroid.entities.PermissionUsage;
import sk.stuba.fei.feidroid.resources.ApplicationCategoryResource;
import sk.stuba.fei.feidroid.resources.ApplicationResource;
import sk.stuba.fei.feidroid.resources.PermissionUsageResource;
import sk.stuba.fei.feidroid.resources.ReversedApplicationResource;
import sk.stuba.fei.feidroid.services.criteria.ApplicationCriteria;

@Path("/application")
public class ApplicationService extends BasicService<Application, ApplicationResource> {

	private ApplicationCategoryService appCategoryService;
	private PermissionService permissionService;
	private PermissionUsageService permissionUsageService;

	public ApplicationService() {
		super(Application.class);
		appCategoryService = new ApplicationCategoryService();
		permissionService = new PermissionService();
		permissionUsageService = new PermissionUsageService();
	}

	@Override
	public ApplicationResource convertEntityToResource(Application entity) {
		ApplicationResource resource = new ApplicationResource();

		if (entity != null) {
			resource.setName(entity.getName());
			resource.setId(entity.getId());
			resource.setDescription(entity.getDescription());
			resource.setVersion(entity.getVersion());
			resource.setAppPackage(entity.getAppPackage());
			resource.setFingerprint(entity.getFingerprint());
		}

		return resource;
	}

	@Override
	protected Application convertResourceToEntity(ApplicationResource resource) {
		Application app = new Application();
		app.setId(resource.getId());
		app.setName(resource.getName());
		app.setDescription(resource.getDescription());
		app.setVersion(resource.getVersion());
		app.setAppPackage(resource.getAppPackage());
		app.setFingerprint(resource.getFingerprint());

		return app;
	}

	private Application createNewApplication(Application app) {
		Application newApp = null;
		if (!(app.getName() == null || app.getName().isEmpty()) && !(app.getVersion() == null || app.getVersion().isEmpty())) {
			newApp = findMatchingApplication(app);
			if (newApp == null) {
				newApp = persistEntity(app);
			}
		}

		return newApp;
	}

	public Application findMatchingApplication(Application app) {
		EntityManager em = getEntityManager();
		Application result = null;

		List<Application> results = em.createNamedQuery(getNamedQuery("findMatch"), getEntityClass()).setParameter("nameParam", app.getName())
		    .setParameter("versionParam", app.getVersion()).getResultList();

		em.close();

		if (!results.isEmpty()) {
			result = results.get(0);
		}

		return result;
	}

	public List<Application> findByPackageAndName(String name, String appPackage, Integer limit) {
		ApplicationCriteria criteria = new ApplicationCriteria();
		criteria.setName(name);
		criteria.setAppPackage(appPackage);

		List<Application> apps = findByCriteria(criteria);

		if (limit > apps.size()) {
			limit = apps.size();
		} else if (limit < 0) {
			limit = 0;
		}

		return apps.subList(0, limit);
	}

	public AnalysisResult analyzeApplication(ApplicationAnalyzer analyzer, Application app) {
		return analyzer.analyze(app);
	}

	public List<Entry<String, Integer>> analyzePermissions(AnalysisConfiguration configuration) {
		EntityManager em = getEntityManager();
		List<Long> ids = em.createQuery("SELECT a.id FROM Application a", Long.class).getResultList();

		HashMap<String, Integer> nTuplesMap = new HashMap<String, Integer>();

		for (Long id : ids) {
			Application app = findById(id);
			List<PermissionUsage> permissions = app.getPermissions();

			List<String> nTuples = AnalysisHelper.constructNtuples(permissions, configuration.getTuples());

			for (String ntuple : nTuples) {
				Integer count = nTuplesMap.get(ntuple);
				if (count == null) {
					count = 1;
				} else {
					count++;
				}

				nTuplesMap.put(ntuple, count);
			}
		}

		Set<String> keys = nTuplesMap.keySet();

		List<Entry<String, Integer>> orderedMap = new ArrayList<Entry<String, Integer>>();

		for (Entry<String, Integer> entry : nTuplesMap.entrySet()) {
			orderedMap.add(entry);
		}

		orderedMap.sort(new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		if (configuration.getCount() > -1 && configuration.getCount() <= orderedMap.size()) {
			return orderedMap.subList(0, configuration.getCount());
		}

		return orderedMap;
	}

	@GET
	@Path("{id}/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCategoriesResource(@PathParam("id") Long id) {

		Application result = findById(id);
		Collection<ApplicationCategoryResource> col = appCategoryService.convertListToResource(result.getCategories());

		return Response.ok(col).build();
	}

	@POST
	@Path("{id}/categories")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setApplicationCategoriesResource(@PathParam("id") Long id, List<Integer> categoryIds) {
		Application app = (Application) findById(id);
		List<ApplicationCategory> catList = appCategoryService.findByIds(categoryIds);

		app.setCategories(catList);
		updateEntity(app);

		return Response.ok(app.getCategories()).build();
	}

	@GET
	@Path("{id}/permissions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPermissionsResource(@PathParam("id") Long id) {

		Application result = (Application) findById(id);
		Collection<PermissionUsageResource> col = permissionUsageService.convertListToResource(result.getPermissions());

		return Response.ok(col).build();
	}

	@POST
	@Path("{id}/permissions")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setPermissionsResource(@PathParam("id") Long id, List<Integer> permissionIds) {
		Application app = findById(id);
		List<Permission> permList = permissionService.findByIds(permissionIds);
		List<PermissionUsage> usages = permissionUsageService.setPermissionUsages(app, permList);
		Collection<PermissionUsageResource> col = permissionUsageService.convertListToResource(usages);

		return Response.ok(col).build();
	}

	@POST
	@Path("{id}/permissions-usage")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setPermissionUsagesResource(@PathParam("id") Long id, List<PermissionUsageResource> usagesResources) {
		Application app = findById(id);
		PermissionUsage usage;
		List<PermissionUsage> usages = new ArrayList<PermissionUsage>();

		for (PermissionUsageResource usageResource : usagesResources) {
			usage = permissionUsageService.convertResourceToEntity(usageResource);
			usage.setApplication(app);
			usages.add(usage);
		}

		usages = permissionUsageService.persistPermissionUsages(app, usages);
		Collection<PermissionUsageResource> col = permissionUsageService.convertListToResource(usages);

		return Response.ok(col).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewApplicationResource(ApplicationResource resource) {
		Application app = convertResourceToEntity(resource);
		app = createNewApplication(app);

		if (app == null) {
			return errorResponse();
		} else {
			return Response.status(201).entity(convertEntityToResource(app)).build();
		}
	}

	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByPackageAndNameResource(@DefaultValue("") @QueryParam("name") String name,
	    @DefaultValue("") @QueryParam("package") String appPackage, @DefaultValue("1") @QueryParam("limit") Integer limit) {
		List<Application> list = findByPackageAndName(name, appPackage, limit);
		return Response.ok(convertListToResource(list)).build();
	}

	@GET
	@Path("{id}/analyze")
	@Produces(MediaType.APPLICATION_JSON)
	public Response analyzeApplicationResource(@PathParam("id") Long id) {
		Application app = findById(id);
		SimpleApplicationAnalyzer analyzer = new SimpleApplicationAnalyzer();
		SimpleAnalysisResult result = (SimpleAnalysisResult) analyzeApplication(analyzer, app);

		return Response.ok(analyzer.convertResultToResource(result)).build();
	}

	@POST
	@Path("reverse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reverseApplicationResource(ReversedApplicationResource resource) {
		Application app = convertResourceToEntity(resource);
		app.setName(UUID.randomUUID().toString());
		app = persistEntity(app);

		List<Permission> permissions = permissionService.findByNames(resource.getUsedPermissions());
		for (Permission p : permissions) {
			permissionUsageService.createPermissionUsage(app, p, true);
		}

		permissions = permissionService.findByNames(resource.getNotUsedPermissions());
		for (Permission p : permissions) {
			permissionUsageService.createPermissionUsage(app, p, false);
		}

		return Response.status(201).entity(convertEntityToResource(app)).build();
	}
}
