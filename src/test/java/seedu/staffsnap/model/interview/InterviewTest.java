package seedu.staffsnap.model.interview;

import static seedu.staffsnap.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Interview(null));
    }

    @Test
    public void constructor_invalidInterviewName_throwsIllegalArgumentException() {
        String invalidType = "";
        assertThrows(IllegalArgumentException.class, () -> new Interview(invalidType));
    }

    @Test
    public void isValidInterviewName() {
        // null interview name
        assertThrows(NullPointerException.class, () -> Interview.isValidType(null));
    }

}
