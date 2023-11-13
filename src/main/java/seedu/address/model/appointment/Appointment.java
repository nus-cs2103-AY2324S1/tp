package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Appointment instance for patients
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS = "Description must not be empty, Date must be in dd-MM-yyyy HH:mm";
    public static final String MESSAGE_INVALID_DATE_TIME = "Date must be in dd-MM-yyyy HH:mm format.\n"
            + "Date & time must be after the current time.\n"
            + "Time of appointment must be on the hour, between 9 AM and 5 PM inclusive.";
    /*
     * description must be alphanumeric
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy hh.mma");

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    private final String description;
    private final LocalDateTime dateTime;
    private final Person patient;

    private final String doctorName;


    /**
     * Constructor for Appointment instance
     * @param description Description of the appointment
     * @param dateTime Time and Date of the appointment
     */
    public Appointment(String description, LocalDateTime dateTime, Person patient, String name) {
        requireAllNonNull(description, dateTime);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.dateTime = dateTime;
        this.patient = patient;
        this.doctorName = name;
    }

    /**
     * Returns true if a given string is a valid descrption
     * @param test String to be tested
     * @return True if the string is a valid description
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given LocalDateTime is a valid date and time (must be in the future) for an appointment.
     */
    public static boolean isValidDateTime(String test) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime parsedDateTime = LocalDateTime.parse(test, formatter);
            LocalDateTime currentDateTime = LocalDateTime.now();

            LocalTime startTime = LocalTime.of(8, 0);
            LocalTime endTime = LocalTime.of(18, 0);

            return parsedDateTime.isAfter(currentDateTime)
                    && parsedDateTime.toLocalTime().isAfter(startTime)
                    && parsedDateTime.toLocalTime().isBefore(endTime)
                    && parsedDateTime.getMinute() == 0; // Ensure it's on the hour
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.doctorName;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("description", description)
                .add("dateTime", dateTime)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;

        return Objects.equals(description, otherAppointment.description)
                && Objects.equals(dateTime, otherAppointment.dateTime);
    }

    @Override
    public int hashCode() {
        // Generate a hashCode based on description and dateTime
        return Objects.hash(description, dateTime);
    }

    /**
     * Returns the patient of the appointment.
     *
     * @return Patient of the appointment.
     */
    public Person getPatient() {
        return this.patient;
    }

    public Name getPatientName() {
        return this.patient.getName();
    }

    /**
     * Returns true if both appointments have the same details.
     */
    public boolean isSameAppointment(Appointment otherAppt) {
        if (otherAppt == this) {
            return true;
        }

        return otherAppt != null
                && otherAppt.getPatient().equals(getPatient())
                && otherAppt.getDescription().equals(getDescription())
                && otherAppt.getDateTime().equals(getDateTime());
    }

    /**
     * Getter method for the patient of the appointment.
     *
     * @return Patient of the appointment.
     */
    public Person getPerson() {
        return this.patient;
    }
}
