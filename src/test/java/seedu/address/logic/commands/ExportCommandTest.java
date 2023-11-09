package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ExportCommandTest {
    @Test
    public void execute_export_success() throws CommandException {
        Model model = new ModelManager();
        ExportCommand exportCommand = new ExportCommand();
        CommandResult commandResult = exportCommand.execute(model);
        assertEquals(ExportCommand.SHOWING_EXPORT_MESSAGE, commandResult.getFeedbackToUser());
    }

}
