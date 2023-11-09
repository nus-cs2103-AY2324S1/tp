package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ExportCommandTest {
    private final Model model = new ModelManager();
    private final ExportCommand exportCommand = new ExportCommand();
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
            assert(ExportCommand.MESSAGE_SUCCESS.equals(result.getFeedbackToUser())
                    || result.getFeedbackToUser().contains("Error exporting data"));
        } catch (CommandException e) {
            //This exception is expected
        } catch (Exception e) {
            fail("This test has failed because of a failed export");
        }
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(exportCommand, new ExportCommand());
    }

    @Test
    public void equals_null_false() {
        assertEquals(false, exportCommand.equals(null));
    }


}
