package sk.stuba.fei.feidroid.analysis;

import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.resources.AnalysisResultResource;

public interface ApplicationAnalyzer<T extends AnalysisResult> {
	T analyze(Application application);

	AnalysisResultResource convertResultToResource(T result);
}
