package seedu.address.storage.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalSchedule.NORMAL_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.TimeBlock;

public class JsonAdaptedModuleTest {
    private static final String INVALID_NAME = "woohoo";
    private static final String INVALID_TIMEBLOCK = "monday";


    private static final String VALID_NAME = "CS2100";
    private static final String VALID_TIMEBLOCK = "Monday 1200 1300";

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(NORMAL_MODULE);
        assertEquals(NORMAL_MODULE, module.toModelType());
    }

    @Test
    public void toModelType_invalidModuleName_throwsException() {
        JsonAdaptedModule module = new JsonAdaptedModule(INVALID_NAME, VALID_TIMEBLOCK);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, module::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_nullModuleName_throwsException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, VALID_TIMEBLOCK);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, module::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_invalidTimeBlock_throwsException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_NAME, INVALID_TIMEBLOCK);
        String expectedMessage = TimeBlock.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, module::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_nullTimeBlock_throwsException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_NAME, null);
        String expectedMessage = TimeBlock.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, module::toModelType, expectedMessage);
    }
}
