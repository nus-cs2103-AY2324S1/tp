package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.model.interview.Interview;

class AddInterviewCommandTest {
    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInterviewCommand(null, null));
    }

    @Test
    public void toStringMethod() {
        Interview stubInterview = new Interview("stub");
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand(INDEX_FIRST_APPLICANT, stubInterview);
        String expected = AddInterviewCommand.class.getCanonicalName() + "{interviewToAdd=" + stubInterview + "}";
        assertEquals(expected, addInterviewCommand.toString());
    }
}
