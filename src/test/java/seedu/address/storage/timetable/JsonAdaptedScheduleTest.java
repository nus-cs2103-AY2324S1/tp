package seedu.address.storage.timetable;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.Schedule;
import seedu.address.testutil.PersonBuilder;


public class JsonAdaptedScheduleTest {
    public static final String VALID_MODULE_NAME = "CS2100";
    public static final String VALID_TIMEBLOCK = "Monday 1200 1300";
    public static final String VALID_DATE = "2001-12-26";

    public static final String VALID_EVENT_NAME = "Walk Dog";

    public static final boolean VALID_EVENT_REMINDER = true;

    public static final Person VALID_PERSON = new PersonBuilder().build();

    @Test
    public void toModelType_validSchedule_returnsSchedule() throws Exception {
        Schedule schedule = new Schedule();
        schedule.addModule(new Module(VALID_MODULE_NAME, VALID_TIMEBLOCK));
        schedule.addDatedEvent(new DatedEvent(VALID_EVENT_NAME, VALID_TIMEBLOCK, VALID_DATE, VALID_EVENT_REMINDER));
        schedule.addCca(new Cca(VALID_MODULE_NAME, VALID_TIMEBLOCK));

        JsonAdaptedSchedule jsonAdaptedSchedule = new JsonAdaptedSchedule(schedule);
        Schedule schedule1 = jsonAdaptedSchedule.toModelType();

        assert schedule1.equals(schedule);
    }
}
