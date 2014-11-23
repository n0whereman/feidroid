package sk.stuba.fei.feidroid.services;

import javax.ws.rs.Path;

import sk.stuba.fei.feidroid.entities.Permission;
import sk.stuba.fei.feidroid.resources.PermissionResource;

@Path("permission")
public class PermissionService extends BasicService {
	private static final Class<?> ENTITY_CLASS = Permission.class;
	private static final String ENTITY_NAME = "Permission";

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
		Permission per = (Permission) object;
		PermissionResource res = new PermissionResource();
		res.setId(per.getId());
		res.setDescription(per.getDescription());
		res.setTitle(per.getTitle());

		return res;
	}

	@Override
	protected Object convertToEntity(Object resource) {
		// TODO Auto-generated method stub
		return null;
	}

}
