package sk.stuba.fei.feidroid.services;

import javax.ws.rs.Path;

import sk.stuba.fei.feidroid.entities.Permission;
import sk.stuba.fei.feidroid.resources.PermissionResource;

@Path("permission")
public class PermissionService extends
    BasicService<Permission, PermissionResource> {
	public PermissionService() {
		super(Permission.class);
	}

	@Override
	public PermissionResource convertEntityToResource(Permission entity) {
		PermissionResource res = new PermissionResource();
		res.setId(entity.getId());
		res.setDescription(entity.getDescription());
		res.setTitle(entity.getTitle());

		return res;
	}

	@Override
	protected Permission convertResourceToEntity(PermissionResource resource) {
		return null;
	}
}
