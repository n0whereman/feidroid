package sk.stuba.fei.feidroid.analysis.permissionanalysis;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.feidroid.analysis.AnalysisHelper;
import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.analysisresult.AnalysisResultResource;
import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.PermissionAnalysis;
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

		analysisResult.setScore(score);
		analysisResult.setDescription("Permission analysis result description");

		return analysisResult;
	}

	@Override
	public AnalysisResultResource convertResultToResource(PermissionAnalysisResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Long> getGroupIds() {
		return groupIds;
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
