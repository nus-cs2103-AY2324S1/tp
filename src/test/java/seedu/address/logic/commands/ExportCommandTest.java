package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ExportCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    @Test
    public void equalsMethod() {
        assert(new ExportCommand().equals(new ExportCommand()));
    }

    @Test
    public void toStringMethod() {
        assertEquals(new ExportCommand().toString(), "ExportCommand");
    }

    @Test
    public void execute_success() {
        CommandResult expectedCommandResult = new CommandResult("Sucessfully Exported", false, false, false);
        assertCommandSuccess(new ExportCommand(), model, expectedCommandResult, expectedModel);
    }

}
