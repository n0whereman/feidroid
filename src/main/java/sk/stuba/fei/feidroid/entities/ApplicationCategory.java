package sk.stuba.fei.feidroid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "APP_CATEGORY")
@NamedQueries({
    @NamedQuery(name = "ApplicationCategory.findAll", query = "SELECT a FROM ApplicationCategory a"),
    @NamedQuery(name = "ApplicationCategory.findById", query = "SELECT a FROM ApplicationCategory a WHERE a.id = :idParam"),
    @NamedQuery(name = "ApplicationCategory.findByIds", query = "SELECT a FROM ApplicationCategory a WHERE a.id IN :idListParam") })
public class ApplicationCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;

	@ManyToMany(mappedBy = "categories")
	private List<Application> applications;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ApplicationCategory [id=" + id + ", title=" + title
		    + ", description=" + description + "]";
	}
}
