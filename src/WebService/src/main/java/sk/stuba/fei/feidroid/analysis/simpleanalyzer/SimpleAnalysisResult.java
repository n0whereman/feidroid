package sk.stuba.fei.feidroid.analysis.simpleanalyzer;

import java.util.List;

import sk.stuba.fei.feidroid.analysis.analysisresult.AbstractAnalysisResult;

public class SimpleAnalysisResult extends AbstractAnalysisResult {
	private double score;
	private List<String> suspiciousPermissions;

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public List<String> getSuspiciousPermissions() {
		return suspiciousPermissions;
	}

	public void setSuspiciousPermissions(List<String> suspiciousPermissions) {
		this.suspiciousPermissions = suspiciousPermissions;
	}

	@Override
	public String toString() {
		return "SimpleAnalysisResult [score=" + score + "]";
	}

}
