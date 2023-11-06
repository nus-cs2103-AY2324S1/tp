package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.Deduction;
import seedu.address.model.person.Reason;

/**
 * Jackson-friendly version of {@link Deduction}.
 */
public class JsonAdaptedDeduction {
    private final String reason;
    private final String deduction;

    /**
     * Constructs a {@code JsonAdaptedDeduction} with the given deduction details.
     */
    @JsonCreator
    public JsonAdaptedDeduction(@JsonProperty("reason") String reason, @JsonProperty("deductions") String deduction) {
        this.reason = reason;
        this.deduction = deduction;
    }

    /**
     * Converts a given {@code Deduction} into this class for Jackson use.
     */
    public JsonAdaptedDeduction(Deduction deduction) {
        this.reason = deduction.getReason().toString();
        this.deduction = deduction.getDeductString();
    }

    /**
     * Converts this Jackson-friendly adapted deduction object into the model's {@code Deduction} object.
     */
    public Deduction toModelType() {
        return new Deduction(this.deduction, Reason.valueOf(this.reason));
    }
}
