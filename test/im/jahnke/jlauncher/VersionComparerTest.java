package im.jahnke.jlauncher;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class VersionComparerTest {

	@Test
	public void testCompareNullOrEmpty() {
		assertThrowsException(IllegalArgumentException.class, null, null);
		assertThrowsException(IllegalArgumentException.class, "1", null);
		assertThrowsException(IllegalArgumentException.class, null, "1");
		assertThrowsException(IllegalArgumentException.class, "", "");
		assertThrowsException(IllegalArgumentException.class, "", "1");
	}

	@Test
	public void testCompare() {
		assertTrue(VersionComparer.compare("1", "1") == 0);
		assertTrue(VersionComparer.compare("1", "2") < 0);
		assertTrue(VersionComparer.compare("2", "1") > 0);

		assertTrue(VersionComparer.compare("1.1", "1.2") < 0);
		assertTrue(VersionComparer.compare("1.2", "1.1") > 0);
		assertTrue(VersionComparer.compare("1.1.1", "1.1.2") < 0);
		assertTrue(VersionComparer.compare("1.2.1", "1.1.2") > 0);
		assertTrue(VersionComparer.compare("2", "1.1") > 0);
		assertTrue(VersionComparer.compare("2.1", "1") > 0);
		assertTrue(VersionComparer.compare("1.2.1", "1.1") > 0);
		assertTrue(VersionComparer.compare("1.1.1", "1.2") < 0);
		assertTrue(VersionComparer.compare("1.1", "1.1.0") == 0);
		assertTrue(VersionComparer.compare("1.1.2", "1.1") == 1);
	}

	private void assertThrowsException(Class<?> exceptionClass, String version1, String version2) {
		try {
			VersionComparer.compare(null, null);
		} catch (Exception e) {
			assertTrue(exceptionClass.isInstance(e));
			return;
		}
		fail("Exception was not thrown");
	}
}
