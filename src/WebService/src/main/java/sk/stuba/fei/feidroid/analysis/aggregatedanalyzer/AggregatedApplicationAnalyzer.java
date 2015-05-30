package sk.stuba.fei.feidroid.analysis.aggregatedanalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.analysisresult.AnalysisResult;
import sk.stuba.fei.feidroid.analysis.module.AnalysisModule;
import sk.stuba.fei.feidroid.entities.Application;

public class AggregatedApplicationAnalyzer implements ApplicationAnalyzer<AggregatedAnalysisResult> {
	private List<AnalysisModule<? extends AnalysisResult>> modules;
	private HashMap<AnalysisModule<?>, Float> weights;
	private static final float CORRECTION = 1.3f;

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
		AggregatedAnalysisResult aggregatedResult = new AggregatedAnalysisResult(0);

		for (AnalysisModule<? extends AnalysisResult> module : modules) {

			NormalizedAnalysisResult normalizedResult = getNormalizedModuleResult(module, application);
			aggregatedValue += normalizedResult.getScore() * weights.get(module);
			aggregatedResult.getDescriptions().add(normalizedResult.getDescription());
		}

		aggregatedValue /= getSumOfWeights();
		aggregatedValue = (float) (Math.exp(CORRECTION * aggregatedValue) - 1);
		aggregatedValue = aggregatedValue > 1 ? 1 : aggregatedValue;
		aggregatedResult.setAggregatedScore(aggregatedValue);

		return aggregatedResult;
	}

	@Override
	public AggregatedAnalysisResultResource convertResultToResource(AggregatedAnalysisResult result) {
		AggregatedAnalysisResultResource resource = new AggregatedAnalysisResultResource();
		resource.setDescriptions(result.getDescriptions());
		resource.setScore(result.getAggregatedScore());

		return resource;
	}

	private <T extends AnalysisResult> NormalizedAnalysisResult getNormalizedModuleResult(AnalysisModule<T> module, Application application) {
		NormalizedAnalysisResult normalizedResult = new NormalizedAnalysisResult();
		T result = module.analyze(application);
		normalizedResult.setScore(module.normalizeResult(result));
		normalizedResult.setDescription(result.getDescription());

		return normalizedResult;
	}

	private float getSumOfWeights() {
		float sum = 0;

		for (Float value : weights.values()) {
			sum += value;
		}

		return sum;
	}
}
