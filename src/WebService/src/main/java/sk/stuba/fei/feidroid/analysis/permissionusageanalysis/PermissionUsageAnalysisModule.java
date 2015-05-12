package sk.stuba.fei.feidroid.analysis.permissionusageanalysis;

import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.module.AbstractAnalysisModule;

public class PermissionUsageAnalysisModule extends AbstractAnalysisModule<PermissionUsageAnalysisResult> {

	public PermissionUsageAnalysisModule(ApplicationAnalyzer<PermissionUsageAnalysisResult> analyzer) {
		super(analyzer);
	}

	@Override
	public float normalizeResult(PermissionUsageAnalysisResult result) {
		if (result.getUnusedPermissions() == null || result.getPermissionCount() == 0) {
			return 0;
		}

		return result.getUnusedPermissions().size() / result.getPermissionCount();
	}

}
