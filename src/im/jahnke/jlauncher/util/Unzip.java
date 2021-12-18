package im.jahnke.jlauncher.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {

	private static Logger log = Logger.getLogger(Unzip.class.getName());

	public static void validate() {

	}

	public static boolean unzipFileToFolder(File zipFile, File destinationFolder) {
		if (!zipFile.isFile() || !destinationFolder.isDirectory()) {
			return false;
		}

		byte[] buffer = new byte[1024];

		try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));) {
			ZipEntry zipEntry = zis.getNextEntry();
			while (zipEntry != null) {
				File newFile = newFile(destinationFolder, zipEntry);
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				zipEntry = zis.getNextEntry();
			}
			return true;
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		return false;
	}

	private static File newFile(File destinationDir, ZipEntry zipEntry) {
		File destFile = new File(destinationDir, zipEntry.getName());

		String destDirPath = null;
		String destFilePath = null;
		try {
			destDirPath = destinationDir.getCanonicalPath();
			destFilePath = destFile.getCanonicalPath();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}

		if (!destFilePath.startsWith(destDirPath + File.separator)) {
			return null;
		}

		return destFile;
	}
}
