package seedu.address.model.person;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * Represents an employee's payroll of the month.
 */
public class Payroll {
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Salary salary;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate paymentDate;

    /**
     * Constructs a {@code Payroll}.
     *
     * @param salary This contains the details of the person's salary.
     *               E.g. basic salary, deductions and bonuses.
     */
    public Payroll(Salary salary) {
        this.salary = salary;
        LocalDate localDate = LocalDate.now();
        this.startDate = localDate.withDayOfMonth(1);
        this.endDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
        this.paymentDate = localDate.plusMonths(1).withDayOfMonth(5);
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
     * Returns the start date of this payroll.
     * @return start date
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the string representation of the start date of this payroll.
     * @return string representation of the start date
     */
    public String getStartDateString() {
        return this.startDate.format(DATE_TIME_FORMATTER);
    }

    /**
     * Returns the end date of this payroll.
     * @return end date
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Returns the string representation of the end date of this payroll.
     * @return string representation of the end date
     */
    public String getEndDateString() {
        return this.endDate.format(DATE_TIME_FORMATTER);
    }

    /**
     * Returns the payment date.
     * @return payment date.
     */
    public LocalDate getPaymentDate() {
        return this.endDate;
    }

    /**
     * Returns the string representation of the payment date.
     * @return string representation of the payment date
     */
    public String getPaymentDateString() {
        return this.paymentDate.format(DATE_TIME_FORMATTER);
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
        return this.salary.equals(otherDeduction.salary) && this.startDate.equals(otherDeduction.startDate)
                && this.endDate.equals(otherDeduction.endDate) && this.paymentDate.equals(otherDeduction.paymentDate);
    }
}
