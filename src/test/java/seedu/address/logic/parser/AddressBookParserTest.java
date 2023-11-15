package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStallCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteStallCommand;
import seedu.address.logic.commands.EditStallCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindStallCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.stall.Stall;
import seedu.address.model.util.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditStallDescriptorBuilder;
import seedu.address.testutil.StallBuilder;
import seedu.address.testutil.StallUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addStall() throws Exception {
        Stall stall = new StallBuilder().build();
        AddStallCommand command = (AddStallCommand) parser.parseCommand(StallUtil.getAddCommand(stall));
        assertEquals(new AddStallCommand(stall), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteStall() throws Exception {
        DeleteStallCommand command = (DeleteStallCommand) parser.parseCommand(
                DeleteStallCommand.COMMAND_WORD + " " + PREFIX_STALL + INDEX_FIRST_STALL.getOneBased());
        assertEquals(new DeleteStallCommand(INDEX_FIRST_STALL), command);
    }

    @Test
    public void parseCommand_editStall() throws Exception {
        Stall stall = new StallBuilder().build();
        EditStallCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder(stall).build();
        EditStallCommand command = (EditStallCommand) parser.parseCommand(EditStallCommand.COMMAND_WORD + " "
                + PREFIX_STALL + INDEX_FIRST_STALL.getOneBased()
                + " " + StallUtil.getEditStallDescriptorDetails(descriptor));
        assertEquals(new EditStallCommand(INDEX_FIRST_STALL, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStallCommand command = (FindStallCommand) parser.parseCommand(
                FindStallCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStallCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
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
