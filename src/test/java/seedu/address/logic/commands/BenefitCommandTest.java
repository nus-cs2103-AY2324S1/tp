package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Benefit;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reason;
import seedu.address.model.person.payroll.PayrollStorage;

public class BenefitCommandTest {

    private final Index index = Index.fromZeroBased(1);
    private final Index outOfBoundIndex = Index.fromZeroBased(1000);
    private final NameContainsKeywordsPredicate name = new NameContainsKeywordsPredicate(
        Collections.singletonList("Alice"));
    private final NameContainsKeywordsPredicate nonExistentName = new NameContainsKeywordsPredicate(
        Collections.singletonList("NonExistent"));
    private final Benefit benefit = new Benefit("200.00", Reason.TRANSPORT_ALLOWANCE);
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void executeByIndexSuccess() throws Exception {
        // success with index
        List<Person> lastShownList = model.getFilteredPersonList();
        Person employeeToCalculate = lastShownList.get(index.getZeroBased());
        PayrollStorage payrollStorage = employeeToCalculate.getPayrollStorage();

        BenefitCommand benefitCommand = new BenefitCommand(index, benefit);
        CommandResult commandResult = benefitCommand.execute(model);
        assertEquals(String.format(BenefitCommand.MESSAGE_ARGUMENTS,
            payrollStorage.getLatestPayroll().getBenefitsString()), commandResult.getFeedbackToUser());
    }

    @Test
    public void executeByIndex_outOfBoundsIndex_throwsCommandException() {
        // fail with out-of-bound index
        BenefitCommand benefitCommand = new BenefitCommand(outOfBoundIndex, benefit);
        assertCommandFailure(benefitCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeByNameSuccess() throws Exception {
        // success with name
        List<Person> lastShownList = model.getFilteredPersonList();
        Person employeeToCalculate = lastShownList.get(index.getZeroBased());
        PayrollStorage payrollStorage = employeeToCalculate.getPayrollStorage();

        BenefitCommand benefitCommand = new BenefitCommand(name, benefit);
        CommandResult commandResult = benefitCommand.execute(model);
        assertEquals(String.format(BenefitCommand.MESSAGE_ARGUMENTS,
            payrollStorage.getLatestPayroll().getBenefitsString()), commandResult.getFeedbackToUser());
    }

    @Test
    public void executeByName_nonExistentName_throwsCommandException() {
        // fail with non-existent name
        BenefitCommand benefitCommand = new BenefitCommand(nonExistentName, benefit);
        assertCommandFailure(benefitCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(new BenefitCommand(index, benefit), new BenefitCommand(index, benefit));
        assertEquals(new BenefitCommand(name, benefit), new BenefitCommand(name, benefit));

        // same values -> returns true
        BenefitCommand benefitCommandCopy = new BenefitCommand(index, benefit);
        assertEquals(new BenefitCommand(index, benefit), benefitCommandCopy);
        BenefitCommand benefitCommandCopy2 = new BenefitCommand(name, benefit);
        assertEquals(new BenefitCommand(name, benefit), benefitCommandCopy2);

        // different types -> returns false
        assertFalse(new BenefitCommand(index, benefit).equals(""));
        assertFalse(new BenefitCommand(name, benefit).equals(""));

        // null -> returns false
        assertFalse(new BenefitCommand(index, benefit).equals(null));

        // different values -> returns false
        assertFalse(new BenefitCommand(index, benefit).equals(new BenefitCommand(index,
            new Benefit("1000.00", Reason.ANNUAL_BONUS))));
        assertFalse(new BenefitCommand(name, benefit).equals(new BenefitCommand(name,
            new Benefit("1000.00", Reason.ANNUAL_BONUS))));
        assertFalse(new BenefitCommand(index, benefit).equals(new BenefitCommand(outOfBoundIndex, benefit)));
        assertFalse(new BenefitCommand(name, benefit).equals(new BenefitCommand(nonExistentName, benefit)));
    }

    @Test
    public void toStringTest() {
        assertEquals(BenefitCommand.class.getCanonicalName()
                + "{index=" + Index.fromZeroBased(1) + "," + " benefit=" + benefit + "}",
            new BenefitCommand(index, benefit).toString());
        assertEquals(BenefitCommand.class.getCanonicalName()
            + "{name=" + name + "," + " benefit=" + benefit + "}", new BenefitCommand(name, benefit).toString());
    }
}
