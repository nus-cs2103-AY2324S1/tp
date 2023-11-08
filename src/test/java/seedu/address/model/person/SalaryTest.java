package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SalaryTest {

    private final Salary salary = new Salary("1500.00");

    private final ArrayList<Benefit> benefits = new ArrayList<>(
        List.of(
            new Benefit("5000.00", Reason.ANNUAL_BONUS),
            new Benefit("500.00", Reason.TRANSPORT_ALLOWANCE)
        )
    );
    private final ArrayList<Deduction> deductions = new ArrayList<>(
        List.of(
            new Deduction("100.00", Reason.NO_PAY_LEAVE),
            new Deduction("120.00", Reason.ABSENCE)
        )
    );

    private final Salary salaryWithNoBenefitAndDeduction = new Salary("1500.00", null, null);

    private final Salary salaryWithBenefit = new Salary("1500.00", null, benefits);
    private final Salary salaryWithDeduction = new Salary("1500.00", deductions, null);
    private final Salary salaryWithBenefitAndDeduction = new Salary("1500.00", deductions, benefits);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidSalary = "1500";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValid(null));

        //valid salary
        assertTrue(Salary.isValid("0.00"));
        assertTrue(Salary.isValid("10.00"));
        assertTrue(Salary.isValid("100.00"));
        assertTrue(Salary.isValid("10000.00"));
        assertTrue(Salary.isValid("100000.00"));

        //invalid salary
        assertFalse(Salary.isValid("")); //empty value
        assertFalse(Salary.isValid(" ")); //space only
        assertFalse(Salary.isValid("*")); //only non-alphanumeric characters
        assertFalse(Salary.isValid("8+1500.00")); //contain non-alphanumeric characters
        assertFalse(Salary.isValid("8000")); //no decimal point
        assertFalse(Salary.isValid("1500.0")); //1 decimal point
        assertFalse(Salary.isValid("1500.000")); //3 decimal points
    }

    @Test
    public void getDeductionsString_nullDeductions_returnNil() {
        assertTrue(salaryWithNoBenefitAndDeduction.getDeductionsString().equals("NIL"));
    }

    @Test
    public void getBenefitsString_nullBenefits_returnNil() {
        assertTrue(salaryWithNoBenefitAndDeduction.getBenefitsString().equals("NIL"));
    }

    @Test
    public void addDeduction_nullDeductions_returnSingleElementArrayList() {
        salaryWithNoBenefitAndDeduction.addDeduction(new Deduction("100.00", Reason.NO_PAY_LEAVE));
        assertTrue(salaryWithNoBenefitAndDeduction.getDeductions().size() == 1);
        assertTrue(salaryWithNoBenefitAndDeduction.getDeductions().get(0).equals(new Deduction("100.00", Reason.NO_PAY_LEAVE)));
    }

    @Test
    public void addBenefit_nullDeductions_returnSingleElementArrayList() {
        salaryWithNoBenefitAndDeduction.addBenefit(new Benefit("100.00", Reason.ANNUAL_BONUS));
        assertTrue(salaryWithNoBenefitAndDeduction.getBenefits().size() == 1);
        assertTrue(salaryWithNoBenefitAndDeduction.getBenefits().get(0).equals(new Benefit("100.00", Reason.ANNUAL_BONUS)));
    }

    @Test
    public void getTotalDeductions() {
        // null deductions
        assertTrue(salary.getTotalDeductions() == 0.0);
        assertTrue(salaryWithBenefit.getTotalDeductions() == 0.0);

        // with deductions
        assertTrue(salaryWithDeduction.getTotalDeductions() == 220.0);
        assertTrue(salaryWithBenefitAndDeduction.getTotalDeductions() == 220.0);
    }

    @Test
    public void getTotalBenefits() {
        // null benefits
        assertTrue(salary.getTotalBenefits() == 0.0);
        assertTrue(salaryWithDeduction.getTotalBenefits() == 0.0);

        // with benefits
        assertTrue(salaryWithBenefit.getTotalBenefits() == 5500.0);
        assertTrue(salaryWithBenefitAndDeduction.getTotalBenefits() == 5500.0);
    }

    @Test
    public void getTotalAllowancesExceptBonuses() {
        assertTrue(salaryWithBenefit.getTotalAllowancesExceptBonuses() == 500.0);
        assertTrue(salaryWithBenefitAndDeduction.getTotalAllowancesExceptBonuses() == 500.0);
        assertTrue(salaryWithDeduction.getTotalAllowancesExceptBonuses() == 0.0);
        assertTrue(salaryWithNoBenefitAndDeduction.getTotalAllowancesExceptBonuses() == 0.0);
    }

    @Test
    public void getTransportAllowances() {
        assertTrue(salaryWithBenefit.getTransportAllowances() == 500.0);
        assertTrue(salaryWithBenefitAndDeduction.getTransportAllowances() == 500.0);
        assertTrue(salaryWithDeduction.getTransportAllowances() == 0.0);
        assertTrue(salaryWithNoBenefitAndDeduction.getTransportAllowances() == 0.0);
    }

    @Test
    public void getAnnualBonuses() {
        assertTrue(salaryWithBenefit.getAnnualBonuses() == 5000.0);
        assertTrue(salaryWithBenefitAndDeduction.getAnnualBonuses() == 5000.0);
        assertTrue(salaryWithDeduction.getAnnualBonuses() == 0.0);
        assertTrue(salaryWithNoBenefitAndDeduction.getAnnualBonuses() == 0.0);
    }

    @Test
    public void getNoPayLeaves() {
        assertTrue(salaryWithBenefit.getNoPayLeaves() == 0.0);
        assertTrue(salaryWithBenefitAndDeduction.getNoPayLeaves() == 100.0);
        assertTrue(salaryWithDeduction.getNoPayLeaves() == 100.0);
        assertTrue(salaryWithNoBenefitAndDeduction.getNoPayLeaves() == 0.0);
    }

    @Test
    public void getAbsences() {
        assertTrue(salaryWithBenefit.getAbsences() == 0.0);
        assertTrue(salaryWithBenefitAndDeduction.getAbsences() == 120.0);
        assertTrue(salaryWithDeduction.getAbsences() == 120.0);
        assertTrue(salaryWithNoBenefitAndDeduction.getAbsences() == 0.0);
    }

    @Test
    public void getEmployeeCpfDeductions() {
        assertTrue(salaryWithBenefit.getEmployeeCpfDeductions() == 0.0);
        assertTrue(salaryWithBenefitAndDeduction.getEmployeeCpfDeductions() == 0.0);
        assertTrue(salaryWithDeduction.getEmployeeCpfDeductions() == 0.0);
        assertTrue(salaryWithNoBenefitAndDeduction.getEmployeeCpfDeductions() == 0.0);
    }

    @Test
    public void getNetSalary() {
        // null benefits and deductions
        assertTrue(salary.getNetSalary() == 1500.0);

        // with benefits
        assertTrue(salaryWithBenefit.getNetSalary() == 7000.0);

        // with deductions
        assertTrue(salaryWithDeduction.getNetSalary() == 1280.0);

        // with benefits and deductions
        assertTrue(salaryWithBenefitAndDeduction.getNetSalary() == 6780.0);
    }

    @Test
    public void getNetSalaryString() {
        DecimalFormat df = new DecimalFormat("0.00");
        // null benefits and deductions
        assertTrue(salary.getNetSalaryString().equals(String.format("%1$-40s %2$-40s\n%3$-40s %4$-40s",
            "Net Salary: $" + df.format(1500.0),
            "Basic Pay: $" + df.format(1500.0),
            "Total Deductions: $" + df.format(0.0),
            "Total Benefits: $" + df.format(0.0))));

        // with benefits
        assertTrue(salaryWithBenefit.getNetSalaryString().equals(String.format("%1$-40s %2$-40s\n%3$-40s %4$-40s",
            "Net Salary: $" + df.format(7000.0),
            "Basic Pay: $" + df.format(1500.0),
            "Total Deductions: $" + df.format(0.0),
            "Total Benefits: $" + df.format(5500.0))));

        // with deductions
        assertTrue(salaryWithDeduction.getNetSalaryString().equals(String.format("%1$-40s %2$-40s\n%3$-40s %4$-40s",
            "Net Salary: $" + df.format(1280.0),
            "Basic Pay: $" + df.format(1500.0),
            "Total Deductions: $" + df.format(220.0),
            "Total Benefits: $" + df.format(0.0))));

        // with benefits and deductions
        assertTrue(salaryWithBenefitAndDeduction.getNetSalaryString().equals(
            String.format("%1$-40s %2$-40s\n%3$-40s %4$-40s",
            "Net Salary: $" + df.format(6780.0),
            "Basic Pay: $" + df.format(1500.0),
            "Total Deductions: $" + df.format(220.0),
            "Total Benefits: $" + df.format(5500.0))));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(salary.equals(new Salary("1500.00")));

        // same object -> returns true
        assertTrue(salary.equals(salary));
        assertTrue(salaryWithBenefit.equals(salaryWithBenefit));
        assertTrue(salaryWithDeduction.equals(salaryWithDeduction));
        assertTrue(salaryWithBenefitAndDeduction.equals(salaryWithBenefitAndDeduction));

        // null -> returns false
        assertFalse(salary.equals(null));

        // different types -> returns false
        assertFalse(salary.equals(5.0f));

        // different values -> returns false
        assertFalse(salary.equals(new Salary("1500.01")));
        assertFalse(salary.equals(salaryWithBenefit));
        assertFalse(salary.equals(salaryWithDeduction));
        assertFalse(salary.equals(salaryWithBenefitAndDeduction));
        assertFalse(salaryWithBenefit.equals(salaryWithDeduction));
        assertFalse(salaryWithBenefit.equals(salaryWithBenefitAndDeduction));
        assertFalse(salaryWithDeduction.equals(salaryWithBenefitAndDeduction));
    }
}
