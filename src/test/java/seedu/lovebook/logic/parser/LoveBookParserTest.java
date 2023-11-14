package seedu.lovebook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.commands.AddCommand;
import seedu.lovebook.logic.commands.ClearCommand;
import seedu.lovebook.logic.commands.DeleteCommand;
import seedu.lovebook.logic.commands.EditCommand;
import seedu.lovebook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.lovebook.logic.commands.ExitCommand;
import seedu.lovebook.logic.commands.FindCommand;
import seedu.lovebook.logic.commands.HelpCommand;
import seedu.lovebook.logic.commands.ListCommand;
import seedu.lovebook.logic.commands.StarCommand;
import seedu.lovebook.logic.commands.UnstarCommand;
import seedu.lovebook.logic.parser.exceptions.ParseException;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.NameContainsKeywordsPredicate;
import seedu.lovebook.testutil.EditPersonDescriptorBuilder;
import seedu.lovebook.testutil.PersonBuilder;
import seedu.lovebook.testutil.PersonUtil;

public class LoveBookParserTest {

    private final LoveBookParser parser = new LoveBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Date date = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(date));
        assertEquals(new AddCommand(date), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Date date = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(date).build();
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
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }
    @Test
    public void parseCommand_star() throws Exception {
        StarCommand command = (StarCommand) parser.parseCommand(
                StarCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new StarCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_unstar() throws Exception {
        UnstarCommand command = (UnstarCommand) parser.parseCommand(
                UnstarCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new UnstarCommand(INDEX_FIRST_PERSON), command);
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
