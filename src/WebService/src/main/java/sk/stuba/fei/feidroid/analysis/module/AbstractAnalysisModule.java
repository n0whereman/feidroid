package sk.stuba.fei.feidroid.analysis.module;

import sk.stuba.fei.feidroid.analysis.AnalysisResult;
import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.entities.Application;

abstract public class AbstractAnalysisModule<T extends AnalysisResult> implements AnalysisModule<T> {
	private ApplicationAnalyzer<T> analyzer;

	public AbstractAnalysisModule(ApplicationAnalyzer<T> analyzer) {
		super();
		this.analyzer = analyzer;
	}

	@Override
	public void setAnalyzer(ApplicationAnalyzer<T> analyzer) {
		this.analyzer = analyzer;
	}

	@Override
	public ApplicationAnalyzer<T> getAnalyzer() {
		return analyzer;
	}

	@Override
	public T analyze(Application application) {
		return analyzer.analyze(application);
	}
}
