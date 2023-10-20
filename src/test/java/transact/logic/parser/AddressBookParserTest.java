package transact.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static transact.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static transact.testutil.Assert.assertThrows;
import static transact.testutil.TypicalIndexes.ID_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import transact.logic.commands.AddStaffCommand;
import transact.logic.commands.ClearCommand;
import transact.logic.commands.DeleteStaffCommand;
import transact.logic.commands.EditStaffCommand;
import transact.logic.commands.EditStaffCommand.EditPersonDescriptor;
import transact.logic.commands.ExitCommand;
import transact.logic.commands.FindCommand;
import transact.logic.commands.HelpCommand;
import transact.logic.commands.ViewCommand;
import transact.logic.parser.exceptions.ParseException;
import transact.model.person.NameContainsKeywordsPredicate;
import transact.model.person.Person;
import transact.testutil.EditPersonDescriptorBuilder;
import transact.testutil.PersonBuilder;
import transact.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().withNextIdAndFree().build();
        AddStaffCommand command = (AddStaffCommand) parser.parseCommand(PersonUtil.getAddStaffCommand(person));
        assertEquals(new AddStaffCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteStaffCommand command = (DeleteStaffCommand) parser.parseCommand(
                DeleteStaffCommand.COMMAND_WORD + " " + ID_FIRST_PERSON);
        assertEquals(new DeleteStaffCommand(ID_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditStaffCommand command = (EditStaffCommand) parser.parseCommand(EditStaffCommand.COMMAND_WORD + " "
                + ID_FIRST_PERSON + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditStaffCommand(ID_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " staff") instanceof ViewCommand);
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " transaction") instanceof ViewCommand);
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " staff 3") instanceof ViewCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE),
                () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
