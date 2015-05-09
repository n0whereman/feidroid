package sk.stuba.fei.feidroid.analysis.module;

import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.analysisresult.AnalysisResult;
import sk.stuba.fei.feidroid.entities.Application;

/**
 * Interface to handle analysis and normalization of the result. The normalized
 * result should return value from the interval <0, 1>
 * 
 * @author Pavol Dobrocka
 *
 * @param <T>
 */
public interface AnalysisModule<T extends AnalysisResult> {
	/**
	 * set analyzer which will perform the analysis of the application
	 * 
	 * @param analyzer
	 */
	public void setAnalyzer(ApplicationAnalyzer<T> analyzer);

	/**
	 * Returns the currently set analyzer
	 * 
	 * @return current analyzer
	 */
	public ApplicationAnalyzer<T> getAnalyzer();

	/**
	 * Performs analysis with the currently set analyzer
	 * 
	 * @param application
	 *          Application to analyze
	 * @return result of the analysis
	 */
	public T analyze(Application application);

	/**
	 * Normalize the provided result to float value. This value should be from
	 * interval <0, 1>
	 * 
	 * @param result
	 * @return float value representing the analysis result
	 */
	public float normalizeResult(T result);

}
