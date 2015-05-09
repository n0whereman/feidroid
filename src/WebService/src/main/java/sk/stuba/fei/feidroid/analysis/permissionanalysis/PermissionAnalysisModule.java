package sk.stuba.fei.feidroid.analysis.permissionanalysis;

import sk.stuba.fei.feidroid.analysis.module.AbstractAnalysisModule;

public class PermissionAnalysisModule extends AbstractAnalysisModule<PermissionAnalysisResult> {

	public PermissionAnalysisModule(PermissionAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public float normalizeResult(PermissionAnalysisResult result) {
		// TODO: Count permission group max score and divide the result with it
		// ((PermissionAnalyzer)getAnalyzer())getGroupIds()
		return result.getScore();
	}
}
