package sk.stuba.fei.feidroid.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
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

	@Override
	protected String getEntityName() {
		return ENTITY_NAME;
	}

	@Override
	public Class<?> getEntityClass() {
		return Application.class;
	}

	@Override
	protected Object convertToResource(Object object) {
		ApplicationResource resource = new ApplicationResource();
		Application app = (Application) object;

		resource.setId(app.getId());
		resource.setDescription(app.getDescription());

		return resource;
	}

	private Collection<ApplicationCategoryResource> convertCategoriesToResource(
	    Collection<ApplicationCategory> collection) {

		List<ApplicationCategoryResource> resource = new ArrayList<ApplicationCategoryResource>();
		for (ApplicationCategory cat : collection) {
			ApplicationCategoryResource res = new ApplicationCategoryResource();
			res.setId(cat.getId());
			res.setDescription(cat.getDescription());
			res.setTitle(cat.getTitle());

			resource.add(res);
		}

		return resource;
	}

	private Collection<PermissionResource> convertPermissionsToResource(
	    Collection<Permission> collection) {

		List<PermissionResource> resource = new ArrayList<PermissionResource>();

		for (Permission per : collection) {
			PermissionResource res = new PermissionResource();
			res.setId(per.getId());
			res.setDescription(per.getDescription());
			res.setTitle(per.getTitle());

			resource.add(res);
		}

		return resource;
	}

	@GET
	@Path("{id}/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCategoriesResource(@PathParam("id") Long id) {

		Application result = (Application) findById(id);
		Collection<ApplicationCategoryResource> col = convertCategoriesToResource(result
		    .getCategories());

		return Response.ok(collectionToJsonArray(col).toString()).build();
	}

	@GET
	@Path("{id}/permissions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPermissionsResource(@PathParam("id") Long id) {

		Application result = (Application) findById(id);
		Collection<PermissionResource> col = convertPermissionsToResource(result
		    .getPermissions());

		return Response.ok(collectionToJsonArray(col).toString()).build();
	}
}
