package sk.stuba.fei.feidroid.resources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PermissionUsageResource extends PermissionResource {
	private Boolean isUsed;

	public Boolean getIsUsed() {
	  return isUsed;
  }

	public void setIsUsed(Boolean isUsed) {
	  this.isUsed = isUsed;
  }
}
