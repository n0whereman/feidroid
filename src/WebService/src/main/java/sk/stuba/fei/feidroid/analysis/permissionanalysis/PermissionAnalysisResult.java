package sk.stuba.fei.feidroid.analysis.permissionanalysis;

import sk.stuba.fei.feidroid.analysis.AnalysisResult;

public class PermissionAnalysisResult implements AnalysisResult {
	private Float score;

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
}
