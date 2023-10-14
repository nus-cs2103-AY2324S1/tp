package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Subject(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Subject(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Subject.isValidSubjectName(null));
    }

}
