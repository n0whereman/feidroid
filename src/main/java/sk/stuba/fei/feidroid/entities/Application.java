package sk.stuba.fei.feidroid.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private String version;

	@ManyToMany
	@JoinTable(name = "APPLICATION_APP_CATEGORY", joinColumns = { @JoinColumn(name = "APP_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "APP_CAT_ID", referencedColumnName = "ID") })
	private Collection<ApplicationCategory> categories;

	public Long getId() {
		return id;
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

	public Collection<ApplicationCategory> getCategories() {
		return categories;
	}

	public void setCategories(Collection<ApplicationCategory> categories) {
		this.categories = categories;
	}

	public void addCategory(ApplicationCategory category) {
		categories.add(category);
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", name=" + name + ", description="
		    + description + ", version=" + version + "]";
	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonObject.put("id", getId());
		jsonObject.put("name", getName());
		jsonObject.put("description", getDescription());
		jsonObject.put("version", getVersion());

		for (ApplicationCategory appCategory : categories) {
			jsonArray.put(appCategory.toJSON());
		}
		jsonObject.put("categories", jsonArray);

		return jsonObject;
	}
}
