package sk.stuba.fei.feidroid.services;

import java.util.List;

import javax.ws.rs.Path;

import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.Permission;
import sk.stuba.fei.feidroid.entities.PermissionUsage;
import sk.stuba.fei.feidroid.resources.PermissionUsageResource;

@Path("permissionUsage")
public class PermissionUsageService extends BasicService<PermissionUsage, PermissionUsageResource> {
	public PermissionUsageService() {
		super(PermissionUsage.class);
	}

	public PermissionUsage createPermissionUsage(Application app, Permission permission, Boolean isUsed) {
		PermissionUsage usage = new PermissionUsage();
		usage.setApplication(app);
		usage.setPermission(permission);
		usage.setIsUsed(isUsed);

		persistEntity(usage);
		app.addPermission(usage);
		return usage;
	}

	public List<PermissionUsage> setPermissionUsages(Application app, List<Permission> permissions) {
		List<PermissionUsage> usages = app.getPermissions();
		removeUsages(usages);
		usages.clear();

		for (Permission p : permissions) {
			usages.add(createPermissionUsage(app, p, true));
		}

		return usages;
	}

	public List<PermissionUsage> persistPermissionUsages(Application app, List<PermissionUsage> permissions) {
		List<PermissionUsage> usages = app.getPermissions();
		removeUsages(usages);
		usages.clear();

		for (PermissionUsage usage : permissions) {
			usages.add(persistEntity(usage));
		}

		return usages;
	}

	@Override
	public PermissionUsageResource convertEntityToResource(PermissionUsage entity) {
		Permission permission = entity.getPermission();
		PermissionUsageResource res = new PermissionUsageResource();
		res.setId(permission.getId());
		res.setDescription(permission.getDescription());
		res.setTitle(permission.getTitle());
		res.setIsUsed(entity.getIsUsed());

		return res;
	}

	@Override
	protected PermissionUsage convertResourceToEntity(PermissionUsageResource resource) {
		PermissionUsage usage = new PermissionUsage();
		usage.getPermission().setId(resource.getId());
		usage.getPermission().setTitle(resource.getTitle());
		usage.getPermission().setDescription(resource.getDescription());
		usage.setIsUsed(resource.getIsUsed());

		return usage;
	}

	private void removeUsages(List<PermissionUsage> usages) {
		for (PermissionUsage usage : usages) {
			removeEntity(usage);
		}
	}
}
