package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalApplicantBook;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.UserPrefs;
import seedu.staffsnap.model.interview.Interview;

class AddInterviewCommandTest {
    private Model model = new ModelManager(getTypicalApplicantBook(), new UserPrefs());

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

    @Test
    public void equalsMethod() {
        Interview stubInterview = new Interview("stub");
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand(INDEX_FIRST_APPLICANT, stubInterview);
        assertEquals(addInterviewCommand, addInterviewCommand);
        assertEquals(addInterviewCommand, new AddInterviewCommand(INDEX_FIRST_APPLICANT, stubInterview));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Interview stubInterview = new Interview("stub");
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand(INDEX_FIRST_APPLICANT, stubInterview);
        assertThrows(NullPointerException.class, () -> addInterviewCommand.execute(null));
    }
}
