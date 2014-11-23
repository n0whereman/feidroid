package sk.stuba.fei.feidroid.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

abstract public class BasicService {
	private static final String PERSISTENCE_UNIT_NAME = "feidroid";

	public Object findById(Long id) {
		EntityManager em = getEntityManager();

		Object result = em
		    .createNamedQuery(getEntityName() + ".findById", getEntityClass())
		    .setParameter("idParam", id).getSingleResult();

		em.close();

		return result;
	}

	public List<?> findAll() {
		EntityManager em = getEntityManager();
		List<?> result = em.createNamedQuery(getEntityName() + ".findAll",
		    getEntityClass()).getResultList();

		em.close();

		return result;
	}

	public List<?> findByIds(List<Integer> ids) {
		EntityManager em = getEntityManager();
		List<?> result = em
		    .createNamedQuery(getEntityName() + ".findByIds", getEntityClass())
		    .setParameter("idListParam", ids).getResultList();

		em.close();

		return result;
	}

	public Object persistEntity(Object obj) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();

		return obj;
	}

	public Object updateEntity(Object obj) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		em.close();

		return obj;
	}

	abstract public Class<?> getEntityClass();

	abstract public String getEntityName();

	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence
		    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		return factory.createEntityManager();
	}

	abstract public Object convertToResource(Object object);

	public List<?> convertListToResource(Collection<?> objList) {
		List<Object> result = new ArrayList<Object>();
		for (Object obj : objList) {
			result.add(convertToResource(obj));
		}

		return result;
	}

	abstract protected Object convertToEntity(Object resource);

	protected JSONArray collectionToJsonArray(Collection<?> list) {
		return new JSONArray(list);
	}

	/* Resource methods */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllResource() {
		List<?> result = findAll();

		return Response.ok(
		    collectionToJsonArray(convertListToResource(result)).toString())
		    .build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object findByIdResource(@PathParam("id") Long id) {
		Object result = findById(id);

		return convertToResource(result);
	}

	public Response errorResponse() {
		return Response.status(400).build();
	}
}
