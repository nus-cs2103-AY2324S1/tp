package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.Benefit;
import seedu.address.model.person.Deduction;
import seedu.address.model.person.Payroll;
import seedu.address.model.person.Salary;

/**
 * Jackson-friendly version of {@link Payroll}.
 */
public class JsonAdaptedPayroll {

    private final String salary;
    private final String startDate;
    private final String endDate;
    private final String paymentDate;
    private final List<JsonAdaptedBenefit> benefits = new ArrayList<>();
    private final List<JsonAdaptedDeduction> deductions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPayroll} with the given payroll details.
     */
    @JsonCreator
    public JsonAdaptedPayroll(@JsonProperty("salary") String salary, @JsonProperty("startDate") String startDate,
            @JsonProperty("endDate") String endDate, @JsonProperty("paymentDate") String paymentDate,
            @JsonProperty("benefits") List<JsonAdaptedBenefit> benefits,
            @JsonProperty("deductions") List<JsonAdaptedDeduction> deductions) {
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentDate = paymentDate;
        this.benefits.addAll(benefits);
        this.deductions.addAll(deductions);
    }

    /**
     * Converts a given {@code Payroll} into this class for Jackson use.
     */
    public JsonAdaptedPayroll(Payroll payroll) {
        salary = payroll.getBasicSalaryString();
        startDate = payroll.getStartDateString();
        endDate = payroll.getEndDateString();
        paymentDate = payroll.getPaymentDateString();
        benefits.addAll(payroll.getSalary()
                .getBenefits()
                .stream()
                .map(JsonAdaptedBenefit::new)
                .collect(Collectors.toList()));
        deductions.addAll(payroll.getSalary()
                .getDeductions()
                .stream()
                .map(JsonAdaptedDeduction::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted payroll object into the model's {@code Payroll} object.
     */
    public Payroll toModelType() {
        final ArrayList<Benefit> personBenefits = new ArrayList<>();
        final ArrayList<Deduction> personDeductions = new ArrayList<>();

        for (JsonAdaptedBenefit benefit: benefits) {
            personBenefits.add(benefit.toModelType());
        }

        for (JsonAdaptedDeduction deduction: deductions) {
            personDeductions.add(deduction.toModelType());
        }

        return new Payroll(new Salary(this.salary, personDeductions, personBenefits),
                this.startDate, this.endDate, this.paymentDate);
    }
}
