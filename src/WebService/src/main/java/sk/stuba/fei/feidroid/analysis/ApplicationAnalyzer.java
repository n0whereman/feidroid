package sk.stuba.fei.feidroid.analysis;

import sk.stuba.fei.feidroid.entities.Application;

public interface ApplicationAnalyzer<T extends AnalysisResult> {
	T analyze(Application application);

	AnalysisResultResource convertResultToResource(T result);
}
