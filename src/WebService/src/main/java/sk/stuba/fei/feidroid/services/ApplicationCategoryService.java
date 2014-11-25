package sk.stuba.fei.feidroid.services;

import javax.ws.rs.Path;

import sk.stuba.fei.feidroid.entities.ApplicationCategory;
import sk.stuba.fei.feidroid.resources.ApplicationCategoryResource;

@Path("/category")
public class ApplicationCategoryService extends
    BasicService<ApplicationCategory, ApplicationCategoryResource> {
	public ApplicationCategoryService() {
		super(ApplicationCategory.class);
	}

	@Override
	public ApplicationCategoryResource convertEntityToResource(
	    ApplicationCategory entity) {
		ApplicationCategoryResource res = new ApplicationCategoryResource();
		res.setId(entity.getId());
		res.setDescription(entity.getDescription());
		res.setTitle(entity.getTitle());

		return res;
	}

	@Override
	protected ApplicationCategory convertResourceToEntity(
	    ApplicationCategoryResource resource) {
		return null;
	}
}
