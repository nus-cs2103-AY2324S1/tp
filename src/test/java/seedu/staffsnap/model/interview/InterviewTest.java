package seedu.staffsnap.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_TYPE_TECHNICAL;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;

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

    @Test
    void isValidType() {
    }

    @Test
    void getType() {
        assertEquals(BENSON.getInterviews().get(0).getType(), VALID_TYPE_TECHNICAL);
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }

    @Test
    void compareTo() {
    }
}
