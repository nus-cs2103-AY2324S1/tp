package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.PaySlipGenerator;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

class PayslipCommandTest {

    private PayslipCommand payslipCommand = new PayslipCommand(Index.fromZeroBased(2));
    private PayslipCommand payslipCommand2 = new PayslipCommand(Index.fromZeroBased(1000));

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PayslipCommand(null));
    }

    @Test
    public void execute() throws Exception {
        // success
        Person employeeToGenerate = model.getFilteredPersonList().get(2);
        CommandResult commandResult = payslipCommand.execute(model);
        assertEquals(String.format(PayslipCommand.MESSAGE_PAYSLIP_SUCCESS, employeeToGenerate.getName().toString()),
            commandResult.getFeedbackToUser());
        File payslip = new File("payslips/" + PaySlipGenerator.getFileName(employeeToGenerate));
        assertTrue(payslip.exists());
        payslip.delete();

        // fail
        assertThrows(CommandException.class, () -> payslipCommand2.execute(model)); // index out of bounds
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
