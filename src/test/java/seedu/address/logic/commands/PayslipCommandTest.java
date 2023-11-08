package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.PaySlipGenerator;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Payroll;
import seedu.address.model.person.Person;

class PayslipCommandTest {

    private final PayslipCommand payslipCommand = new PayslipCommand(Index.fromZeroBased(2));
    private final PayslipCommand payslipCommand2 = new PayslipCommand(Index.fromZeroBased(1000));
    private final PayslipCommand payslipCommand3 = new PayslipCommand(
        new NameContainsKeywordsPredicate(Collections.singletonList("Alice")),
        ParserUtil.stringToDate("06/10/2023"));
    private final PayslipCommand payslipCommand4 = new PayslipCommand(
        new NameContainsKeywordsPredicate(Collections.singletonList("Sponge")));
    private final PayslipCommand payslipCommand5 = new PayslipCommand(
        new NameContainsKeywordsPredicate(Collections.singletonList("Alice")),
        ParserUtil.stringToDate("06/10/2024"));

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PayslipCommand((Index) null));
        assertThrows(NullPointerException.class, () -> new PayslipCommand((NameContainsKeywordsPredicate) null));
    }

    @Test
    public void executeWithIndex() throws Exception {
        // success with index
        Person employeeToGenerate = model.getFilteredPersonList().get(2);
        employeeToGenerate.addPayroll(new Payroll(employeeToGenerate.getSalary()));
        CommandResult commandResult = payslipCommand.execute(model);
        assertEquals(String.format(PayslipCommand.MESSAGE_PAYSLIP_SUCCESS, employeeToGenerate.getName().toString()),
            commandResult.getFeedbackToUser());
        File payslip = new File("payslips/" + PaySlipGenerator.getFileName(employeeToGenerate));
        assertTrue(payslip.exists());
        payslip.delete();
    }

    @Test
    public void execute_invalidArguments_throwsCommandException() {
        // fail with invalid index
        assertThrows(CommandException.class, () -> payslipCommand2.execute(model)); // index out of bounds

        // fail with non-existent name
        assertThrows(CommandException.class, () -> payslipCommand4.execute(model)); // no such person

        // fail with non-existent payroll
        assertThrows(CommandException.class, () -> payslipCommand5.execute(model)); // no such payroll
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(payslipCommand.equals(payslipCommand));

        // same values -> returns true
        PayslipCommand payslipCommandCopy = new PayslipCommand(Index.fromZeroBased(2));
        assertTrue(payslipCommand.equals(payslipCommandCopy));

        // different types -> returns false
        assertFalse(payslipCommand.equals(1));

        // null -> returns false
        assertFalse(payslipCommand.equals(null));

        // different person -> returns false
        assertFalse(payslipCommand.equals(payslipCommand2));
    }

    @Test
    public void toStringTest() {
        String expected = PayslipCommand.class.getCanonicalName()
            + "{index=" + Index.fromZeroBased(2) + "}";
        assertEquals(expected, payslipCommand.toString());
    }
}
