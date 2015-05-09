package sk.stuba.fei.feidroid.analysis.permissiondistribution;

import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.services.criteria.Criteria;

/**
 * AnalysisConfiguration class is used to set parameters of Permission analysis.
 * It is possible to set parameter tuples, which the number of how many app
 * permissions are taken into combination If tuples == 3, then we are picking
 * all triples from permissions Parameter count says how many results to return,
 * if count == -1, then the whole result list is returned
 * 
 * @author Pavol Dobrocka
 *
 */
public class AnalysisConfiguration {
	private int tuples;
	private int count;
	private Criteria<Application> criteria;

	public AnalysisConfiguration(int nTuples, int count) {
		super();
		this.tuples = nTuples;
		this.count = count;
	}

	public int getTuples() {
		return tuples;
	}

	public void setTuples(int tuples) {
		this.tuples = tuples;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Criteria<Application> getCriteria() {
	  return criteria;
  }

	public void setCriteria(Criteria<Application> criteria) {
	  this.criteria = criteria;
  }
}
