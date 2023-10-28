package flashlingo.commons.util;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.commons.util.FileUtil;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilTest {

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

}
