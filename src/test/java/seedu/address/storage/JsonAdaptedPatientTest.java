package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class JsonAdaptedPatientTest {
    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient specialist = new JsonAdaptedPatient(ALICE);
        assertEquals(ALICE, specialist.toModelType());
    }
    //TODO: Add Patient field validity check tests (like in JsonAdaptedPersonTest)
}
