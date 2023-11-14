package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ViewAppointmentCommand.SHOWING_APPOINTMENTS_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ViewAppointmentCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_APPOINTMENTS_MESSAGE, false, true, false);
        assertCommandSuccess(new ViewAppointmentCommand(), model, expectedCommandResult, expectedModel);
    }
}
