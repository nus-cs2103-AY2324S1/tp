package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;

public class JsonAdaptedModuleTest {
    private static final String INVALID_MODULE_CODE = "S/U THIS MOD LMAO";
    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        Module randomModule = new Module("CS2030S");
        JsonAdaptedModule module = new JsonAdaptedModule(randomModule);
        assertEquals(randomModule, module.toModelType());
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(INVALID_MODULE_CODE);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }
}
