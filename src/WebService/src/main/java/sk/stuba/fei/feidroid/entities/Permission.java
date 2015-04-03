package sk.stuba.fei.feidroid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISSION")
@NamedQueries({ @NamedQuery(name = "Permission.findAll", query = "SELECT a FROM Permission a"),
    @NamedQuery(name = "Permission.findById", query = "SELECT a FROM Permission a WHERE a.id = :idParam"),
    @NamedQuery(name = "Permission.findByIds", query = "SELECT a FROM Permission a WHERE a.id IN :idListParam"),
    @NamedQuery(name = "Permission.findByNames", query = "SELECT a FROM Permission a WHERE a.title IN :nameListParam") })
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String title;

	@OneToMany(mappedBy = "permission")
	private List<PermissionUsage> usages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
}
