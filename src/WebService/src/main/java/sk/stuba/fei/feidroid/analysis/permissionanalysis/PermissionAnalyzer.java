package sk.stuba.fei.feidroid.analysis.permissionanalysis;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.feidroid.analysis.AnalysisHelper;
import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.analysisresult.AnalysisResultResource;
import sk.stuba.fei.feidroid.analysis.permissionanalysis.entities.PermissionAnalysis;
import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.PermissionUsage;
import sk.stuba.fei.feidroid.services.ApplicationService;

public class PermissionAnalyzer implements ApplicationAnalyzer<PermissionAnalysisResult> {
	private ApplicationService applicationService = new ApplicationService();
	private List<Long> groupIds;

	public PermissionAnalyzer() {
		super();
		groupIds = new ArrayList<Long>();
	}

	@Override
	public PermissionAnalysisResult analyze(Application application) {
		if (groupIds.size() == 0) {
			groupIds.add(Long.valueOf(1));
		}

		List<PermissionAnalysis> permissionAnalysisList = applicationService.getEntityManager()
		    .createNamedQuery(PermissionAnalysis.GET_BY_GROUP_QUERY_NAME, PermissionAnalysis.class).setParameter("listParam", groupIds).getResultList();

		List<Long> permissions = new ArrayList<Long>();
		List<Long> appPermissions = extractApplicationPermissions(application);
		PermissionAnalysisResult analysisResult = new PermissionAnalysisResult();
		Float score = 0f;

		for (PermissionAnalysis permissionAnalysis : permissionAnalysisList) {
			permissions = AnalysisHelper.parsePermissionKey(permissionAnalysis.getPermissions(), ",");
			if (appPermissions.containsAll(permissions)) {
				score += permissionAnalysis.getScore();
			}
		}

		float normalized = (float) (score / countMaxScore());
		String description = "";

		if (normalized >= 0.8f) {
			description = "Very high similarity to a malware";
		} else if (normalized >= 0.6f) {
			description = "High similarity to a malware ";
		} else if (normalized >= 0.4f) {
			description = "Medium similarity to a malware ";
		} else if (normalized >= 0.2f) {
			description = "Small similarity to a malware ";
		} else {
			description = "Negligible similarity to a malware ";
		}

		analysisResult.setScore(score);
		analysisResult.setDescription(description);

		return analysisResult;
	}

	public Double countMaxScore() {
		List<Double> result = applicationService.getEntityManager().createNamedQuery(PermissionAnalysis.SUM_GROUP_SCORE, Double.class)
		    .setParameter("listParam", groupIds).getResultList();

		Double score = result != null ? Double.valueOf(result.get(0)) : Double.valueOf(0);

		return score;
	}

	@Override
	public AnalysisResultResource convertResultToResource(PermissionAnalysisResult result) {
		return null;
	}

	public List<Long> getGroupIds() {
		return groupIds;
	}

	/**
	 * Sets group ids for analysis.
	 * 
	 * @param groups
	 *          CSV of group ids
	 */
	public void setGroups(String groups) {
		String ids[] = groups.split(",");
		List<Long> groupIds = new ArrayList<Long>();

		for (String id : ids) {
			groupIds.add(Long.valueOf(id.trim()));
		}

		this.groupIds = groupIds;
	}

	private List<Long> extractApplicationPermissions(Application application) {
		List<Long> permissions = new ArrayList<Long>();
		List<PermissionUsage> usages = application.getPermissions();

		for (PermissionUsage usage : usages) {
			permissions.add(usage.getPermission().getId());
		}

		return permissions;
	}
}
