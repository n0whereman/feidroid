package sk.stuba.fei.feidroid.resources;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "application")
public class ApplicationResource {
	private Long id;
	private String name;
	private String description;
	private String version;
	private String appPackage;
	private String fingerprint;

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

	@JsonProperty("package")
	public String getAppPackage() {
		return appPackage;
	}

	@JsonProperty("package")
	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	@Override
	public String toString() {
		return "ApplicationResource [id=" + id + ", name=" + name + ", description=" + description + ", version=" + version + ", package=" + appPackage
		    + ", fingerprint=" + fingerprint + "]";
	}
}
