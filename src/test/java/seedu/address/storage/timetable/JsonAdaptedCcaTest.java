package seedu.address.storage.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalSchedule.NORMAL_CCA;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.TimeBlock;

public class JsonAdaptedCcaTest {
    private static final String INVALID_TIMEBLOCK = "monday";


    private static final String VALID_NAME = "CS2100";
    private static final String VALID_TIMEBLOCK = "Monday 1200 1300";

    @Test
    public void toModelType_validCcaDetails_returnsModule() throws Exception {
        JsonAdaptedCca cca = new JsonAdaptedCca(NORMAL_CCA);
        assertEquals(NORMAL_CCA, cca.toModelType());
    }

    @Test
    public void toModelType_nullCcaName_throwsException() {
        JsonAdaptedCca cca = new JsonAdaptedCca(null, VALID_TIMEBLOCK);
        String expectedMessage = Cca.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, cca::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_invalidTimeBlock_throwsException() {
        JsonAdaptedCca cca = new JsonAdaptedCca(VALID_NAME, INVALID_TIMEBLOCK);
        String expectedMessage = TimeBlock.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, cca::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_nullTimeBlock_throwsException() {
        JsonAdaptedCca cca = new JsonAdaptedCca(VALID_NAME, null);
        String expectedMessage = TimeBlock.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, cca::toModelType, expectedMessage);
    }
}
