package sk.stuba.fei.feidroid.analysis.permissionanalysis;

import sk.stuba.fei.feidroid.analysis.analysisresult.AbstractAnalysisResult;

public class PermissionAnalysisResult extends AbstractAnalysisResult {
	private Float score;

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
}
