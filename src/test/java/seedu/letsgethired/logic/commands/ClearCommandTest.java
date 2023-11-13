package seedu.letsgethired.logic.commands;

import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyInternTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternTracker_success() {
        UserPrefs userPrefs = new UserPrefs();
        Model model = new ModelManager(getTypicalInternTracker(), userPrefs);
        Model expectedModel = new ModelManager(getTypicalInternTracker(), userPrefs);
        expectedModel.setInternTracker(new InternTracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
