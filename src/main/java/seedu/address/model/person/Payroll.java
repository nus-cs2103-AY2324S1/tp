package seedu.address.model.person;

import java.text.DecimalFormat;

/**
 * Represents an employee's payroll of the month.
 */
public class Payroll {
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    private Salary salary;

    /**
     * Constructs a {@code Payroll}.
     *
     * @param salary This contains the details of the person's salary.
     *               E.g. basic salary, deductions and bonuses.
     */
    public Payroll(Salary salary) {
        this.salary = salary;
    }

    /**
     * Calculates the net salary of a person.
     * @return net salary.
     */
    public String getNetSalaryString() {
        return DECIMAL_FORMAT.format(this.salary.getNetSalary());
    }

    /**
     * Returns the basic salary of a person.
     * @return basic salary.
     */
    public String getBasicSalaryString() {
        return DECIMAL_FORMAT.format(this.salary.getBasicSalary());
    }

    /**
     * Returns the total deductions of a person.
     * @return total deductions.
     */
    public String getTotalDeductionsString() {
        return DECIMAL_FORMAT.format(this.salary.getTotalDeductions());
    }

    /**
     * Returns the total allowances of a person, except annual bonuses.
     * @return total allowances except annual bonuses.
     */
    public String getTotalAllowancesExceptBonusesString() {
        return DECIMAL_FORMAT.format(this.salary.getTotalAllowancesExceptBonuses());
    }

    /**
     * Returns the transport allowances of a person.
     * @return transport allowances.
     */
    public String getTransportAllowancesString() {
        return DECIMAL_FORMAT.format(this.salary.getTransportAllowances());
    }

    /**
     * Returns the annual bonuses of a person.
     * @return annual bonuses.
     */
    public String getAnnualBonusesString() {
        return DECIMAL_FORMAT.format(this.salary.getAnnualBonuses());
    }

    /**
     * Returns the no pay leaves of a person.
     * @return no pay leaves.
     */
    public String getNoPayLeavesString() {
        return DECIMAL_FORMAT.format(this.salary.getNoPayLeaves());
    }

    /**
     * Returns the absences of a person.
     * @return absences.
     */
    public String getAbsencesString() {
        return DECIMAL_FORMAT.format(this.salary.getAbsences());
    }

    /**
     * Returns the employee CPF deductions of a person.
     * @return employee CPF deductions.
     */
    public String getEmployeeCpfDeductionsString() {
        return DECIMAL_FORMAT.format(this.salary.getEmployeeCpfDeductions());
    }

    /**
     * Returns the gross pay of a person.
     * @return gross pay.
     */
    public String getGrossPayString() {
        return DECIMAL_FORMAT.format(this.salary.getGrossPay());
    }

    /**
     * Returns the string representation of the net salary.
     * @return string net salary.
     */
    public String calculatePayrollString() {
        return this.salary.getNetSalaryString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Payroll)) {
            return false;
        }

        Payroll otherDeduction = (Payroll) other;
        return this.salary.equals(otherDeduction.salary);
    }
}
