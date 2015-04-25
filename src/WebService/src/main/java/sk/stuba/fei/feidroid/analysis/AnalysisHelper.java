package sk.stuba.fei.feidroid.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.persistence.indirection.IndirectList;

import sk.stuba.fei.feidroid.entities.PermissionUsage;

public class AnalysisHelper {
	public static List<String> constructNtuples(List<PermissionUsage> permissions, int n) {
		int size = permissions.size();
		int numberOfTuples = nChooseK(size, n);
		List<String> nTuples = new ArrayList<String>(numberOfTuples);

		// Ugly workaround for not sorting list
		// http://stackoverflow.com/questions/26816650/java8-collections-sort-sometimes-does-not-sort-jpa-returned-lists
		if (permissions instanceof IndirectList) {
			IndirectList iList = (IndirectList) permissions;
			Object sortTargetObject = iList.getDelegateObject();
			if (sortTargetObject instanceof List<?>) {
				List<PermissionUsage> sortTarget = (List<PermissionUsage>) sortTargetObject;
				Collections.sort(sortTarget, new PermissionComparator());
			}
		} else {
			Collections.sort(permissions, new PermissionComparator());
		}

		if (size != 0 && numberOfTuples != 0) {
			for (int i = 0; i < numberOfTuples; i++) {
				nTuples.add(i, "");
			}

			constructList(nTuples, permissions, n, 0, n, 0);
		}

		return nTuples;
	}

	public static List<Long> parsePermissionKey(String key) {
		return parsePermissionKey(key, "-");
	}

	public static List<Long> parsePermissionKey(String key, String delimiter) {
		String idStrings[] = key.split(delimiter);
		List<Long> ids = new ArrayList<Long>();

		for (String id : idStrings) {
			ids.add(Long.valueOf(id));
		}

		return ids;
	}

	private static void constructList(List<String> destination, List<PermissionUsage> permissions, int fullN, int index, int n, int p) {

		String value = "";
		int size = permissions.size();
		int offset = 0;
		int bound = size >= n ? size - n : size;

		for (int i = p; i <= bound; i++) {
			int numberOfTuples = size > n ? nChooseK(size - (i + 1), n - 1) : 1;

			for (int j = 0; j < numberOfTuples; j++) {
				value = destination.get(j + index + offset);
				value += value.isEmpty() || "".equals(value) ? String.valueOf(permissions.get(i).getPermission().getId()) : "-"
				    + String.valueOf(permissions.get(i).getPermission().getId());

				destination.set(j + index + offset, value);
			}

			if (n > 1 && size > n) {
				constructList(destination, permissions, fullN, index + offset, n - 1, i + 1);
			}
			offset += numberOfTuples;

		}
	};

	private static int nChooseK(int n, int k) {
		int part1 = 1;
		int part2 = 1;

		for (int i = 0; i < k; i++) {
			part1 *= n - i;
			part2 *= k - i;
		}

		return part1 / part2;
	}

	private static class PermissionComparator implements Comparator<PermissionUsage> {

		@Override
		public int compare(PermissionUsage o1, PermissionUsage o2) {
			return o1.getPermission().getId().compareTo(o2.getPermission().getId());
		}

	}
}
