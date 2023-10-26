package seedu.address.model.person;

/**
 * Represents an employee's payroll of the month.
 */
public class Payroll {
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
    public double calculatePayroll() {
        return this.salary.getNetSalary();
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
