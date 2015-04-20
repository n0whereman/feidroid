package sk.stuba.fei.feidroid.services.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.Application_;

/**
 * Criteria implementation to match applications by application package and name
 * Can be extended to match by more values, but consider creation of new
 * criteria implementation for specific case
 * 
 * @author Pavol Dobrocka
 *
 */
public class ApplicationCriteria implements Criteria<Application> {
	private String appPackage;
	private String name;

	@Override
	public boolean applyTo(Application entity) {
		boolean packageMatch = false;
		boolean nameMatch = false;

		packageMatch = appPackage == null || appPackage.isEmpty() || appPackage.equals(entity.getAppPackage());
		nameMatch = name == null || name.isEmpty() || name.equals(entity.getName());

		return packageMatch && nameMatch;
	}

	@Override
	public Predicate toPredicate(Root<Application> root, CriteriaBuilder cb) {
		if (name != null && !name.isEmpty() && appPackage != null && !appPackage.isEmpty()) {
			return cb.and(cb.equal(root.get(Application_.name), name), cb.equal(root.get(Application_.appPackage), appPackage));
		} else if (name != null && !name.isEmpty()) {
			return cb.equal(root.get(Application_.name), name);
		} else {
			return cb.equal(root.get(Application_.appPackage), appPackage);
		}
	}

	@Override
	public List<Order> getOrderBy(Root<Application> root, CriteriaBuilder cb) {
		List<Order> order = new ArrayList<Order>();
		order.add(cb.desc(root.get(Application_.version)));
		return order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}
}
