package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.schedule.Schedule;

/**
 * A utility class containing a list of {@code Schedule} objects to be used in tests.
 */
public class TypicalSchedules {
    public static final Schedule SCHEDULE_ONE_FIRST_JAN = new ScheduleBuilder()
            .withTutorIndex(1)
            .withStartTime(LocalDateTime.of(2023, 1, 1, 9, 0, 0))
            .withEndTime(LocalDateTime.of(2023, 1, 1, 11, 0, 0))
            .build();

    public static final Schedule SCHEDULE_TWO_SECOND_JAN = new ScheduleBuilder()
            .withTutorIndex(2)
            .withStartTime(LocalDateTime.of(2023, 1, 2, 20, 0))
            .withEndTime(LocalDateTime.of(2023, 1, 2, 22, 0))
            .build();

    private TypicalSchedules() {} // prevents instantiation

    public static List<Schedule> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(SCHEDULE_ONE_FIRST_JAN, SCHEDULE_TWO_SECOND_JAN));
    }
}
