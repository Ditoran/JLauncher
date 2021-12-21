package im.jahnke.jlauncher;

import java.util.Arrays;
import java.util.stream.Stream;

public class VersionComparer {

	/**
	 * Compares two version numbers
	 * 
	 * @param version1
	 * @param version2
	 * @return The value 0 if version1 == version2; a value < 0 if version1 <
	 *         version2; and a value > 0 if x > y
	 */
	public static int compare(String version1, String version2) {
		if (version1 == null || version2 == null || version1.trim().equals("") || version2.trim().equals("")) {
			throw new IllegalArgumentException("Parameters can not be null or empty");
		}

		int[] version1Split = Stream.of(version1.split("[\\.]")).mapToInt(Integer::parseInt).toArray();
		int[] version2Split = Stream.of(version2.split("[\\.]")).mapToInt(Integer::parseInt).toArray();

		int maxNumberOfVersionNumberElements = Math.max(version1Split.length, version2Split.length);

		int[] version1SplitExtended = Arrays.copyOf(version1Split, maxNumberOfVersionNumberElements);
		int[] version2SplitExtended = Arrays.copyOf(version2Split, maxNumberOfVersionNumberElements);

		return Arrays.compare(version1SplitExtended, version2SplitExtended);
	}

}
