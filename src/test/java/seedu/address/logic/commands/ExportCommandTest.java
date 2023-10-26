package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ExportCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
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
        ExportCommand exportCommand = new ExportCommand();

        String expectedMessage = String.format(ExportCommand.MESSAGE_SUCCESS);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(exportCommand, model, expectedMessage, expectedModel, false);
    }

}
