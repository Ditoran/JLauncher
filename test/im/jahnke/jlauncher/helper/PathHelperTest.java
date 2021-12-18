package im.jahnke.jlauncher.helper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class PathHelperTest {

	@Test
	public void testGetWorkingDir() {
		String workingDir = PathHelper.getWorkingDir();
		assertTrue(workingDir.matches(".+\\\\JLauncher\\\\"));
	}

	@Test
	public void test2() {
		String path = PathHelper.getAbsoluteFilePathInWorkingDirectory("sample.txt");
		assertTrue(path.matches(".+\\\\JLauncher\\\\sample\\.txt"));
	}
}
