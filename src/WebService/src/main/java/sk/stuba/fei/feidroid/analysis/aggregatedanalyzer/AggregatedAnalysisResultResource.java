package sk.stuba.fei.feidroid.analysis.aggregatedanalyzer;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import sk.stuba.fei.feidroid.analysis.analysisresult.AnalysisResultResource;

@XmlRootElement(name = "AggregatedAnalysisResult")
public class AggregatedAnalysisResultResource extends AnalysisResultResource {
	private float score;
	private List<String> descriptions;

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

}
