package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.remark.Remark;

/**
 * Jackson-friendly version of {@link Remark}.
 */
public class JsonAdaptedRemark {
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedRemark} with the given {@code remark}.
     */
    @JsonCreator
    public JsonAdaptedRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Converts a given {@code Remark} into this class for Jackson use.
     */
    public JsonAdaptedRemark(Remark source) {
        this.remark = source.toString();
    }

    @JsonValue
    public String getRemark() {
        return remark;
    }

    /**
     * Converts this Jackson-friendly adapted department object into the model's {@code Department} object.
     */
    public Remark toModelType() throws IllegalValueException {
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(remark);
    }
}
