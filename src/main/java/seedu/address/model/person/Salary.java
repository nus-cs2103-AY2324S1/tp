package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Represents a Person's salary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class Salary extends Payment {
    private ArrayList<Deduction> deductions;
    private ArrayList<Benefit> benefits;

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid basic salary.
     */
    public Salary(String salary) {
        super(salary);
    }

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid basic salary.
     * @param deductions A list of valid deductions.
     * @param benefits A list of valid benefits.
     */
    public Salary(String salary, ArrayList<Deduction> deductions, ArrayList<Benefit> benefits) {
        super(salary);
        requireNonNull(deductions);
        requireNonNull(benefits);
        this.deductions = deductions;
        this.benefits = benefits;
    }

    /**
     * Calculates the total deductions of a person.
     * @return total deductions.
     */
    public double getTotalDeductions() {
        DecimalFormat df = new DecimalFormat("0.00");

        if (deductions == null) {
            double res = 0;
            df.format(res);
            return res;
        }

        double total = 0;
        for (Deduction deduction : deductions) {
            total += Double.parseDouble(deduction.value);
        }
        df.format(total);
        return total;
    }

    /**
     * Calculates the total benefits of a person.
     * @return total benefits.
     */
    public double getTotalBenefits() {
        DecimalFormat df = new DecimalFormat("0.00");

        if (benefits == null) {
            double res = 0;
            df.format(res);
            return res;
        }

        double total = 0;
        for (Benefit benefit : benefits) {
            total += Double.parseDouble(benefit.value);
        }
        df.format(total);
        return total;
    }

    /**
     * Calculates the net salary of a person.
     * @return net salary.
     */
    public double getNetSalary() {
        DecimalFormat df = new DecimalFormat("0.00");
        double total = Double.parseDouble(value);
        total -= getTotalDeductions();
        total += getTotalBenefits();
        df.format(total);
        return total;
    }

    /**
     * Returns the string representation of the net salary.
     * @return net salary string.
     */
    public String getNetSalaryString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String basicSalary = "Basic Pay: $" + super.toString();
        String deductions = "Total Deductions: $" + df.format(getTotalDeductions());
        String benefits = "Total Benefits: $" + df.format(getTotalBenefits());
        String netSalary = "Net Salary: $" + df.format(getNetSalary());
        return String.format("%1$-40s %2$-40s\n%3$-40s %4$-40s", netSalary, basicSalary, deductions, benefits);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Salary)) {
            return false;
        }

        // degenerates to Payment if deductions and benefits are null
        if (deductions == null && benefits == null) {
            return super.equals(other);
        }

        Salary otherSalary = (Salary) other;
        if (deductions != null && benefits != null) {
            return value.equals(otherSalary.value)
                    && deductions.equals(otherSalary.deductions)
                    && benefits.equals(otherSalary.benefits);
        } else if (deductions != null) {
            return value.equals(otherSalary.value)
                    && deductions.equals(otherSalary.deductions);
        } else if (benefits != null) {
            return value.equals(otherSalary.value)
                    && benefits.equals(otherSalary.benefits);
        }
        return false;
    }
}
