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
@Table(name = "PERMISSION_ANALYSIS")
@NamedQueries({
    @NamedQuery(name = PermissionAnalysis.GET_BY_GROUP_QUERY_NAME, query = "SELECT p FROM PermissionAnalysis p WHERE p.groupId IN :listParam"),
    @NamedQuery(name = PermissionAnalysis.SUM_GROUP_SCORE, query = "SELECT SUM(p.score) FROM PermissionAnalysis p WHERE p.groupId IN :listParam") })
public class PermissionAnalysis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String permissions;
	private Float score;
	@Column(name = "GROUP_ID")
	private Long groupId;

	public static final String GET_BY_GROUP_QUERY_NAME = "PermissionAnalysis.getByGroup";
	public static final String SUM_GROUP_SCORE = "PermissionAnalysis.sumGroupScore";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
}
