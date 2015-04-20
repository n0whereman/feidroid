package sk.stuba.fei.feidroid.analysis.aggregatedanalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sk.stuba.fei.feidroid.analysis.AnalysisResult;
import sk.stuba.fei.feidroid.analysis.AnalysisResultResource;
import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.module.AnalysisModule;
import sk.stuba.fei.feidroid.entities.Application;

public class AggregatedApplicationAnalyzer implements ApplicationAnalyzer<AggregatedAnalysisResult> {
	private List<AnalysisModule<? extends AnalysisResult>> modules;
	private HashMap<AnalysisModule<?>, Float> weights;

	public AggregatedApplicationAnalyzer() {
		super();
		modules = new ArrayList<AnalysisModule<? extends AnalysisResult>>();
		weights = new HashMap<AnalysisModule<?>, Float>();
	}

	public <T extends AnalysisResult> void addModule(AnalysisModule<T> module, Float weight) {
		modules.add(module);
		weights.put(module, weight);
	}

	@Override
	public AggregatedAnalysisResult analyze(Application application) {
		float aggregatedValue = 0;

		for (AnalysisModule<? extends AnalysisResult> module : modules) {
			float normalized = getNormalizedModuleScore(module, application);
			aggregatedValue += normalized * weights.get(module);
		}

		aggregatedValue /= getSumOfWeights();

		return new AggregatedAnalysisResult(aggregatedValue);
	}

	@Override
	public AnalysisResultResource convertResultToResource(AggregatedAnalysisResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	private <T extends AnalysisResult> float getNormalizedModuleScore(AnalysisModule<T> module, Application application) {
		T result = module.analyze(application);
		return module.normalizeResult(result);
	}

	private float getSumOfWeights() {
		float sum = 0;

		for (Float value : weights.values()) {
			sum += value;
		}

		return sum;
	}
}
