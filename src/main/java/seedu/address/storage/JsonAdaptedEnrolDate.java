package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.EnrolDate;

/**
 * Jackson-friendly version of {@link EnrolDate}.
 */
public class JsonAdaptedEnrolDate {
    private final String enrolDate;

    /**
     * Constructs a {@code JsonAdaptedEnrolDate} with the given {@code enrolDate}.
     */
    @JsonCreator
    public JsonAdaptedEnrolDate(String enrolDate) {
        this.enrolDate = enrolDate;
    }

    /**
     * Converts a given {@code Subject} into this class for Jackson use.
     */
    public JsonAdaptedEnrolDate(EnrolDate source) {
        enrolDate = source.toString();
    }

    @JsonValue
    public String getEnrolDate() {
        return enrolDate;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code EnrolDate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public EnrolDate toModelType() throws IllegalValueException {
        if (!EnrolDate.isValidDate(enrolDate)) {
            throw new IllegalValueException(EnrolDate.MESSAGE_INVALID_DATE_FORMAT);
        }
        return new EnrolDate(enrolDate);
    }
}
