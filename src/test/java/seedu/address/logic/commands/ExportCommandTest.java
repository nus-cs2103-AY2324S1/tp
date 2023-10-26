package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ExportCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private ExportCommand exportCommand = new ExportCommand();
    @Test
    public void equalsMethod() {
        assert(new ExportCommand().equals(new ExportCommand()));
    }

    @Test
    public void toStringMethod() {
        assertEquals(new ExportCommand().toString(), "ExportCommand");
    }

    @Test
    public void execute_validExport_successful() {
        try {
            CommandResult result = exportCommand.execute(model);
            assertEquals(ExportCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
        } catch (CommandException e) {
            fail("This test has failed because of a failed export");
        }
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(exportCommand, exportCommand);
    }

    @Test
    public void equals_null_false() {
        assertEquals(false, exportCommand.equals(null));
    }


}
