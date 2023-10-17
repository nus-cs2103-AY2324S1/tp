package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents an Appointment in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */

public class Appointment {
    private AppointmentTime appointmentTime;
    private Person patient;
    private int index;

    private AppointmentDescription description;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param appointmentTime The scheduled time for the appointment.
     * @param index patient index for whom we are setting appointment for.
     */
    public Appointment(AppointmentTime appointmentTime, AppointmentDescription description, int index) {
        requireAllNonNull(appointmentTime);
        this.appointmentTime = appointmentTime;
        this.patient = null;
        this.index = index;
        this.description = description;
    }

    /**
     * Used in the AddAppointmentCommand
     * @param patient sets the patient into the appointment
     */
    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public AppointmentTime getAppointmentTime() {
        return this.appointmentTime;
    }

    public LocalDateTime getStartTime() {
        return this.appointmentTime.getStart();
    }

    public LocalDateTime getEndTime() {
        return this.appointmentTime.getEnd();
    }

    public Person getPerson() {
        return this.patient;
    }

    public String getPatientName() {
        return this.patient.getName().fullName;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return patient.equals(otherAppointment.patient)
                && appointmentTime.equals(otherAppointment.appointmentTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patient, appointmentTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patient", patient)
                .add("appointmentTime", appointmentTime)
                .toString();
    }
}
