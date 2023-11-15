package seedu.address.testutil;

import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.Schedule;

/**
 * A utility class containing a list of {@code Schedule} objects to be used in tests.
 */
public class TypicalSchedule {

    public static final Schedule NORMAL_SCHEDULE = new ScheduleBuilder()
            .withModule(new Module("CS2103", "Wednesday 1200 1300"))
            .withCca(new Cca("Basketball", "Monday 1800 2000"))
            .withDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"))
            .build();

    public static final Schedule FULL_SCHEDULE = new ScheduleBuilder()
            .withModule(new Module("CS2103", "Monday 0000 2400"))
            .withModule(new Module("CS2101", "Tuesday 0000 2400"))
            .withModule(new Module("CS2100", "Wednesday 0000 2400"))
            .withModule(new Module("CS2103", "Thursday 0000 2400"))
            .withModule(new Module("CS2101", "Friday 0000 2400"))
            .withModule(new Module("CS2100", "Saturday 0000 2400"))
            .withModule(new Module("CS2103", "Sunday 0000 2400"))
            .build();

    public static final Schedule FILLED_SCHEDULE = new ScheduleBuilder()
            .withModule(new Module("CS2103", "Monday 0000 2400"))
            .withModule(new Module("CS2101", "Tuesday 0000 2400"))
            .withModule(new Module("CS2100", "Wednesday 0000 2400"))
            .withModule(new Module("CS2103", "Thursday 0000 2400"))
            .withModule(new Module("CS2101", "Friday 0000 2400"))
            .withModule(new Module("CS2100", "Saturday 0000 2400"))
            .withModule(new Module("CS2103", "Sunday 0000 2400"))
            .build();

    public static final Cca NORMAL_CCA = new Cca("Basketball", "Monday 1800 2000");
    public static final Module NORMAL_MODULE = new Module("CS2103", "Wednesday 1200 1300");
    public static final DatedEvent NORMAL_DATED_EVENT = DatedEvent
            .newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y");
}
