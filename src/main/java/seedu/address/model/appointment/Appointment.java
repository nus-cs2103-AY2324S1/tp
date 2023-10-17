package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents an Appointment in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */

public class Appointment {
    private final AppointmentTime appointmentTime;
    private final AppointmentDescription description;
    private Person patient;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param appointmentTime The scheduled time for the appointment.
     * @param description The description for the appointment.
     */
    public Appointment(AppointmentTime appointmentTime, AppointmentDescription description) {
        requireAllNonNull(appointmentTime);
        this.appointmentTime = appointmentTime;
        this.patient = null;
        this.description = description;
    }

    /**
     * Constructs an {@code Appointment}.
     *
     * @param patient The patient associated with the appointment.
     * @param appointmentTime The scheduled time for the appointment.
     * @param description The description for the appointment.
     */
    public Appointment(Person patient, AppointmentTime appointmentTime, AppointmentDescription description) {
        requireAllNonNull(appointmentTime);
        this.appointmentTime = appointmentTime;
        this.patient = patient;
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

    public AppointmentDescription getDescription() {
        return this.description;
    }

    public Person getPerson() {
        return this.patient;
    }

    public String getPatientName() {
        return this.patient.getName().fullName;
    }

    /**
     * Returns true if both appointments have the same time.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && otherAppointment.getAppointmentTime().equals(getAppointmentTime());
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
                && appointmentTime.equals(otherAppointment.appointmentTime)
                && description.equals(otherAppointment.description);
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patient, appointmentTime, description);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patient", patient)
                .add("appointmentTime", appointmentTime)
                .add("description", description)
                .toString();
    }
}
