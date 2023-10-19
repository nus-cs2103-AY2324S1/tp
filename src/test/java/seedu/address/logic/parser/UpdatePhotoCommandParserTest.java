package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UpdatePhotoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UpdatePhotoCommandParserTest {

    @Test
    public void parseInvalidCommand_exceptionThrown() {
        AddressBookParser parser = new AddressBookParser();
        try {
            Command updatePhotoCommand = parser.parseCommand("updatephoto path/D:/image/cute_cat.png");
        } catch (ParseException e) {
            assertEquals(e.getMessage(),
                    "Invalid command format! \n"
                            + UpdatePhotoCommand.MESSAGE_USAGE);
        }
    }
}






