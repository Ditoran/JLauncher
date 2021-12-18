package im.jahnke.jlauncher.util;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.Test;

public class ChecksumTest {

	@Test
	public void testChecksumSha256() {
		File downloadedFile = new File("test-res\\one-file.zip");
		Checksum sum = new Checksum(downloadedFile);
		assertEquals("e7a6bf71e61bdd3bddadf65ded85784238c135935ec536beee852e7785a66e2b", sum.getSHA256().toLowerCase());
	}

	@Test
	public void testChecksumSha256NonExistingFile() {
		File downloadedFile = new File("test-res\\nonexisting.zip");
		Checksum sum = new Checksum(downloadedFile);
		assertNull(sum.getSHA256());
	}

	@Test
	public void testChecksumMd5() {
		File downloadedFile = new File("test-res\\one-file.zip");
		Checksum sum = new Checksum(downloadedFile);
		assertEquals("7210ef49fd067868dc243a90a3b4388e", sum.getMD5().toLowerCase());
	}

}
