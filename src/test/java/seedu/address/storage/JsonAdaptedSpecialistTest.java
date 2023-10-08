package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ELLE;

import org.junit.jupiter.api.Test;

public class JsonAdaptedSpecialistTest {
    @Test
    public void toModelType_validSpecialistDetails_returnsSpecialist() throws Exception {
        JsonAdaptedSpecialist specialist = new JsonAdaptedSpecialist(ELLE);
        assertEquals(ELLE, specialist.toModelType());
    }
    //TODO: Add Specialist field validity check tests (like in JsonAdaptedPersonTest)
}
