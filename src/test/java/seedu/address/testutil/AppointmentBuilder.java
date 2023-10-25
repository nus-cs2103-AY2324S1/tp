package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Time;
import seedu.address.model.appointment.exceptions.InvalidStartEndTimeException;
import seedu.address.model.student.Name;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NAME = "John Doe";
    public static final String DEFAULT_DATE = "2023-12-31";
    public static final String DEFAULT_START_TIME = "16:30";
    public static final String DEFAULT_END_TIME = "17:30";
    public static final String DEFAULT_DESCRIPTION = "First Session";

    private Name name;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Description description;

    /**
     * Creates an {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        name = new Name(DEFAULT_NAME);
        date = new Date(DEFAULT_DATE);
        startTime = new Time(DEFAULT_START_TIME);
        endTime = new Time(DEFAULT_END_TIME);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        name = appointmentToCopy.getName();
        date = appointmentToCopy.getDate();
        startTime = appointmentToCopy.getStartTime();
        endTime = appointmentToCopy.getEndTime();
        description = appointmentToCopy.getDescription();
    }

    /**
     * Sets the {@code Name} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStartTime(String startTime) {
        this.startTime = new Time(startTime);
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withEndTime(String endTime) {
        this.endTime = new Time(endTime);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Builds an {@code Appointment} with the provided details
     * @return Appointment with the given details
     */
    public Appointment build() {
        try {
            Appointment appt = new Appointment(date, startTime, endTime, name, description);
            return appt;
        } catch (InvalidStartEndTimeException e) {
            throw new RuntimeException(e);
        }
    }
}
