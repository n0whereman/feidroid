package sk.stuba.fei.feidroid.analysis.permissionanalysis;

import sk.stuba.fei.feidroid.analysis.module.AbstractAnalysisModule;

public class PermissionAnalysisModule extends AbstractAnalysisModule<PermissionAnalysisResult> {

	public PermissionAnalysisModule(PermissionAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public float normalizeResult(PermissionAnalysisResult result) {
		return (float) (result.getScore() / ((PermissionAnalyzer) getAnalyzer()).countMaxScore());
	}
}
