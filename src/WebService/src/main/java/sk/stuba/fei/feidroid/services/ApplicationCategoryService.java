package sk.stuba.fei.feidroid.services;

import javax.ws.rs.Path;

import sk.stuba.fei.feidroid.entities.ApplicationCategory;
import sk.stuba.fei.feidroid.resources.ApplicationCategoryResource;

@Path("/category")
public class ApplicationCategoryService extends BasicService {
	private static final Class<?> ENTITY_CLASS = ApplicationCategory.class;
	private static final String ENTITY_NAME = "ApplicationCategory";

	@Override
	public Class<?> getEntityClass() {
		return ENTITY_CLASS;
	}

	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	@Override
	public Object convertToResource(Object object) {
		ApplicationCategoryResource res = new ApplicationCategoryResource();
		ApplicationCategory cat = (ApplicationCategory) object;
		res.setId(cat.getId());
		res.setDescription(cat.getDescription());
		res.setTitle(cat.getTitle());

		return res;
	}

	@Override
	protected Object convertToEntity(Object resource) {
		return null;
	}
}
