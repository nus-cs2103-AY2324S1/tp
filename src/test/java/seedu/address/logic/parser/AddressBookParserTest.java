package seedu.address.logic.parser;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIds.ID_FIRST_EMPLOYEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddLeaveCommand;
import seedu.address.logic.commands.AddRemarkCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteLeaveCommand;
import seedu.address.logic.commands.DeleteRemarkCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.logic.commands.EditLeaveCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListLeaveCommand;
import seedu.address.logic.commands.OvertimeCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeContainsKeywordsPredicate;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.OvertimeHours;
import seedu.address.model.remark.Remark;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.EmployeeUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

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
                DeleteCommand.COMMAND_WORD + " " + ID_FIRST_EMPLOYEE);
        assertEquals(new DeleteCommand(ID_FIRST_EMPLOYEE), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(employee).build();
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
        assertEquals(new FindCommand(new EmployeeContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_sort() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " f/ " + "salary" + " in/ " + "asc");
        assertEquals(new SortCommand("salary", "asc"), command);
    }

    @Test
    public void parseCommand_addLeave() throws Exception {
        AddLeaveCommand command = (AddLeaveCommand) parser.parseCommand(
                    AddLeaveCommand.COMMAND_WORD + " " + PREFIX_ID + "EID1234-5678 "
                + PREFIX_FROM + "2023-10-12 " + PREFIX_TO + "2023-10-15");
        Id id = new Id("EID1234-5678");
        LocalDate startDate = LocalDate.parse("2023-10-12", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse("2023-10-15", DateTimeFormatter.ISO_LOCAL_DATE);
        assertEquals(new AddLeaveCommand(id, startDate, endDate), command);
    }

    @Test
    public void parseCommand_deleteLeave() throws Exception {
        DeleteLeaveCommand command = (DeleteLeaveCommand) parser.parseCommand(
                DeleteLeaveCommand.COMMAND_WORD + " " + PREFIX_ID + "EID1234-5678 "
                        + PREFIX_FROM + "2023-10-12 " + PREFIX_TO + "2023-10-15");
        Id id = new Id("EID1234-5678");
        LocalDate startDate = LocalDate.parse("2023-10-12", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse("2023-10-15", DateTimeFormatter.ISO_LOCAL_DATE);
        assertEquals(new DeleteLeaveCommand(id, startDate, endDate), command);
    }

    @Test
    public void parseCommand_editLeave() throws Exception {
        EditLeaveCommand command = (EditLeaveCommand) parser.parseCommand(
                EditLeaveCommand.COMMAND_WORD + " " + PREFIX_ID + "EID1234-5678 "
                        + PREFIX_OLD + "2023-10-12 " + PREFIX_NEW + "2023-10-15");
        Id id = new Id("EID1234-5678");
        LocalDate oldDate = LocalDate.parse("2023-10-12", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate newDate = LocalDate.parse("2023-10-15", DateTimeFormatter.ISO_LOCAL_DATE);
        assertEquals(new EditLeaveCommand(id, oldDate, newDate), command);
    }

    @Test
    public void parseCommand_listLeave() throws Exception {
        ListLeaveCommand command = (ListLeaveCommand) parser.parseCommand(
                ListLeaveCommand.COMMAND_WORD + " " + PREFIX_ON + "2023-11-01");
        assertEquals(
                new ListLeaveCommand(LocalDate.parse("2023-11-01", ISO_LOCAL_DATE)),
                command
        );
    }

    @Test
    public void parseCommand_reset() throws Exception {
        ResetCommand command = (ResetCommand) parser.parseCommand(
                ResetCommand.COMMAND_WORD + " f/ " + "overtime");
        assertEquals(new ResetCommand("overtime"), command);
    }

    @Test
    public void parseCommand_overtime() throws Exception {
        OvertimeCommand command = (OvertimeCommand) parser.parseCommand(
                OvertimeCommand.COMMAND_WORD + " " + PREFIX_ID + ID_FIRST_EMPLOYEE + " " + PREFIX_OPERATION
                        + "inc " + PREFIX_AMOUNT + "2");
        assertEquals(new OvertimeCommand(ID_FIRST_EMPLOYEE, new OvertimeHours(2), true), command);
    }

    @Test
    public void parseCommand_addRemark() throws Exception {
        String userInput = AddRemarkCommand.COMMAND_WORD + " " + PREFIX_ID + ID_FIRST_EMPLOYEE + " " + PREFIX_REMARK
                + "fast worker";
        AddRemarkCommand command = (AddRemarkCommand) parser.parseCommand(userInput);
        assertEquals(new AddRemarkCommand(ID_FIRST_EMPLOYEE, new Remark("fast worker")), command);
    }

    @Test
    public void parseCommand_deleteRemark() throws Exception {
        String userInput = DeleteRemarkCommand.COMMAND_WORD + " " + PREFIX_ID + ID_FIRST_EMPLOYEE + " " + PREFIX_REMARK
                + "fast worker";
        DeleteRemarkCommand command = (DeleteRemarkCommand) parser.parseCommand(userInput);
        assertEquals(new DeleteRemarkCommand(ID_FIRST_EMPLOYEE, new Remark("fast worker")), command);
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
