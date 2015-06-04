package sk.stuba.fei.feidroid.analysis.methodanalysis;

import sk.stuba.fei.feidroid.analysis.analysisresult.AbstractAnalysisResult;

public class MethodAnalysisResult extends AbstractAnalysisResult {
	Boolean unsafeMethods;

	public Boolean getUnsafeMethods() {
		return unsafeMethods;
	}

	public void setUnsafeMethods(Boolean unsafeMethods) {
		this.unsafeMethods = unsafeMethods;
	}
}
