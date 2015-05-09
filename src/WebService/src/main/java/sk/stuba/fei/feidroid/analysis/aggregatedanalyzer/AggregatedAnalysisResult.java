package sk.stuba.fei.feidroid.analysis.aggregatedanalyzer;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.feidroid.analysis.analysisresult.AbstractAnalysisResult;

public class AggregatedAnalysisResult extends AbstractAnalysisResult {
	private float aggregatedScore;
	private List<String> descriptions;

	public AggregatedAnalysisResult(float aggregatedScore) {
		super();
		this.aggregatedScore = aggregatedScore;
		descriptions = new ArrayList<String>();
	}

	public float getAggregatedScore() {
		return aggregatedScore;
	}

	public void setAggregatedScore(float aggregatedScore) {
		this.aggregatedScore = aggregatedScore;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
}
