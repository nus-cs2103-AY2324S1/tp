package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;

import org.junit.jupiter.api.Test;

class DeleteInterviewCommandTest {
    @Test
    public void equalsMethod() {
        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(
                INDEX_FIRST_APPLICANT, INDEX_FIRST_INTERVIEW);
        assertEquals(deleteInterviewCommand, deleteInterviewCommand);
        assertEquals(deleteInterviewCommand,
                new DeleteInterviewCommand(INDEX_FIRST_APPLICANT, INDEX_FIRST_INTERVIEW));
    }

    @Test
    public void toStringMethod() {
        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(
                INDEX_FIRST_APPLICANT, INDEX_FIRST_INTERVIEW);
        String expected = DeleteInterviewCommand.class.getCanonicalName()
                + "{targetApplicantIndex=" + INDEX_FIRST_APPLICANT + ", "
                + "targetInterviewIndex=" + INDEX_FIRST_INTERVIEW + "}";
        assertEquals(expected, deleteInterviewCommand.toString());
    }
}
