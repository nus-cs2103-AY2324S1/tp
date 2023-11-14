package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.attachment.Attachment;

public class AttachmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attachment(null));
    }

    @Test
    public void constructor_invalidPathString_throwsIllegalArgumentException() {
        String invalidPath = "[E:\\Temp\0\\564\\324\\123.txt]";
        assertThrows(IllegalArgumentException.class, () -> new Attachment(invalidPath));
    }

    @Test
    public void isValidPathString() {
        // null path string
        assertThrows(NullPointerException.class, () -> Attachment.isValidAttachment(null));
    }

}
