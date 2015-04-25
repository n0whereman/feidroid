package sk.stuba.fei.feidroid.appconfig.service;

import javax.persistence.EntityManager;

import sk.stuba.fei.feidroid.appconfig.entities.AppConfig;
import sk.stuba.fei.feidroid.appconfig.resource.AppConfigResource;
import sk.stuba.fei.feidroid.services.BasicService;

public class AppConfigService extends BasicService<AppConfig, AppConfigResource> {

	public AppConfigService() {
		super(AppConfig.class);
	}

	public AppConfig getAppConfig(String key) {
		EntityManager em = getEntityManager();
		AppConfig result = em.createNamedQuery(getNamedQuery("findByKey"), getEntityClass()).setParameter("keyParam", key).getSingleResult();

		return result;
	}

	@Override
	public AppConfigResource convertEntityToResource(AppConfig object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AppConfig convertResourceToEntity(AppConfigResource resource) {
		// TODO Auto-generated method stub
		return null;
	}

}
