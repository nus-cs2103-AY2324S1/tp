package seedu.cc.logic.commands;

import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import org.junit.jupiter.api.Test;

import seedu.cc.model.ClinicBook;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyClinicBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyClinicBook_success() {
        Model model = new ModelManager(getTypicalClinicBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalClinicBook(), new UserPrefs());
        expectedModel.setClinicBook(new ClinicBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
