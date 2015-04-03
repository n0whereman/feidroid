package sk.stuba.fei.feidroid.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import sk.stuba.fei.feidroid.services.criteria.Criteria;

abstract public class BasicService<Entity, Resource> {
	private static final String PERSISTENCE_UNIT_NAME = "feidroid";
	private final Class<Entity> entityClass;

	public BasicService(Class<Entity> entity) {
		entityClass = entity;
	}

	public Entity findById(Long id) {
		EntityManager em = getEntityManager();
		Entity result = null;

		List<Entity> results = em.createNamedQuery(getNamedQuery("findById"), getEntityClass()).setParameter("idParam", id).getResultList();

		em.close();

		if (!results.isEmpty()) {
			result = results.get(0);
		}

		return result;
	}

	public List<Entity> findAll() {
		EntityManager em = getEntityManager();
		List<Entity> result = em.createNamedQuery(getNamedQuery("findAll"), getEntityClass()).getResultList();

		em.close();

		return result;
	}

	public List<Entity> findByIds(List<Integer> ids) {
		EntityManager em = getEntityManager();
		List<Entity> result;
		if (ids.size() > 0) {
			result = em.createNamedQuery(getNamedQuery("findByIds"), getEntityClass()).setParameter("idListParam", ids).getResultList();
		} else {
			result = new ArrayList<Entity>();
		}

		em.close();

		return result;
	}

	public List<Entity> findByCriteria(Criteria<Entity> criteria) {
		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Entity> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
		Root<Entity> root = criteriaQuery.from(getEntityClass());
		Predicate predicate = criteria.toPredicate(root, criteriaBuilder);
		criteriaQuery.where(predicate).orderBy(criteria.getOrderBy(root, criteriaBuilder));

		return em.createQuery(criteriaQuery).getResultList();
	}

	public Entity persistEntity(Entity obj) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();

		return obj;
	}

	public Entity updateEntity(Entity obj) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		em.close();

		return obj;
	}

	public void removeEntity(Entity obj) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Entity entity = em.merge(obj);
		em.remove(entity);
		em.getTransaction().commit();
		em.close();
	}

	public Class<Entity> getEntityClass() {
		return entityClass;
	};

	public String getEntityName() {
		return entityClass.getSimpleName();
	}

	protected String getNamedQuery(String queryName) {
		return getEntityName() + "." + queryName;
	}

	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		return factory.createEntityManager();
	}

	abstract public Resource convertEntityToResource(Entity object);

	public List<Resource> convertListToResource(Collection<Entity> objList) {
		List<Resource> result = new ArrayList<Resource>();
		for (Entity obj : objList) {
			result.add(convertEntityToResource(obj));
		}

		return result;
	}

	abstract protected Entity convertResourceToEntity(Resource resource);

	protected JSONArray collectionToJsonArray(Collection<?> list) {
		return new JSONArray(list);
	}

	/* Resource methods */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllResource() {
		List<Entity> result = findAll();

		return Response.ok(convertListToResource(result)).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Resource findByIdResource(@PathParam("id") Long id) {
		Entity result = findById(id);

		return convertEntityToResource(result);
	}

	public Response errorResponse() {
		return Response.status(400).build();
	}
}
