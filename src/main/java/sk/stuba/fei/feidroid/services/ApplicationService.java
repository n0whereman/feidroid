package sk.stuba.fei.feidroid.services;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.ApplicationCategory;
import sk.stuba.fei.feidroid.entities.Permission;
import sk.stuba.fei.feidroid.resources.ApplicationCategoryResource;
import sk.stuba.fei.feidroid.resources.ApplicationResource;
import sk.stuba.fei.feidroid.resources.PermissionResource;

@Path("/application")
public class ApplicationService extends BasicService {
	private static final String ENTITY_NAME = "Application";
	private ApplicationCategoryService appCategoryService;
	private PermissionService permissionService;

	public ApplicationService() {
		super();
		appCategoryService = new ApplicationCategoryService();
		permissionService = new PermissionService();
	}

	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	@Override
	public Class<?> getEntityClass() {
		return Application.class;
	}

	@Override
	public Object convertToResource(Object object) {
		ApplicationResource resource = new ApplicationResource();
		Application app = (Application) object;

		resource.setName(app.getName());
		resource.setId(app.getId());
		resource.setDescription(app.getDescription());
		resource.setVersion(app.getVersion());

		return resource;
	}

	@Override
	protected Object convertToEntity(Object resource) {
		return null;
	}

	private Application convertApplicationResourceToEntity(
	    ApplicationResource resource) {
		Application app = new Application();
		app.setId(resource.getId());
		app.setName(resource.getName());
		app.setDescription(resource.getDescription());
		app.setVersion(resource.getVersion());

		return app;
	}

	@GET
	@Path("{id}/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCategoriesResource(@PathParam("id") Long id) {

		Application result = (Application) findById(id);
		Collection<ApplicationCategoryResource> col = (Collection<ApplicationCategoryResource>) appCategoryService
		    .convertListToResource(result.getCategories());

		return Response.ok(collectionToJsonArray(col).toString()).build();
	}

	@POST
	@Path("{id}/categories")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setApplicationCategoriesResource(@PathParam("id") Long id,
	    List<Integer> categoryIds) {
		Application app = (Application) findById(id);
		List<ApplicationCategory> catList = (List<ApplicationCategory>) appCategoryService
		    .findByIds(categoryIds);

		app.setCategories(catList);
		updateEntity(app);

		return Response.ok(app.getCategories()).build();
	}

	@GET
	@Path("{id}/permissions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPermissionsResource(@PathParam("id") Long id) {

		Application result = (Application) findById(id);
		Collection<PermissionResource> col = (Collection<PermissionResource>) permissionService
		    .convertListToResource(result.getPermissions());

		return Response.ok(collectionToJsonArray(col).toString()).build();
	}

	@POST
	@Path("{id}/permissions")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setPermissionsResource(@PathParam("id") Long id,
	    List<Integer> permissionIds) {
		Application app = (Application) findById(id);
		List<Permission> permList = (List<Permission>) permissionService
		    .findByIds(permissionIds);

		app.setPermissions(permList);
		updateEntity(app);

		return Response.ok(app.getPermissions()).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewApplicationResource(ApplicationResource resource) {
		Application app = convertApplicationResourceToEntity(resource);
		persistEntity(app);

		return Response.status(201).entity(convertToResource(app)).build();
	}
}
