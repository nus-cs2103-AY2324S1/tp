package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.Benefit;
import seedu.address.model.person.Reason;

/**
 * Jackson-friendly version of {@link Benefit}.
 */
public class JsonAdaptedBenefit {
    private final String reason;
    private final String benefit;

    /**
     * Constructs a {@code JsonAdaptedBenefit} with the given benefit details.
     */
    @JsonCreator
    public JsonAdaptedBenefit(@JsonProperty("reason") String reason, @JsonProperty("benefit") String benefit) {
        this.reason = reason;
        this.benefit = benefit;
    }

    /**
     * Converts a given {@code Benefit} into this class for Jackson use.
     */
    public JsonAdaptedBenefit(Benefit benefit) {
        this.reason = benefit.getReason().toString();
        this.benefit = benefit.getBenefitString();
    }

    /**
     * Converts this Jackson-friendly adapted benefit object into the model's {@code Benefit} object.
     */
    public Benefit toModelType() {
        return new Benefit(this.benefit, Reason.valueOf(this.reason));
    }
}
