package seedu.address.storage;

import static seedu.address.model.employee.Leave.VALID_DATE_FORMAT;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Leave;

/**
 * Jackson-friendly version of {@link Leave}.
 */
public class JsonAdaptedLeave {
    private final String leaveDate;

    /**
     * Constructs a {@code JsonAdaptedLeave} with the given {@code leaveDate}.
     */
    @JsonCreator
    public JsonAdaptedLeave(LocalDate leaveDate) {
        this.leaveDate = leaveDate.format(VALID_DATE_FORMAT);
    }

    /**
     * Converts a given {@code Department} into this class for Jackson use.
     */
    public JsonAdaptedLeave(Leave source) {
        this.leaveDate = source.toString();
    }

    @JsonValue
    public String getLeaveDate() {
        return leaveDate;
    }

    /**
     * Converts this Jackson-friendly adapted department object into the model's {@code Department} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted department.
     */
    public Leave toModelType() throws IllegalValueException {
        if (!Leave.isValidLeaveDate(leaveDate)) {
            throw new IllegalValueException(Leave.MESSAGE_CONSTRAINTS);
        }
        return new Leave(LocalDate.parse(leaveDate, VALID_DATE_FORMAT));
    }


}
