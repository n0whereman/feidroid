package sk.stuba.fei.feidroid.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISSION_USAGE_ANALYSIS")
@NamedQueries({ @NamedQuery(name = PermissionUsageRelation.GET_RELATED_TO_ORIGINATOR_QUERY, query = "SELECT p.relatedTo FROM PermissionUsageRelation p WHERE p.originator = :originatorParam") })
public class PermissionUsageRelation {
	public static final String GET_RELATED_TO_ORIGINATOR_QUERY = "PermissionUsageRelation.getRelatedToOriginator";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long originator;

	@Column(name = "RELATED_TO")
	private Long relatedTo;

	public Long getId() {
		return id;
	}

	public Long getOriginator() {
		return originator;
	}

	public void setOriginator(Long originator) {
		this.originator = originator;
	}

	public Long getRelatedTo() {
		return relatedTo;
	}

	public void setRelatedTo(Long relatedTo) {
		this.relatedTo = relatedTo;
	}

}
