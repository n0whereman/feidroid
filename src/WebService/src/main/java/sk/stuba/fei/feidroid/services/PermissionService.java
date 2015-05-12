package sk.stuba.fei.feidroid.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Path;

import sk.stuba.fei.feidroid.entities.Permission;
import sk.stuba.fei.feidroid.resources.PermissionResource;

@Path("permission")
public class PermissionService extends BasicService<Permission, PermissionResource> {
	public PermissionService() {
		super(Permission.class);
	}

	public List<Permission> findByNames(List<String> names) {
		EntityManager em = getEntityManager();
		List<Permission> result;

		if (names.size() > 0) {
			result = em.createNamedQuery(getNamedQuery("findByNames"), getEntityClass()).setParameter("nameListParam", names).getResultList();
		} else {
			result = new ArrayList<Permission>();
		}

		em.close();

		return result;
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
