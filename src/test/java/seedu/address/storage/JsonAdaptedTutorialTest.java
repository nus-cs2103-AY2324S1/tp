package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;
import seedu.address.model.tutorial.Tutorial;

public class JsonAdaptedTutorialTest {

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        Module module = new Module("CS2030S");
        Tutorial t12 = new Tutorial(module, "T12", "Mon 6pm");
        JsonAdaptedTutorial tutorial = new JsonAdaptedTutorial(t12);
        assertEquals(t12, tutorial.toModelType());
    }
}
