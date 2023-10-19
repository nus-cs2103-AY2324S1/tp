package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.UpdatePhotoCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.testutil.ModelStub;

public class UpdatePhotoCommandTest {

    @Test
    public void invalidIndex_exceptionThrown() {
        AddressBookParser parser = new AddressBookParser();
        try {
            Command command = parser.parseCommand("updatephoto 0 path/D:/image/cute_cat.png");
            assertTrue(command instanceof UpdatePhotoCommand);
            UpdatePhotoCommand updatePhotoCommand = (UpdatePhotoCommand) command;
            updatePhotoCommand.execute(new ModelManager());
        } catch (ParseException e) {
            assertEquals(1, 0);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), "The person index provided is invalid");
        }
    }
}









