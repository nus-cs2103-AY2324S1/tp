package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
        assertNull(model.getCurrentlyDisplayedPerson());
        assertNull(expectedModel.getCurrentlyDisplayedPerson());
        assertNull(model.getCurrentlyDisplayedLesson());
        assertNull(expectedModel.getCurrentlyDisplayedLesson());
    }

}
