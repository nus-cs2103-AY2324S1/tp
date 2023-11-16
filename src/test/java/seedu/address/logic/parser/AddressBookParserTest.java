package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.add.AddDeveloperCommand;
import seedu.address.logic.commands.delete.DeleteDeveloperCommand;
import seedu.address.logic.commands.edit.EditDeveloperCommand;
import seedu.address.logic.commands.list.ListClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.developer.Developer;
import seedu.address.testutil.DeveloperBuilder;
import seedu.address.testutil.DeveloperUtil;
import seedu.address.testutil.EditDeveloperDescriptorBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Developer developer = new DeveloperBuilder().build();
        AddressBookParser.unlock();
        AddDeveloperCommand command = (AddDeveloperCommand) parser.parseCommand(DeveloperUtil.getAddCommand(developer));
        assertEquals(new AddDeveloperCommand(developer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        AddressBookParser.unlock();
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        AddressBookParser.unlock();
        DeleteDeveloperCommand command = (DeleteDeveloperCommand) parser.parseCommand(
                DeleteDeveloperCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteDeveloperCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Developer developer = new DeveloperBuilder().build();
        AddressBookParser.unlock();
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder(developer).build();
        EditDeveloperCommand command = (EditDeveloperCommand) parser
                .parseCommand(EditDeveloperCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + DeveloperUtil.getEditDeveloperDescriptorDetails(descriptor));
        assertEquals(new EditDeveloperCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    /* @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        AddressBookParser.unlock();
        FindDeveloperCommand command = (FindDeveloperCommand) parser.parseCommand(
                FindDeveloperCommand.COMMAND_WORD + " n/ "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindDeveloperCommand(new NameDeveloperContainsKeywordsPredicate(keywords)), command);
    } */

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        AddressBookParser.unlock();
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD) instanceof ListClientCommand);
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD + " 3") instanceof ListClientCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }
}
