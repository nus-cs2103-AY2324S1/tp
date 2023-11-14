package seedu.address.model.person;

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
        this.deductions = new ArrayList<>();
        this.benefits = new ArrayList<>();
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
        this.deductions = deductions;
        this.benefits = benefits;
    }

    /**
     * Calculates the total deductions of a person.
     * @return total deductions.
     */
    public double getTotalDeductions() {
        if (deductions == null) {
            return 0.0;
        }

        double total = 0;
        for (Deduction deduction : deductions) {
            total += Double.parseDouble(deduction.value);
        }
        return total;
    }

    /**
     * Calculates the total benefits of a person.
     * @return total benefits.
     */
    public double getTotalBenefits() {
        if (benefits == null) {
            return 0.0;
        }

        double total = 0;
        for (Benefit benefit : benefits) {
            total += Double.parseDouble(benefit.value);
        }
        return total;
    }

    /**
     * Calculates the net salary of a person.
     * @return net salary.
     */
    public double getNetSalary() {
        double total = Double.parseDouble(value);
        total -= getTotalDeductions();
        total += getTotalBenefits();
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

    /**
     * Adds a deduction to this salary.
     * @param d The deduction to be added.
     */
    public void addDeduction(Deduction d) {
        if (deductions == null) {
            deductions = new ArrayList<>();
        }
        deductions.add(d);
    }

    /**
     * Returns the string representation of the deductions.
     * @return deductions string.
     */
    public String getDeductionsString() {
        if (deductions == null) {
            return "NIL";
        }

        String deductionsString = "";
        int i = 1;
        for (Deduction deduction : deductions) {
            deductionsString += i + ". " + deduction.toString() + "\n";
            i++;
        }
        return deductionsString;
    }

    /**
     * Adds a benefit to this salary.
     * @param b The benefit to be added.
     */
    public void addBenefit(Benefit b) {
        if (benefits == null) {
            benefits = new ArrayList<>();
        }
        benefits.add(b);
    }

    /**
     * Returns the string representation of the benefits.
     * @return benefits string.
     */
    public String getBenefitsString() {
        if (benefits == null) {
            return "NIL";
        }

        String benefitsString = "";
        int i = 1;
        for (Benefit benefit : benefits) {
            benefitsString += i + ". " + benefit.toString() + "\n";
            i++;
        }
        return benefitsString;
    }

    /**
     * Returns the basic salary of a person.
     * @return basic salary.
     */
    public double getBasicSalary() {
        return Double.parseDouble(value);
    }

    /**
     * Returns the total allowances of a person, except annual bonuses.
     * @return total allowances except annual bonuses.
     */
    public double getTotalAllowancesExceptBonuses() {
        if (benefits == null) {
            return 0.0;
        }

        double total = 0;
        for (Benefit benefit : benefits) {
            if (!benefit.getReason().equals(Reason.ANNUAL_BONUS)) {
                total += Double.parseDouble(benefit.value);
            }
        }
        return total;
    }

    /**
     * Returns the transport allowances of a person.
     * @return transport allowances.
     */
    public double getTransportAllowances() {
        if (benefits == null) {
            return 0.0;
        }

        double total = 0;
        for (Benefit benefit : benefits) {
            if (benefit.getReason().equals(Reason.TRANSPORT_ALLOWANCE)) {
                total += Double.parseDouble(benefit.value);
            }
        }
        return total;
    }

    /**
     * Returns the annual bonuses of a person.
     * @return annual bonuses.
     */
    public double getAnnualBonuses() {
        if (benefits == null) {
            return 0.0;
        }

        double total = 0;
        for (Benefit benefit : benefits) {
            if (benefit.getReason().equals(Reason.ANNUAL_BONUS)) {
                total += Double.parseDouble(benefit.value);
            }
        }
        return total;
    }

    /**
     * Returns the no pay leaves of a person.
     * @return no pay leaves.
     */
    public double getNoPayLeaves() {
        if (deductions == null) {
            return 0.0;
        }

        double total = 0;
        for (Deduction deduction : deductions) {
            if (deduction.getReason().equals(Reason.NO_PAY_LEAVE)) {
                total += Double.parseDouble(deduction.value);
            }
        }
        return total;
    }

    /**
     * Returns the absences of a person.
     * @return absences.
     */
    public double getAbsences() {
        if (deductions == null) {
            return 0.0;
        }

        double total = 0;
        for (Deduction deduction : deductions) {
            if (deduction.getReason().equals(Reason.ABSENCE)) {
                total += Double.parseDouble(deduction.value);
            }
        }
        return total;
    }

    /**
     * Returns the employee CPF deductions of a person.
     * @return employee CPF deductions.
     */
    public double getEmployeeCpfDeductions() {
        if (deductions == null) {
            return 0.0;
        }

        double total = 0;
        for (Deduction deduction : deductions) {
            if (deduction.getReason().equals(Reason.EMPLOYEE_CPF_DEDUCTION)) {
                total += Double.parseDouble(deduction.value);
            }
        }
        return total;
    }

    /**
     * Returns the gross pay of a person.
     * @return gross pay.
     */
    public double getGrossPay() {
        return getBasicSalary() + getTotalAllowancesExceptBonuses();
    }

    public ArrayList<Deduction> getDeductions() {
        return this.deductions;
    }

    public ArrayList<Benefit> getBenefits() {
        return this.benefits;
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

        Salary otherSalary = (Salary) other;

        // degenerates to Payment if deductions and benefits are null
        if (deductions == null && benefits == null && otherSalary.deductions == null
                && otherSalary.benefits == null) {
            return super.equals(other);
        }

        if (deductions != null && benefits != null && otherSalary.deductions != null
                && otherSalary.benefits != null) {
            return value.equals(otherSalary.value)
                    && deductions.equals(otherSalary.deductions)
                    && benefits.equals(otherSalary.benefits);
        }

        if (deductions != null && benefits == null && otherSalary.deductions != null
                && otherSalary.benefits == null) {
            return value.equals(otherSalary.value)
                    && deductions.equals(otherSalary.deductions);
        }

        if (deductions == null && benefits != null && otherSalary.deductions == null
                && otherSalary.benefits != null) {
            return value.equals(otherSalary.value)
                    && benefits.equals(otherSalary.benefits);
        }

        return false;
    }
}
