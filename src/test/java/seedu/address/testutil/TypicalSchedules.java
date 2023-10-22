package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.Status;

/**
 * A utility class containing a list of {@code Schedule} objects to be used in tests.
 */
// Schedule objects are named in the format: SCHEDULE_{TUTOR}_{DAY}_{MONTH}
public class TypicalSchedules {
    public static final Schedule SCHEDULE_ALICE_FIRST_JAN = new ScheduleBuilder()
            .withTutor(TypicalPersons.ALICE)
            .withStartTime(LocalDateTime.of(2023, 1, 1, 9, 0, 0))
            .withEndTime(LocalDateTime.of(2023, 1, 1, 11, 0, 0))
            .withStatus(Status.PENDING)
            .build();

    public static final Schedule SCHEDULE_BOB_SECOND_JAN = new ScheduleBuilder()
            .withTutor(TypicalPersons.BOB)
            .withStartTime(LocalDateTime.of(2023, 1, 2, 20, 0, 0))
            .withEndTime(LocalDateTime.of(2023, 1, 2, 22, 0, 0))
            .withStatus(Status.PENDING)
            .build();

    private TypicalSchedules() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical schedules and associated persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Schedule schedule : getTypicalSchedules()) {
            ab.addSchedule(schedule);
        }
        return ab;
    }

    public static List<Schedule> getTypicalSchedules() {
        return new ArrayList<>(Arrays.asList(SCHEDULE_ALICE_FIRST_JAN, SCHEDULE_BOB_SECOND_JAN));
    }
}
