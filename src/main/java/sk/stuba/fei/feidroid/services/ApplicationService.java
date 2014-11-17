package sk.stuba.fei.feidroid.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sk.stuba.fei.feidroid.entities.Application;

@Path("/application")
public class ApplicationService {
	private static final String PERSISTENCE_UNIT_NAME = "feidroid";

	@GET
	@Produces("application/json")
	public Response listAllApps() throws JSONException {

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject;

		EntityManagerFactory factory = Persistence
		    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select a from Application a");
		List<Application> appList = q.getResultList();

		for (Application app : appList) {
			jsonObject = app.toJSON();
			jsonArray.put(jsonObject);
		}

		String result = jsonArray.toString();

		em.close();

		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getApplicationWithId(@PathParam("id") Long id) {
		String result = "";
		EntityManagerFactory factory = Persistence
		    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select a from Application a where a.id=:idParam");
		q.setParameter("idParam", id);
		List<Application> appList = q.getResultList();

		if (appList.size() == 1) {
			result = appList.get(0).toJSON().toString();
		}

		return Response.status(200).entity(result).build();
	}
}
