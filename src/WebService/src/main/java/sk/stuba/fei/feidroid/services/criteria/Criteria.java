package sk.stuba.fei.feidroid.services.criteria;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Criteria<T> {
	public boolean applyTo(T entity);

	public Predicate toPredicate(Root<T> root, CriteriaBuilder cb);

	public List<Order> getOrderBy(Root<T> root, CriteriaBuilder cb);
}
