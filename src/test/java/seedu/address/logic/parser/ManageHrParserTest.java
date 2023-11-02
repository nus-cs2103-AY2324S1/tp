package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.ContainsDepartmentPredicate;
import seedu.address.model.employee.Employee;
import seedu.address.model.name.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.EmployeeUtil;

public class ManageHrParserTest {

    private final ManageHrParser parser = new ManageHrParser();

    @Test
    public void parseCommand_add() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EmployeeUtil.getAddCommand(employee));
        assertEquals(new AddCommand(employee), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_EMPLOYEE.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_EMPLOYEE), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(employee).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_EMPLOYEE.getOneBased() + " " + EmployeeUtil.getEditEmployeeDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_EMPLOYEE, descriptor), command);
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
    public void parseCommand_filter() throws Exception {
        String keyword = "d/R&D";
        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD + " " + keyword);
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate();
        predicate.setDepartment("R&D");
        assertEquals(new FilterCommand(predicate), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " add") instanceof HelpCommand);
        // To do: add more tests.
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

    @Test
    public void checkCommand_add() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        Pair<String, String> command = parser.checkCommandUsage(EmployeeUtil.getAddCommand(employee));
        assertEquals(new Pair<String, String>(AddCommand.MESSAGE_USAGE, AddCommand.MESSAGE_EXAMPLE), command);
    }

    @Test
    public void checkCommand_clear() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        Pair<String, String> command = parser.checkCommandUsage(ClearCommand.COMMAND_WORD);
        assertEquals(new Pair<String, String>(ClearCommand.MESSAGE_USAGE, ClearCommand.MESSAGE_EXAMPLE), command);
    }

    @Test
    public void checkCommand_delete() throws Exception {
        Pair<String, String> command = parser.checkCommandUsage(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_EMPLOYEE.getOneBased());
        assertEquals(new Pair<String, String>(DeleteCommand.MESSAGE_USAGE, DeleteCommand.MESSAGE_EXAMPLE), command);
    }

    @Test
    public void checkCommand_edit() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(employee).build();
        Pair<String, String> command = parser.checkCommandUsage(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_EMPLOYEE.getOneBased() + " " + EmployeeUtil.getEditEmployeeDescriptorDetails(descriptor));
        assertEquals(new Pair<String, String>(EditCommand.MESSAGE_USAGE, EditCommand.MESSAGE_EXAMPLE), command);
    }

    @Test
    public void checkCommand_exit() throws Exception {
        Pair<String, String> command = parser.checkCommandUsage(ExitCommand.COMMAND_WORD);
        assertEquals(command, new Pair<String, String>(ExitCommand.MESSAGE_USAGE, ExitCommand.MESSAGE_EXAMPLE));
    }

    @Test
    public void checkCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        Pair<String, String> command = parser.checkCommandUsage(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new Pair<String, String>(FindCommand.MESSAGE_USAGE, FindCommand.MESSAGE_EXAMPLE), command);
    }

    @Test
    public void checkCommand_help() throws Exception {
        Pair<String, String> command = parser.checkCommandUsage(HelpCommand.COMMAND_WORD);
        assertEquals(command, new Pair<String, String>(HelpCommand.MESSAGE_USAGE, HelpCommand.MESSAGE_EXAMPLE));
        // To do: add more tests.
    }

    @Test
    public void checkCommand_list() throws Exception {
        Pair<String, String> command = parser.checkCommandUsage(ListCommand.COMMAND_WORD);
        assertEquals(command, new Pair<String, String>(ListCommand.MESSAGE_USAGE, ListCommand.MESSAGE_EXAMPLE));
    }

    @Test
    public void checkCommand_filter() throws Exception {
        Pair<String, String> command = parser.checkCommandUsage(FilterCommand.COMMAND_WORD);
        assertEquals(command, new Pair<String, String>(FilterCommand.MESSAGE_USAGE, FilterCommand.MESSAGE_EXAMPLE));
    }

    @Test
    public void checkCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.checkCommandUsage(""));
    }

    @Test
    public void checkCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.checkCommandUsage("unknownCommand"));
    }

}
