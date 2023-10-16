package seedu.staffsnap.logic.commands;

import static seedu.staffsnap.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalApplicantBook;

import org.junit.jupiter.api.Test;

//import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
//import seedu.staffsnap.model.UserPrefs;

public class ClearCommandTest {


    @Test
    public void execute_emptyApplicantBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
    /*
    @Test
    public void execute_nonEmptyApplicantBook_success() {
        Model model = new ModelManager(getTypicalApplicantBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalApplicantBook(), new UserPrefs());
        expectedModel.setApplicantBook(new ApplicantBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
    */




}
