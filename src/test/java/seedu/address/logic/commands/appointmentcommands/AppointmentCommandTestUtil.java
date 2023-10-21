package seedu.address.logic.commands.appointmentcommands;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class AppointmentCommandTestUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    public static DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();

    // Current date and date 4 hours later
    public static final String CURRENT_DATE = LocalDateTime.now().format(DATE_TIME_FORMATTER);

    public static final String CURRENT_DATE_PLUS_FOUR_HOURS = LocalDateTime.now().plusHours(4).format(DATE_TIME_FORMATTER);


    // Date next week and 4 hours after that
    public static final String NEXT_WEEK_MONDAY = DATE_TIME_FORMATTER.format(LocalDateTime.now()
            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
            .withHour(0)
            .withMinute(0)
            .withSecond(0)
            .withNano(0));

    public static final String VALID_START_ONE = "2023/08/03 10:00";
    public static final String VALID_END_ONE = "2023/08/03 13:00";

    public static final String VALID_START_TWO = "2023/09/03 10:00";

    public static final String VALID_END_TWO = "2023/09/03 11:00";


    public static final String VALID_DESCRIPTION = "Follow Up Blood Test";
}


