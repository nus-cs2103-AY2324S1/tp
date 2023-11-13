package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalWellNus.getTypicalWellNus;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.WellNus;

public class ClearCommandTest {

    // EP: Empty WellNus
    @Test
    public void execute_emptyWellNus_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    // EP: Non-empty WellNus
    @Test
    public void execute_nonEmptyWellNus_success() {
        Model model = new ModelManager(getTypicalWellNus(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalWellNus(), new UserPrefs());
        expectedModel.setWellNusData(new WellNus());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
