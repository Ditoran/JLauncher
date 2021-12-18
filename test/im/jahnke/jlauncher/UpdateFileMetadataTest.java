package im.jahnke.jlauncher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class UpdateFileMetadataTest {

	@Test
	public void testParseMetadata() {
		String text = "version:2\r\n" + "checksum:123456\r\n" + "updatefile:DemoApp.jar";

		UpdateFileMetadata metadata = new UpdateFileMetadata();
		metadata.parseMetadata(text);

		assertEquals("2", metadata.getVersion());
		assertEquals("123456", metadata.getChecksum());
		assertEquals("DemoApp.jar", metadata.getUpdateFileName());
	}

}
