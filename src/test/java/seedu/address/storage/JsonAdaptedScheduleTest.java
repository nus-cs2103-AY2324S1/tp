package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.schedule.Time.DATETIME_INPUT_FORMAT;
import static seedu.address.storage.JsonAdaptedSchedule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_FIRST_JAN;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.StartTime;
import seedu.address.testutil.TypicalSchedules;

public class JsonAdaptedScheduleTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STARTTIME = "15/02/2023 6pm";
    private static final String INVALID_ENDTIME = "14/02/2023 6pm";

    private static final String VALID_NAME = SCHEDULE_ALICE_FIRST_JAN.getTutor().getName().toString();
    private static final String VALID_STARTTIME = SCHEDULE_ALICE_FIRST_JAN.getStartTime().getTime().format(formatter);
    private static final String VALID_ENDTIME = SCHEDULE_ALICE_FIRST_JAN.getEndTime().getTime().format(formatter);

    private static final AddressBook original = TypicalSchedules.getTypicalAddressBook();

    @Test
    public void toModelType_validScheduleDetails_returnsSchedule() throws Exception {

        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(SCHEDULE_ALICE_FIRST_JAN);
        assertEquals(SCHEDULE_ALICE_FIRST_JAN, schedule.toModelType(original));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(INVALID_NAME, VALID_STARTTIME, VALID_ENDTIME);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> schedule.toModelType(original));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(null, VALID_STARTTIME, VALID_ENDTIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> schedule.toModelType(original));
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(VALID_NAME, INVALID_STARTTIME, INVALID_ENDTIME);
        String expectedMessage = StartTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> schedule.toModelType(original));
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_NAME, null, VALID_ENDTIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StartTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> schedule.toModelType(original));
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule =
                new JsonAdaptedSchedule(VALID_NAME, VALID_STARTTIME, INVALID_ENDTIME);
        String expectedMessage = EndTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> schedule.toModelType(original));
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(VALID_NAME, VALID_STARTTIME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> schedule.toModelType(original));
    }

}
