package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;

public class PayrollTest {

    private final Salary validSalary = new Salary("2000.00");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Payroll(validSalary,
                        CommandTestUtil.VALID_DATE, null, null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "5000";
        assertThrows(IllegalArgumentException.class, () -> new Payroll(new Salary(invalidSalary)));
    }

    @Test
    public void constructor_invalidDate_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> new Payroll(validSalary,
                "today", "30/11/2023", "05/12/2023"));
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
    public void getTransportAllowancesString_addingTransportAllowance_addSuccessful() {
        Payroll payroll = new Payroll(new Salary("800.00"));
        payroll.addBenefit(new Benefit("200.00", Reason.TRANSPORT_ALLOWANCE));
        assertTrue(payroll.getTransportAllowancesString().equals("200.00"));
    }

    @Test
    public void getNoPayLeavesString_addingNoPayLeaves_addSuccessful() {
        Payroll payroll = new Payroll(new Salary("20000.00"));
        payroll.addDeduction(new Deduction("200.00", Reason.NO_PAY_LEAVE));
        assertTrue(payroll.getNoPayLeavesString().equals("200.00"));
    }

    @Test
    public void getAbsencesString_addingAbsences_addSuccessful() {
        Payroll payroll = new Payroll(new Salary("3000.00"));
        payroll.addDeduction(new Deduction("200.00", Reason.ABSENCE));
        assertTrue(payroll.getAbsencesString().equals("200.00"));
    }

    @Test
    public void getPaymentDate_checkPaymentDate_samePaymentDate() {
        LocalDate localDate = LocalDate.parse("2023-11-30");
        Payroll payroll = new Payroll((new Salary("200.00")));
        assertTrue(payroll.getPaymentDate().equals(localDate));
    }

    @Test
    public void equals() {
        Payroll payroll1 = new Payroll(validSalary);
        Payroll payroll2 = new Payroll(validSalary, "01/11/2023", "30/11/2023", "05/12/2023");

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

        // different start date -> return false
        assertFalse(payroll2.equals(new Payroll(validSalary,
                "02/11/2023", "30/11/2023", "05/12/2023")));

        // different end date -> return false
        assertFalse(payroll2.equals(new Payroll(validSalary,
                "01/11/2023", "29/11/2023", "05/12/2023")));

        // different payment date -> return false
        assertFalse(payroll2.equals(new Payroll(validSalary,
                "01/11/2023", "30/11/2023", "06/12/2023")));
    }
}
