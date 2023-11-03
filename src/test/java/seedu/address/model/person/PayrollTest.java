package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;

public class PayrollTest {

    private final Salary VALID_SALARY = new Salary("2000.00");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Payroll(null));
        assertThrows(NullPointerException.class,
                () -> new Payroll(VALID_SALARY,
                        CommandTestUtil.VALID_DATE, null, null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "5000";
        assertThrows(IllegalArgumentException.class, () -> new Payroll(new Salary(invalidSalary)));
    }

    @Test
    public void constructor_invalidDate_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class,
                () -> new Payroll(VALID_SALARY, "today", "30/11/2023", "05/12/2023"));
    }

    @Test
    public void addBenefit_addingBenefit_addSuccessful() {
        Payroll payroll = new Payroll(new Salary("2000.00"));
        payroll.addBenefit(new Benefit("200.00", Reason.ANNUAL_BONUS));
        assertTrue(payroll.getSalary().getTotalBenefits() == 200.00);
    }

    @Test
    public void addDeduction_addingDeduction_addSuccessful() {
        Payroll payroll = new Payroll(new Salary("2000.00"));
        payroll.addDeduction(new Deduction("200.00", Reason.ABSENCE));
        assertTrue(payroll.getSalary().getTotalDeductions() == 200.00);
    }

    @Test
    public void equals() {
        Payroll payroll1 = new Payroll(VALID_SALARY);
        Payroll payroll2 = new Payroll(VALID_SALARY, "01/11/2023", "30/11/2023", "05/12/2023");

        // same values -> returns true
        assertTrue(payroll1.equals(new Payroll(new Salary("2000.00"))));
        assertTrue(payroll2.equals(new Payroll(new Salary("2000.00"),
                "01/11/2023", "30/11/2023", "05/12/2023")));

        // same object -> returns true
        assertTrue(payroll1.equals(payroll1));
        assertTrue(payroll2.equals(payroll2));

        // null -> returns false
        assertFalse(payroll1.equals(null));
        assertFalse(payroll2.equals(null));

        // different types -> return false
        assertFalse(payroll1.equals(5.0f));
        assertFalse(payroll2.equals(5.0f));

        // different salary -> return false
        assertFalse(payroll1.equals(new Payroll(new Salary("200.00"))));

        // different date -> return false
        assertFalse(payroll2.equals(new Payroll(VALID_SALARY,
                "02/11/2023", "30/11/2023", "05/12/2023")));
    }
}
