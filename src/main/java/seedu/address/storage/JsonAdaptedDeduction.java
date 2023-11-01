package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.Deduction;
import seedu.address.model.person.Reason;

public class JsonAdaptedDeduction {
    private final String reason;
    private final String deduction;

    @JsonCreator
    public JsonAdaptedDeduction(@JsonProperty("reason") String reason, @JsonProperty("benefit") String deduction) {
        this.reason = reason;
        this.deduction = deduction;
    }

    public JsonAdaptedDeduction(Deduction deduction) {
        this.reason = deduction.getReason().toString();
        this.deduction = deduction.getDeductString();
    }

    public Deduction toModelType() {
        return new Deduction(this.deduction, Reason.valueOf(this.reason));
    }
}
