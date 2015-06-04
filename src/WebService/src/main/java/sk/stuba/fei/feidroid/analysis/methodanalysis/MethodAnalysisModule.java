package sk.stuba.fei.feidroid.analysis.methodanalysis;

import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.module.AbstractAnalysisModule;

public class MethodAnalysisModule extends AbstractAnalysisModule<MethodAnalysisResult> {

	public MethodAnalysisModule(ApplicationAnalyzer<MethodAnalysisResult> analyzer) {
		super(analyzer);
	}

	@Override
	public float normalizeResult(MethodAnalysisResult result) {
		if (Boolean.TRUE.equals(result.getUnsafeMethods())) {
			return 1f;
		}

		return 0f;
	}

}
