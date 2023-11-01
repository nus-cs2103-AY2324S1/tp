package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.Benefit;
import seedu.address.model.person.Reason;

public class JsonAdaptedBenefit {
    private final String reason;
    private final String benefit;

    @JsonCreator
    public JsonAdaptedBenefit(@JsonProperty("reason") String reason, @JsonProperty("benefit") String benefit) {
        this.reason = reason;
        this.benefit = benefit;
    }

    public JsonAdaptedBenefit(Benefit benefit) {
        this.reason = benefit.getReason().toString();
        this.benefit = benefit.getBenefitString();
    }

    public Benefit toModelType() {
        return new Benefit(this.benefit, Reason.valueOf(this.reason));
    }
}
