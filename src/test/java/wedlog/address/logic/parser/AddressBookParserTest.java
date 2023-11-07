package wedlog.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static wedlog.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static wedlog.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static wedlog.address.testutil.Assert.assertThrows;
import static wedlog.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import wedlog.address.logic.commands.ClearCommand;
import wedlog.address.logic.commands.Command;
import wedlog.address.logic.commands.EditCommand;
import wedlog.address.logic.commands.EditCommand.EditPersonDescriptor;
import wedlog.address.logic.commands.ExitCommand;
import wedlog.address.logic.commands.HelpCommand;
import wedlog.address.logic.commands.RedoCommand;
import wedlog.address.logic.commands.UndoCommand;
import wedlog.address.logic.parser.exceptions.ParseException;
import wedlog.address.model.person.Person;
import wedlog.address.testutil.EditPersonDescriptorBuilder;
import wedlog.address.testutil.PersonBuilder;
import wedlog.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_vendor() throws Exception {
        String input = "vendor list"; // a valid command
        // no split between Vendor & Guest type, so just test that the output is a command
        Command command = parser.parseCommand(input);
        assertEquals(command instanceof Command, true);
    }

    @Test
    public void parseCommand_guest() throws Exception {
        String input = "guest list"; // a valid command
        // no split between Vendor & Guest type, so just test that the output is a command
        Command command = parser.parseCommand(input);
        assertEquals(command instanceof Command, true);
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

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }


    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }
}
