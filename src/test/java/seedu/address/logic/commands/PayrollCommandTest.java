package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookPayroll;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Payroll;
import seedu.address.model.person.Person;
import seedu.address.model.person.Salary;

class PayrollCommandTest {

    private Index index = Index.fromZeroBased(0);
    private NameContainsKeywordsPredicate name = new NameContainsKeywordsPredicate(Arrays.asList("Alice"));
    private NameContainsKeywordsPredicate name2 = new NameContainsKeywordsPredicate(Arrays.asList("George"));
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model model2 = new ModelManager(getTypicalAddressBookPayroll(), new UserPrefs());
    private Salary salary = new Salary("2000.00");

    @Test
    public void constructor_nullModel_throwsNullPointerException() {
        Index index = null;
        NameContainsKeywordsPredicate name = null;
        // Empty Index
        assertThrows(NullPointerException.class, () -> new PayrollCommand(index));

        // Empty Name
        assertThrows(NullPointerException.class, () -> new PayrollCommand(name));
    }

    @Test
    public void executeByIndex_outOfBoundIndex_throwsCommandException() {
        Index index = Index.fromZeroBased(99);
        PayrollCommand payrollCommand = new PayrollCommand(index);
        assertCommandFailure(payrollCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeByIndex_calculatePayroll_calculationSuccess() throws CommandException {
        List<Person> lastFilteredList = model.getFilteredPersonList();
        Person personToCalculate = lastFilteredList.get(index.getZeroBased());
        Payroll latestPayroll = personToCalculate.getLatestPayroll();

        PayrollCommand payrollCommand = new PayrollCommand(index);
        CommandResult commandResult = payrollCommand.execute(model);
        assertTrue(String.format(PayrollCommand.MESSAGE_ARGUMENTS,
                latestPayroll.calculatePayrollString()).equals(commandResult.getFeedbackToUser()));
    }

    @Test
    public void executeByIndex_differentStartDate_calculationSuccess() throws CommandException {
        Payroll payroll = new Payroll(salary,
                "01/12/2023", "01/01/2023", "05/01/2023");
        List<Person> lastFilteredList = model2.getFilteredPersonList();
        Person personToCalculate = lastFilteredList.get(index.getZeroBased());
        Payroll latestPayroll = personToCalculate.getLatestPayroll();
        personToCalculate.addPayroll(payroll);

        PayrollCommand payrollCommand = new PayrollCommand(index);
        CommandResult commandResult = payrollCommand.execute(model2);
        assertTrue(String.format(PayrollCommand.MESSAGE_ARGUMENTS,
                latestPayroll.calculatePayrollString()).equals(commandResult.getFeedbackToUser()));
    }

    @Test
    public void executeByName_nonExistentName_throwsCommandException() {
        NameContainsKeywordsPredicate nonExistentName = new NameContainsKeywordsPredicate(Arrays.asList("Boo"));
        PayrollCommand payrollCommand = new PayrollCommand(nonExistentName);
        assertCommandFailure(payrollCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

    @Test
    public void executeByName_calculatePayroll_calculationSuccess() throws CommandException {
        List<Person> lastFilteredList = model.getFilteredPersonList();
        List<Integer> indexes = model.getIndexOfFilteredPersonList(name);
        Person personToCalculate = lastFilteredList.get(indexes.get(0) - 1);
        Payroll latestPayroll = personToCalculate.getLatestPayroll();

        PayrollCommand payrollCommand = new PayrollCommand(name);
        CommandResult commandResult = payrollCommand.execute(model);
        assertTrue(String.format(PayrollCommand.MESSAGE_ARGUMENTS,
                latestPayroll.calculatePayrollString()).equals(commandResult.getFeedbackToUser()));
    }

    @Test
    public void executeByName_differentStartDate_calculationSuccess() throws CommandException {
        Payroll payroll = new Payroll(salary,
                "01/12/2023", "01/01/2023", "05/01/2023");
        List<Person> lastFilteredList = model2.getFilteredPersonList();
        List<Integer> indexes = model2.getIndexOfFilteredPersonList(name2);
        Person personToCalculate = lastFilteredList.get(indexes.get(0) - 1);
        Payroll latestPayroll = personToCalculate.getLatestPayroll();
        personToCalculate.addPayroll(payroll);

        PayrollCommand payrollCommand = new PayrollCommand(name2);
        CommandResult commandResult = payrollCommand.execute(model2);
        assertTrue(String.format(PayrollCommand.MESSAGE_ARGUMENTS,
                latestPayroll.calculatePayrollString()).equals(commandResult.getFeedbackToUser()));
    }

    @Test
    public void equals() {
        PayrollCommand payrollCommand1 = new PayrollCommand(index);
        PayrollCommand payrollCommand2 = new PayrollCommand(name);

        // Same object -> returns true
        assertTrue(payrollCommand1.equals(payrollCommand1));
        assertTrue(payrollCommand2.equals(payrollCommand2));

        // Same values -> returns true
        assertTrue(payrollCommand1.equals(new PayrollCommand(index)));
        assertTrue(payrollCommand2.equals(new PayrollCommand(name)));

        // null -> returns false
        assertFalse(payrollCommand1.equals(null));
        assertFalse(payrollCommand2.equals(null));

        // one holds index and the other one holds name -> returns false
        assertFalse(payrollCommand1.equals(payrollCommand2));
        assertFalse(payrollCommand2.equals(payrollCommand1));

        // different types -> returns false
        assertFalse(payrollCommand1.equals(5.0f));
        assertFalse(payrollCommand2.equals(5.0f));

        // different index -> returns false
        assertFalse(payrollCommand1.equals(new PayrollCommand(Index.fromZeroBased(1))));

        //different name -> returns false
        assertFalse(payrollCommand2.equals(new NameContainsKeywordsPredicate(Arrays.asList("John"))));
    }
}
