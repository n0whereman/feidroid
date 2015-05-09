package sk.stuba.fei.feidroid.analysis.aggregatedanalyzer;

import sk.stuba.fei.feidroid.analysis.analysisresult.AbstractAnalysisResult;

public class NormalizedAnalysisResult extends AbstractAnalysisResult {
	private float score;

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
}
