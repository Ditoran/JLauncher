package im.jahnke.jlauncher.helper;

import java.io.File;
import java.nio.file.Paths;

public final class PathHelper {

	public static String getWorkingDir() {
		String workingDir = Paths.get("").toAbsolutePath().toString();
		return workingDir + File.separatorChar;
	}

	public static String getAbsoluteFilePathInWorkingDirectory(String fileName) {
		String workingDir = getWorkingDir();
		return workingDir + fileName;
	}
}
