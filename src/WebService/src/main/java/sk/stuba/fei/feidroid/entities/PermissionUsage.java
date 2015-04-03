package sk.stuba.fei.feidroid.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "APPLICATION_PERMISSIONS")
@NamedQueries({ @NamedQuery(name = "PermissionUsage.findAll", query = "SELECT a FROM PermissionUsage a"),
    @NamedQuery(name = "PermissionUsage.findById", query = "SELECT a FROM PermissionUsage a WHERE a.id = :idParam"),
    @NamedQuery(name = "PermissionUsage.findByIds", query = "SELECT a FROM PermissionUsage a WHERE a.id IN :idListParam") })
public class PermissionUsage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "PERMISSION_ID")
	private Permission permission;

	@ManyToOne
	@JoinColumn(name = "APP_ID")
	private Application application;

	@Column(name = "IS_USED")
	private Boolean isUsed;

	public PermissionUsage() {
		this.permission = new Permission();
		this.application = new Application();
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "PermissionUsage [id=" + id + ", application=" + application.getId().toString() + ", permission=" + permission.getId().toString()
		    + ", isUsed=" + isUsed.toString() + "]";
	}
}
