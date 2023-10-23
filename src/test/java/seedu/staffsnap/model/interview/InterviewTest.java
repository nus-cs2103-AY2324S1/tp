package seedu.staffsnap.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_INTERVIEW_BEHAVIORAL;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_INTERVIEW_TECHNICAL;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_TYPE_TECHNICAL;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;

import org.junit.jupiter.api.Test;

public class InterviewTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Interview(null, null));
    }

    @Test
    public void constructor_invalidInterviewName_throwsIllegalArgumentException() {
        String invalidType = "";
        Rating validRating = BENSON.getInterviews().get(0).getRating();
        assertThrows(IllegalArgumentException.class, () -> new Interview(invalidType, validRating));
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
    void testEquals_nullInterview() {
        assertEquals(BENSON.getInterviews().get(0).equals(null), false);
    }

    @Test
    public void testEquals_sameInterview() {
        assertEquals(BENSON.getInterviews().get(0).equals(BENSON.getInterviews().get(0)), true);
    }

    @Test
    public void testEquals_differentInterview() {
        assertEquals(BENSON.getInterviews().get(0).equals(VALID_INTERVIEW_BEHAVIORAL), false);
    }

    @Test
    public void testHashCode() {
        assertEquals(BENSON.getInterviews().get(0).hashCode(), BENSON.getInterviews().get(0).hashCode());
    }

    @Test
    void compareTo() {
        Rating rating = VALID_INTERVIEW_BEHAVIORAL.getRating();
        assertEquals((new Interview("a", rating)).compareTo(new Interview("a", rating)), 0);
        assertEquals((new Interview("a", rating)).compareTo(new Interview("b", rating)), -1);
        assertEquals((new Interview("b", rating)).compareTo(new Interview("a", rating)), 1);
    }

    @Test
    public void isSameInterview() {
        // same interview type -> returns true
        assertTrue(VALID_INTERVIEW_TECHNICAL.isSameInterview(BENSON.getInterviews()));

        // different interview type -> returns false
        assertFalse(VALID_INTERVIEW_BEHAVIORAL.isSameInterview(BENSON.getInterviews()));
    }
}
