package sk.stuba.fei.feidroid.analysis.permissionusageanalysis;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.analysisresult.AnalysisResultResource;
import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.Permission;
import sk.stuba.fei.feidroid.entities.PermissionUsage;
import sk.stuba.fei.feidroid.entities.PermissionUsageRelation;
import sk.stuba.fei.feidroid.services.ApplicationService;

public class PermissionUsageAnalyzer implements ApplicationAnalyzer<PermissionUsageAnalysisResult> {
	private ApplicationService applicationService = new ApplicationService();

	@Override
	public PermissionUsageAnalysisResult analyze(Application application) {
		List<Long> list = applicationService.getEntityManager().createNamedQuery(PermissionUsageRelation.GET_RELATED_TO_ORIGINATOR_QUERY, Long.class)
		    .setParameter("originatorParam", application.getId()).getResultList();

		Long relatedTo = null;
		if (list.size() != 0) {
			relatedTo = list.get(0);
		}

		Application relatedApplication = applicationService.findById(relatedTo);
		List<PermissionUsage> permUsages = relatedApplication.getPermissions();
		List<Permission> unusedPermissions = new ArrayList<Permission>();
		String description = "";

		for (PermissionUsage usage : permUsages) {
			if (!usage.getIsUsed()) {
				unusedPermissions.add(usage.getPermission());

				if (!"".equals(description)) {
					description.concat(", ");
				}

				description.concat(usage.getPermission().getTitle());
			}
		}

		PermissionUsageAnalysisResult result = new PermissionUsageAnalysisResult();
		result.setPermissionCount(permUsages.size());
		result.setUnusedPermissions(unusedPermissions);
		result.setDescription(description);

		return result;
	}

	@Override
	public AnalysisResultResource convertResultToResource(PermissionUsageAnalysisResult result) {
		return null;
	}

}
