package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Deduction;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reason;
import seedu.address.model.person.payroll.PayrollStorage;

class DeductCommandTest {

    private final Index index = Index.fromZeroBased(1);
    private final Index outOfBoundIndex = Index.fromZeroBased(1000);
    private final NameContainsKeywordsPredicate name = new NameContainsKeywordsPredicate(
        Collections.singletonList("Alice"));
    private final NameContainsKeywordsPredicate nonExistentName = new NameContainsKeywordsPredicate(
        Collections.singletonList("NonExistent"));
    private final Deduction deduction = new Deduction("200.00", Reason.NO_PAY_LEAVE);
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeductCommand((Index) null, deduction));
        assertThrows(NullPointerException.class, () -> new DeductCommand((Index) null, deduction));
    }

    @Test
    void executeByIndexSuccess() throws Exception {
        // success with index
        List<Person> lastShownList = model.getFilteredPersonList();
        Person employeeToCalculate = lastShownList.get(index.getZeroBased());
        PayrollStorage payrollStorage = employeeToCalculate.getPayrollStorage();

        DeductCommand deductCommand = new DeductCommand(index, deduction);
        CommandResult commandResult = deductCommand.execute(model);
        assertEquals(String.format(DeductCommand.MESSAGE_ARGUMENTS,
            payrollStorage.getLatestPayroll().getDeductionsString()), commandResult.getFeedbackToUser());
    }

    @Test
    public void executeByIndex_outOfBoundsIndex_throwsCommandException() {
        // fail with out-of-bound index
        DeductCommand deductCommand = new DeductCommand(outOfBoundIndex, deduction);
        assertCommandFailure(deductCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeByNameSuccess() throws CommandException {
        // success with name
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Integer> indexes = model.getIndexOfFilteredPersonList(this.name);
        Person employeeToCalculate = lastShownList.get(indexes.get(0) - 1);
        PayrollStorage payrollStorage = employeeToCalculate.getPayrollStorage();

        DeductCommand deductCommand = new DeductCommand(name, deduction);
        CommandResult commandResult = deductCommand.execute(model);
        assertEquals(String.format(DeductCommand.MESSAGE_ARGUMENTS,
            payrollStorage.getLatestPayroll().getDeductionsString()), commandResult.getFeedbackToUser());
    }

    @Test
    public void executeByName_nonExistentName_throwsCommandException() {
        // fail with name
        DeductCommand deductCommand = new DeductCommand(nonExistentName, deduction);
        assertCommandFailure(deductCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(new DeductCommand(index, deduction), new DeductCommand(index, deduction));
        assertEquals(new DeductCommand(name, deduction), new DeductCommand(name, deduction));

        // same values -> returns true
        DeductCommand deductCommandCopy = new DeductCommand(index, deduction);
        assertEquals(new DeductCommand(index, deduction), deductCommandCopy);
        DeductCommand deductCommandCopy2 = new DeductCommand(name, deduction);
        assertEquals(new DeductCommand(name, deduction), deductCommandCopy2);

        // different types -> returns false
        assertFalse(new DeductCommand(index, deduction).equals(""));
        assertFalse(new DeductCommand(name, deduction).equals(""));

        // null -> returns false
        assertFalse(new DeductCommand(index, deduction).equals(null));

        // different values -> returns false
        assertFalse(new DeductCommand(index, deduction).equals(new DeductCommand(index,
            new Deduction("100.00", Reason.NO_PAY_LEAVE))));
        assertFalse(new DeductCommand(name, deduction).equals(new DeductCommand(name,
            new Deduction("100.00", Reason.NO_PAY_LEAVE))));
        assertFalse(new DeductCommand(index, deduction).equals(new DeductCommand(outOfBoundIndex, deduction)));
        assertFalse(new DeductCommand(name, deduction).equals(new DeductCommand(nonExistentName, deduction)));
    }

    @Test
    public void testToString() {
        assertEquals(DeductCommand.class.getCanonicalName()
            + "{index=" + Index.fromZeroBased(1) + "," + " deduction=" + deduction + "}",
            new DeductCommand(index, deduction).toString());
        assertEquals(DeductCommand.class.getCanonicalName()
            + "{name=" + name + "," + " deduction=" + deduction + "}", new DeductCommand(name, deduction).toString());
    }
}
