package sk.stuba.fei.feidroid.resources;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class ReversedApplicationResource extends ApplicationResource {
	private List<String> permissions;
	private List<String> usedPermissions;
	private List<String> notUsedPermissions;
	private Long relatedTo;

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	@JsonProperty("used")
	public List<String> getUsedPermissions() {
		return usedPermissions;
	}

	@JsonProperty("used")
	public void setUsedPermissions(List<String> usedPermissions) {
		this.usedPermissions = usedPermissions;
	}

	@JsonProperty("noused")
	public List<String> getNotUsedPermissions() {
		return notUsedPermissions;
	}

	@JsonProperty("noused")
	public void setNotUsedPermissions(List<String> notUsedPermissions) {
		this.notUsedPermissions = notUsedPermissions;
	}

	@JsonProperty("SHA1")
	public String getFingerprint() {
		return super.getFingerprint();
	}

	@JsonProperty("SHA1")
	public void setFingerprint(String fingerprint) {
		super.setFingerprint(fingerprint);
	}

	public Long getRelatedTo() {
		return relatedTo;
	}

	public void setRelatedTo(Long relatedTo) {
		this.relatedTo = relatedTo;
	}

	@Override
	public String toString() {
		return "ReversedApplicationResource [id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + ", version=" + getVersion()
		    + ", package=" + getAppPackage() + "SHA1=" + getFingerprint() + "relatedTo=" + getRelatedTo() + "]";
	}
}
