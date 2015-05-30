package sk.stuba.fei.feidroid.analysis.methodanalysis;

import java.util.List;

import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.analysisresult.AnalysisResultResource;
import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.PermissionUsageRelation;
import sk.stuba.fei.feidroid.services.ApplicationService;

public class MethodAnalyzer implements ApplicationAnalyzer<MethodAnalysisResult> {
	private ApplicationService applicationService = new ApplicationService();

	@Override
	public MethodAnalysisResult analyze(Application application) {
		List<Long> list = applicationService.getEntityManager().createNamedQuery(PermissionUsageRelation.GET_RELATED_TO_ORIGINATOR_QUERY, Long.class)
		    .setParameter("originatorParam", application.getId()).getResultList();

		Long relatedTo = null;
		if (list.size() != 0) {
			relatedTo = list.get(0);
		}

		Application relatedApplication = applicationService.findById(relatedTo);
		MethodAnalysisResult result = new MethodAnalysisResult();
		String description = "";

		if (relatedApplication != null) {
			result.setUnsafeMethods(Boolean.TRUE.equals(relatedApplication.getUnsafeMethods()));
			description = "Application uses unsafe methods";
		} else {
			result.setUnsafeMethods(Boolean.FALSE);
		}

		result.setDescription(description);

		return result;
	}

	@Override
	public AnalysisResultResource convertResultToResource(MethodAnalysisResult result) {
		return null;
	}

}
