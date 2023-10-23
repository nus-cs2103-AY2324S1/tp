package seedu.address.testutil;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.DateUtil.parseDateTime;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.CURRENT_DATE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.CURRENT_DATE_PLUS_FOUR_HOURS;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_END_ONE;
import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_START_ONE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.appointment.AppointmentTime;

/**
 * A utility class to help with building AppointmentTime objects.
 */
public class AppointmentTimeBuilder {

    private static final String DEFAULT_START = VALID_START_ONE;
    private static final String DEFAULT_END = VALID_END_ONE;
    private static final String DEFAULT_START_TWO = CURRENT_DATE;
    private static final String DEFAULT_END_TWO = CURRENT_DATE_PLUS_FOUR_HOURS;

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates a {@code AppointmentTimeBuilder} with the default details.
     */
    public AppointmentTimeBuilder() {
        start = parseDateTime(DEFAULT_START);
        end = parseDateTime(DEFAULT_END);
    }

    /**
     * Initializes the AppointmentTimeBuilder with the data of {@code appointmentTime}.
     */
    public AppointmentTimeBuilder(AppointmentTime appointmentTime) {
        start = appointmentTime.getStart();
        end = appointmentTime.getEnd();
    }

    /**
     * Sets the start time of the {@code AppointmentTime} that we are building.
     */
    public AppointmentTimeBuilder withStart(String stringStart) {
        this.start = parseDateTime(stringStart);
        return this;
    }

    /**
     * Sets the end time of the {@code AppointmentTime} that we are building.
     */
    public AppointmentTimeBuilder withEnd(String stringEnd) {
        this.end = parseDateTime(stringEnd);
        return this;
    }

    public AppointmentTime build() {
        return new AppointmentTime(start, end);
    }

}
