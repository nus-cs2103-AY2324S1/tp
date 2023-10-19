package seedu.address.storage;


import static seedu.address.model.appointment.NullAppointment.MESSAGE_NULL_APT;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.NullAppointment;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {
    private final String appointment;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given {@code Appointment}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String appointment) {
        this.appointment = appointment;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointment = source.toString();
    }

    @JsonValue
    public String getAptDescription() {
        return appointment;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Appointment toModelType() throws IllegalValueException {

        if (appointment.equals(MESSAGE_NULL_APT)) {
            return new NullAppointment();
        }

        if (!Appointment.isValidAppointment(appointment)) {
            throw new IllegalValueException(Appointment.MESSAGE_APT_CONSTRAINTS);
        }

        return Appointment.parseAppointmentDescription(appointment);
    }

}
