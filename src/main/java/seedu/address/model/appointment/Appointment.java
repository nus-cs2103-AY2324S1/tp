package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents an Appointment in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */

public class Appointment {
    private AppointmentTime appointmentTime;
    private Person patient;
    private String patientString;
    private Set<Tag> tags = new HashSet<>();
    private Boolean isCompleted;
    private Boolean isMissed;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param appointmentTime The scheduled time for the appointment.
     * @param patientString A string representation of the patient.
     * @param tags A set of tags associated with the appointment.
     * @param isCompleted Whether the appointment has been completed.
     * @param isMissed Whether the appointment was missed by the patient.
     */
    public Appointment(AppointmentTime appointmentTime, String patientString, Set<Tag> tags,
                       Boolean isCompleted, Boolean isMissed) {
        requireAllNonNull(appointmentTime, patientString, tags, isCompleted, isMissed);
        this.appointmentTime = appointmentTime;
        this.patient = null;
        this.patientString = patientString;
        this.isCompleted = isCompleted;
        this.isMissed = isMissed;
        this.tags.addAll(tags);
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

    public String getPatientString() {
        return this.patientString;
    }

    public Boolean isMissed() {
        return this.isMissed;
    }

    public Boolean isCompleted() {
        return this.isCompleted;
    }
}
