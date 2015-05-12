package sk.stuba.fei.feidroid.analysis.permissionusageanalysis;

import java.util.List;

import sk.stuba.fei.feidroid.analysis.analysisresult.AbstractAnalysisResult;
import sk.stuba.fei.feidroid.entities.Permission;

public class PermissionUsageAnalysisResult extends AbstractAnalysisResult {
	private int permissionCount;
	private List<Permission> unusedPermissions;

	public int getPermissionCount() {
		return permissionCount;
	}

	public void setPermissionCount(int permissionCount) {
		this.permissionCount = permissionCount;
	}

	public List<Permission> getUnusedPermissions() {
		return unusedPermissions;
	}

	public void setUnusedPermissions(List<Permission> unusedPermissions) {
		this.unusedPermissions = unusedPermissions;
	}
}
