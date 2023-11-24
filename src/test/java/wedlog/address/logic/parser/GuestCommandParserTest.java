package wedlog.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static wedlog.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static wedlog.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static wedlog.address.testutil.Assert.assertThrows;
import static wedlog.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import wedlog.address.logic.commands.Command;
import wedlog.address.logic.commands.GuestAddCommand;
import wedlog.address.logic.commands.GuestDeleteCommand;
import wedlog.address.logic.commands.GuestEditCommand;
import wedlog.address.logic.commands.GuestFilterCommand;
import wedlog.address.logic.commands.GuestListCommand;
import wedlog.address.logic.commands.HelpCommand;
import wedlog.address.logic.parser.exceptions.ParseException;
import wedlog.address.model.person.Guest;
import wedlog.address.testutil.EditGuestDescriptorBuilder;
import wedlog.address.testutil.GuestBuilder;
import wedlog.address.testutil.GuestUtil;

public class GuestCommandParserTest {
    private GuestCommandParser parser = new GuestCommandParser();

    @Test
    public void parseCommand_guestAdd_success() throws Exception {
        String input = "add n/guest p/102391";

        Command command = parser.parseCommand(input);
        assertTrue(command instanceof GuestAddCommand);
    }

    @Test
    public void parseCommand_guestDelete() throws Exception {
        String input = "delete 1";
        Command command = parser.parseCommand(input);
        assertTrue(command instanceof GuestDeleteCommand);
    }

    @Test
    public void parseCommand_guestList() throws Exception {
        assertTrue(parser.parseCommand("list") instanceof GuestListCommand);
    }

    @Test
    public void parseCommand_guestFilter() throws Exception {
        assertTrue(parser.parseCommand("filter n/name") instanceof GuestFilterCommand);
    }

    @Test
    public void parseCommand_guestEdit() throws Exception {
        Guest guest = new GuestBuilder().build();
        GuestEditCommand.EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(guest).build();
        GuestEditCommand command = (GuestEditCommand) parser.parseCommand(GuestEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + GuestUtil.getEditGuestDescriptorDetails(descriptor));
        assertEquals(new GuestEditCommand(INDEX_FIRST_PERSON, descriptor), command);

    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
