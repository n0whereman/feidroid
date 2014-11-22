package sk.stuba.fei.feidroid.resources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PermissionResource {
	private Long id;
	private String description;
	private String title;

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
		return "PermissionResource [id=" + id + ", title=" + title
		    + ", description=" + description + "]";
	}
}
