package sk.stuba.fei.feidroid.analysis.aggregatedanalyzer;

import sk.stuba.fei.feidroid.analysis.AnalysisResult;

public class AggregatedAnalysisResult implements AnalysisResult {
	private float aggregatedScore;

	public AggregatedAnalysisResult(float aggregatedScore) {
		super();
		this.aggregatedScore = aggregatedScore;
	}

	public float getAggregatedScore() {
		return aggregatedScore;
	}

	public void setAggregatedScore(float aggregatedScore) {
		this.aggregatedScore = aggregatedScore;
	}
}
