package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_ON;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BenefitCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CurrentMonthCommand;
import seedu.address.logic.commands.DeductCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.NextMonthCommand;
import seedu.address.logic.commands.PayrollCommand;
import seedu.address.logic.commands.PayslipCommand;
import seedu.address.logic.commands.PreviousMonthCommand;
import seedu.address.logic.commands.ReadCommand;
import seedu.address.logic.commands.ViewLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Benefit;
import seedu.address.model.person.Deduction;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reason;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;
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
        Person person = new PersonBuilder().build();
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditEmployeeDescriptorDetails(descriptor));
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
    public void parseCommand_read() throws Exception {
        ReadCommand command = (ReadCommand) parser.parseCommand(
            ReadCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + "/p");
        assertEquals(new ReadCommand(INDEX_FIRST_PERSON, "phone"), command);
    }

    @Test
    public void parseCommand_deduct() throws Exception {
        DeductCommand command = (DeductCommand) parser.parseCommand(
            DeductCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + "/v 150.00 /r cpf");
        assertEquals(new DeductCommand(INDEX_FIRST_PERSON,
            new Deduction("150.00", Reason.EMPLOYEE_CPF_DEDUCTION)), command);
    }

    @Test
    public void parseCommand_benefit() throws Exception {
        BenefitCommand command = (BenefitCommand) parser.parseCommand(
            BenefitCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + "/v 150.00 /r bonus");
        assertEquals(new BenefitCommand(INDEX_FIRST_PERSON,
            new Benefit("150.00", Reason.ANNUAL_BONUS)), command);
    }

    @Test
    public void parseCommand_payroll() throws Exception {
        PayrollCommand command = (PayrollCommand) parser.parseCommand(
            PayrollCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new PayrollCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_payslip() throws Exception {
        LocalDate currentDate = LocalDate.now();
        PayslipCommand command = (PayslipCommand) parser.parseCommand(
            PayslipCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new PayslipCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_viewLeave() throws Exception {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        ViewLeaveCommand command = (ViewLeaveCommand) parser.parseCommand(
                ViewLeaveCommand.COMMAND_WORD + " " + PREFIX_ADD_ANNUAL_LEAVE_ON + " " + formattedDate);
        assertEquals(new ViewLeaveCommand(currentDate), command);
    }

    @Test
    public void parseCommand_nextMonth() throws Exception {
        assertTrue(parser.parseCommand(NextMonthCommand.COMMAND_WORD) instanceof NextMonthCommand);
        assertTrue(parser.parseCommand(NextMonthCommand.COMMAND_WORD + " 3") instanceof NextMonthCommand);
    }

    @Test
    public void parseCommand_previousMonth() throws Exception {
        assertTrue(parser.parseCommand(PreviousMonthCommand.COMMAND_WORD) instanceof PreviousMonthCommand);
        assertTrue(parser.parseCommand(PreviousMonthCommand.COMMAND_WORD + " 3") instanceof PreviousMonthCommand);
    }

    @Test
    public void parseCommand_currentMonth() throws Exception {
        assertTrue(parser.parseCommand(CurrentMonthCommand.COMMAND_WORD) instanceof CurrentMonthCommand);
        assertTrue(parser.parseCommand(CurrentMonthCommand.COMMAND_WORD + " 3") instanceof CurrentMonthCommand);
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
