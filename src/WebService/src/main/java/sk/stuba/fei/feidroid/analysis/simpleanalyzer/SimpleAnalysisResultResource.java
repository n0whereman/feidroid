package sk.stuba.fei.feidroid.analysis.simpleanalyzer;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import sk.stuba.fei.feidroid.analysis.analysisresult.AnalysisResultResource;

@XmlRootElement(name = "simpleAnalysisResult")
public class SimpleAnalysisResultResource extends AnalysisResultResource {
	private double score;
	private List<String> suspiciousPermissions;

	public SimpleAnalysisResultResource(SimpleAnalysisResult result) {
		this.score = result.getScore();
		this.suspiciousPermissions = result.getSuspiciousPermissions();
	}

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
		return "SimpleAnalysisResultResource [score=" + score
		    + ", suspiciousPermissions=" + suspiciousPermissions.toString() + "]";
	}
}
