package sk.stuba.fei.feidroid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a"),
    @NamedQuery(name = "Application.findById", query = "SELECT a FROM Application a WHERE a.id = :idParam"),
    @NamedQuery(name = "Application.findByIds", query = "SELECT a FROM Application a WHERE a.id IN :idListParam"),
    @NamedQuery(name = "Application.findMatch", query = "SELECT a FROM Application a WHERE a.name = :nameParam AND a.version = :versionParam") })
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private String version;

	@ManyToMany
	@JoinTable(name = "APPLICATION_APP_CATEGORY", joinColumns = { @JoinColumn(name = "APP_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "APP_CAT_ID", referencedColumnName = "ID") })
	private List<ApplicationCategory> categories;

	@ManyToMany
	@JoinTable(name = "APPLICATION_PERMISSIONS", joinColumns = { @JoinColumn(name = "APP_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID") })
	private List<Permission> permissions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<ApplicationCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ApplicationCategory> categories) {
		this.categories = categories;
	}

	public void addCategory(ApplicationCategory category) {
		categories.add(category);
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public void addPermission(Permission permission) {
		permissions.add(permission);
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", name=" + name + ", description="
		    + description + ", version=" + version + "]";
	}
}
