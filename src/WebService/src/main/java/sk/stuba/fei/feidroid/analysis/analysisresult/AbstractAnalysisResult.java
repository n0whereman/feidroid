package sk.stuba.fei.feidroid.analysis.analysisresult;


public class AbstractAnalysisResult implements AnalysisResult {
	private String description;

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

}
