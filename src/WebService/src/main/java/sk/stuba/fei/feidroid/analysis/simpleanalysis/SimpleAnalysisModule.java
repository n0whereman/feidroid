package sk.stuba.fei.feidroid.analysis.simpleanalysis;

import sk.stuba.fei.feidroid.analysis.module.AbstractAnalysisModule;

public class SimpleAnalysisModule extends AbstractAnalysisModule<SimpleAnalysisResult> {

	public SimpleAnalysisModule(SimpleApplicationAnalyzer analyzer) {
		super(analyzer);
	}

	@Override
	public float normalizeResult(SimpleAnalysisResult result) {
		return 0.5f;
	}

}
