package seedu.application.model.job.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.testutil.TypicalInterviews.CHEF_INTERVIEW;
import static seedu.application.testutil.TypicalInterviews.CLEANER_INTERVIEW;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.InterviewBuilder;

public class InterviewTest {
    @Test
    public void testEqualsAndHashcode() {
        // same values -> returns true
        Interview chefInterviewCopy = new InterviewBuilder(CHEF_INTERVIEW).build();
        assertTrue(CHEF_INTERVIEW.equals(chefInterviewCopy));
        assertEquals(CHEF_INTERVIEW.hashCode(), chefInterviewCopy.hashCode());

        // same object -> returns true
        assertTrue(CHEF_INTERVIEW.equals(CHEF_INTERVIEW));

        // null -> returns false
        assertFalse(CHEF_INTERVIEW.equals(null));

        // different type -> returns false
        assertFalse(CHEF_INTERVIEW.equals(5.0f));

        // different job -> returns false
        assertFalse(CHEF_INTERVIEW.equals(CLEANER_INTERVIEW));
        assertNotEquals(CHEF_INTERVIEW.hashCode(), CLEANER_INTERVIEW.hashCode());

        // different role -> returns false
        Interview chefInterviewEditedType = new InterviewBuilder(CHEF_INTERVIEW)
            .withType(VALID_INTERVIEW_TYPE_CLEANER).build();
        assertFalse(CHEF_INTERVIEW.equals(chefInterviewEditedType));
        assertNotEquals(CHEF_INTERVIEW.hashCode(), chefInterviewEditedType.hashCode());

        // different role -> returns false
        Interview chefInterviewEditedDateTime = new InterviewBuilder(CHEF_INTERVIEW)
            .withDateTime(VALID_INTERVIEW_DATETIME_CLEANER).build();
        assertFalse(CHEF_INTERVIEW.equals(chefInterviewEditedDateTime));
        assertNotEquals(CHEF_INTERVIEW.hashCode(), chefInterviewEditedDateTime.hashCode());

        // different role -> returns false
        Interview chefInterviewEditedAddress = new InterviewBuilder(CHEF_INTERVIEW)
            .withAddress(VALID_INTERVIEW_ADDRESS_CLEANER).build();
        assertFalse(CHEF_INTERVIEW.equals(chefInterviewEditedAddress));
        assertNotEquals(CHEF_INTERVIEW.hashCode(), chefInterviewEditedAddress.hashCode());

    }

    @Test
    public void toStringMethod() {
        String expected = Interview.class.getCanonicalName()
            + "{interviewType=" + CHEF_INTERVIEW.getInterviewType()
            + ", interviewDateTime=" + CHEF_INTERVIEW.getInterviewDateTime()
            + ", interviewAddress=" + CHEF_INTERVIEW.getInterviewAddress()
            + "}";
        assertEquals(expected, CHEF_INTERVIEW.toString());
    }
}
