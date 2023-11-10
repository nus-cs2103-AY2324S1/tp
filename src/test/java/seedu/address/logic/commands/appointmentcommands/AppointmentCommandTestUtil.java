package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * The AppointmentCommandTestUtil class provides utility constants for testing appointment-related commands.
 * It includes various date and time strings, as well as a valid description for creating test data.
 */
public class AppointmentCommandTestUtil {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    public static final DayOfWeek DAY_OF_WEEK = LocalDateTime.now().getDayOfWeek();

    // Current date and date 4 hours later
    public static final String CURRENT_DATE = LocalDateTime.now().format(DATE_TIME_FORMATTER);

    public static final String CURRENT_DATE_PLUS_FOUR_HOURS = LocalDateTime.now().plusHours(4)
            .format(DATE_TIME_FORMATTER);


    // Date next week and 4 hours after that
    public static final String NEXT_WEEK_MONDAY = DATE_TIME_FORMATTER.format(LocalDateTime.now()
            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
            .withHour(0)
            .withMinute(0)
            .withSecond(0)
            .withNano(0));

    public static final String VALID_PATIENT_ONE = "John Doe";

    public static final String PATIENT_ONE_DESC = " " + PREFIX_APPOINTMENT_PATIENT + VALID_PATIENT_ONE;

    public static final String VALID_START_ONE = "2023/08/03 10:00";
    public static final String VALID_END_ONE = "2023/08/03 13:00";

    public static final String VALID_START_TWO = "2023/09/03 10:00";

    public static final String VALID_END_TWO = "2023/09/03 11:00";

    public static final String VALID_DESCRIPTION_ONE = "Follow Up Blood Test";

    public static final String DESCRIPTION_DESC_ONE = " " + PREFIX_APPOINTMENT_DESCRIPTION + VALID_DESCRIPTION_ONE;
    public static final String VALID_DESCRIPTION_TWO = "Routine Checkup";

    public static final String START_DESC_ONE = " " + PREFIX_APPOINTMENT_START + VALID_START_ONE;

    public static final String START_DESC_TWO = " " + PREFIX_APPOINTMENT_START + VALID_START_TWO;

    public static final String END_DESC_ONE = " " + PREFIX_APPOINTMENT_END + VALID_END_ONE;

    public static final String END_DESC_TWO = " " + PREFIX_APPOINTMENT_END + VALID_END_TWO;

    public static final String INVALID_START = "10:00 2023/08/03";
    public static final String INVALID_END = "13:00 2023/08/03";

    public static final String INVALID_START_DESC = " " + PREFIX_APPOINTMENT_START + INVALID_START;
    public static final String INVALID_END_DESC = " " + PREFIX_APPOINTMENT_END + INVALID_END;

    public static final String VALID_PRIORITY_TAG_HIGH = "high";

    public static final String PRIORITY_TAG_DESC_HIGH = " " + PREFIX_APPOINTMENT_PRIORITY + VALID_PRIORITY_TAG_HIGH;
    public static final String VALID_PRIORITY_TAG_MEDIUM = "medium";
    public static final String PRIORITY_TAG_DESC_MEDIUM = " " + PREFIX_APPOINTMENT_PRIORITY + VALID_PRIORITY_TAG_MEDIUM;
    public static final String VALID_PRIORITY_TAG_LOW = "low";
    public static final String PRIORITY_TAG_DESC_LOW = " " + PREFIX_APPOINTMENT_PRIORITY + VALID_PRIORITY_TAG_LOW;
}
