package sk.stuba.fei.feidroid.resources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "application")
public class ApplicationResource {
	private Long id;
	private String name;
	private String description;
	private String version;

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

	@Override
	public String toString() {
		return "ApplicationResource [id=" + id + ", name=" + name
		    + ", description=" + description + ", version=" + version + "]";
	}
}
