package sk.stuba.fei.feidroid.analysis.permissiondistribution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;

import sk.stuba.fei.feidroid.analysis.AnalysisHelper;
import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.Permission;
import sk.stuba.fei.feidroid.entities.PermissionUsage;
import sk.stuba.fei.feidroid.services.ApplicationService;
import sk.stuba.fei.feidroid.services.PermissionService;

public class PermissionDistributionAnalyzer {
	private ApplicationService appService;
	private PermissionService permService;

	public PermissionDistributionAnalyzer(ApplicationService appService, PermissionService permService) {
		this.appService = appService;
		this.permService = permService;
	}

	public LinkedHashMap<String, Integer> analyzePermissions(AnalysisConfiguration configuration) {
		EntityManager em = appService.getEntityManager();
		List<Long> ids;
		if (configuration.getCriteria() == null) {
			ids = em.createQuery("SELECT a.id FROM Application a", Long.class).getResultList();
		} else {
			List<Application> apps = appService.findByCriteria(configuration.getCriteria());
			ids = new ArrayList<Long>();
			for (Application app : apps) {
				ids.add(app.getId());
			}
		}

		HashMap<String, Integer> nTuplesMap = new HashMap<String, Integer>();

		for (Long id : ids) {
			Application app = appService.findById(id);
			List<PermissionUsage> permissions = app.getPermissions();

			List<String> nTuples = AnalysisHelper.constructNtuples(permissions, configuration.getTuples());

			for (String ntuple : nTuples) {
				Integer count = nTuplesMap.get(ntuple);
				if (count == null) {
					count = 1;
				} else {
					count++;
				}

				nTuplesMap.put(ntuple, count);
			}
		}

		List<Entry<String, Integer>> orderedMap = new ArrayList<Entry<String, Integer>>();

		for (Entry<String, Integer> entry : nTuplesMap.entrySet()) {
			orderedMap.add(entry);
		}

		orderedMap.sort(new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		if (configuration.getCount() > -1 && configuration.getCount() <= orderedMap.size()) {
			orderedMap = orderedMap.subList(0, configuration.getCount());
		}

		return resolvePermissionNames(orderedMap);
	}

	private LinkedHashMap<String, Integer> resolvePermissionNames(List<Entry<String, Integer>> permissionDistribution) {
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		List<Permission> permissions = permService.findAll();
		Map<Long, String> permissionMap = createPermissionMap(permissions);

		for (Entry<String, Integer> entry : permissionDistribution) {
			List<Long> permIds = AnalysisHelper.parsePermissionKey(entry.getKey());
			String serializedPermissions = "";
			boolean first = true;
			for (Long id : permIds) {
				serializedPermissions = first ? permissionMap.get(id) : String.join(", ", permissionMap.get(id), serializedPermissions);
				first = false;
			}

			map.put(serializedPermissions, entry.getValue());
		}

		return map;
	}

	private Map<Long, String> createPermissionMap(List<Permission> permissions) {
		Map<Long, String> map = new HashMap<Long, String>();

		for (Permission perm : permissions) {
			map.put(perm.getId(), perm.getTitle());
		}

		return map;
	}
}
