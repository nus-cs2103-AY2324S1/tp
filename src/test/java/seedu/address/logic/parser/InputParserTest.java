package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.COMMAND_UNKNOWN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestData.INDEX_FIRST_CONTACT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.CommandUtil;
import seedu.address.testutil.EditContactDescriptorBuilder;

public class InputParserTest {

    @Test
    public void parseCommand_add() throws Exception {
        Contact contact = new ContactBuilder().build();
        AddCommand command = (AddCommand) InputParser.parseCommand(CommandUtil.getAddCommand(contact));
        assertEquals(new AddCommand(contact), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(InputParser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(InputParser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) InputParser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Contact contact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        EditCommand command = (EditCommand) InputParser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CONTACT.getOneBased() + " " + CommandUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_CONTACT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(InputParser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(InputParser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) InputParser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(InputParser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(InputParser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(InputParser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(InputParser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(
            ParseException.class,
            Messages.commandInvalidFormat(HelpCommand.MESSAGE_USAGE),
            () -> InputParser.parseCommand("")
        );
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(
            ParseException.class,
            COMMAND_UNKNOWN,
            () -> InputParser.parseCommand("unknownCommand")
        );
    }
}
