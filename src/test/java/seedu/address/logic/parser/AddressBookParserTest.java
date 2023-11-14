package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AppendLogCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearLogCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCommand.DeletePersonDescriptor;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.LogCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.CompositePredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_add_alias() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommandAlias(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_clear_alias() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD_ALIAS) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD_ALIAS + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " n/" + PersonBuilder.DEFAULT_NAME);
        assertEquals(new DeleteCommand(null,
                new Name(PersonBuilder.DEFAULT_NAME), new DeletePersonDescriptor()), command);
    }

    @Test
    public void parseCommand_delete_alias() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_WORD_ALIAS + " n/" + PersonBuilder.DEFAULT_NAME);
        assertEquals(new DeleteCommand(null,
                new Name(PersonBuilder.DEFAULT_NAME), new DeletePersonDescriptor()), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
            + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(new Name("Amy Bee"), null, descriptor), command);
    }

    @Test
    public void parseCommand_edit_alias() throws Exception {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD_ALIAS + " "
            + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(new Name("Amy Bee"), null, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_exit_alias() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD_ALIAS) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD_ALIAS + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        CompositePredicate findCommandPredicate = new CompositePredicate();
        findCommandPredicate.add(new NameContainsKeywordsPredicate(keywords));
        assertEquals(new FindCommand(findCommandPredicate), command);
    }

    @Test
    public void parseCommand_find_alias() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(FindCommand.COMMAND_WORD_ALIAS
                + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        CompositePredicate findCommandPredicate = new CompositePredicate();
        findCommandPredicate.add(new NameContainsKeywordsPredicate(keywords));
        assertEquals(new FindCommand(findCommandPredicate), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_help_alias() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD_ALIAS) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD_ALIAS + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_list_alias() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD_ALIAS) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD_ALIAS + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_undo_alias() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD_ALIAS) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD_ALIAS + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_log() throws Exception {
        assertTrue(parser.parseCommand(LogCommand.COMMAND_WORD) instanceof LogCommand);
        assertTrue(parser.parseCommand(LogCommand.COMMAND_WORD + " 3") instanceof LogCommand);
    }

    @Test
    public void parseCommand_log_alias() throws Exception {
        assertTrue(parser.parseCommand(LogCommand.COMMAND_WORD_ALIAS) instanceof LogCommand);
        assertTrue(parser.parseCommand(LogCommand.COMMAND_WORD_ALIAS + " 3") instanceof LogCommand);
    }

    @Test
    public void parseCommand_appendlog() throws Exception {
        assertTrue(parser.parseCommand(AppendLogCommand.COMMAND_WORD) instanceof AppendLogCommand);
        assertTrue(parser.parseCommand(AppendLogCommand.COMMAND_WORD + " 3") instanceof AppendLogCommand);
    }

    @Test
    public void parseCommand_appendlog_alias() throws Exception {
        assertTrue(parser.parseCommand(AppendLogCommand.COMMAND_WORD_ALIAS) instanceof AppendLogCommand);
        assertTrue(parser.parseCommand(AppendLogCommand.COMMAND_WORD_ALIAS + " 3") instanceof AppendLogCommand);
    }

    @Test
    public void parseCommand_clearlog() throws Exception {
        assertTrue(parser.parseCommand(ClearLogCommand.COMMAND_WORD) instanceof ClearLogCommand);
        assertTrue(parser.parseCommand(ClearLogCommand.COMMAND_WORD + " 3") instanceof ClearLogCommand);
    }

    @Test
    public void parseCommand_clearlog_alias() throws Exception {
        assertTrue(parser.parseCommand(ClearLogCommand.COMMAND_WORD_ALIAS) instanceof ClearLogCommand);
        assertTrue(parser.parseCommand(ClearLogCommand.COMMAND_WORD_ALIAS + " 3") instanceof ClearLogCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        String expectedString = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedString, () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
