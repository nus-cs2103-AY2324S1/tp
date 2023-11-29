package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTreatment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTreatments.WISDOM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.treatment.TreatmentCost;
import seedu.address.model.treatment.TreatmentName;
import seedu.address.model.treatment.TreatmentTime;

public class JsonAdaptedTreatmentTest {

    private static final String INVALID_NAME = "Br@ces";
    private static final String INVALID_COST = "+651234.3";

    private static final String INVALID_TIME = "69:32";


    private static final String VALID_NAME = WISDOM.getName().toString();
    private static final String VALID_COST = WISDOM.getCost().toString();
    private static final String VALID_TIME = "13:12";


    @Test
    public void toModelType_validTreatmentDetails_returnsTreatment() throws Exception {
        JsonAdaptedTreatment treatment = new JsonAdaptedTreatment(WISDOM);
        assertEquals(WISDOM, treatment.toModelType());
    }

    @Test
    public void toModelType_invalidTreatmentName_throwsIllegalValueException() {
        JsonAdaptedTreatment treatment =
            new JsonAdaptedTreatment(INVALID_NAME, VALID_COST, VALID_TIME);
        String expectedMessage = TreatmentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, treatment::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTreatment treatment = new JsonAdaptedTreatment(null, VALID_COST, VALID_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
            TreatmentName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, treatment::toModelType);
    }

    @Test
    public void toModelType_invalidCost_throwsIllegalValueException() {
        JsonAdaptedTreatment treatment =
            new JsonAdaptedTreatment(VALID_NAME, INVALID_COST, VALID_TIME);
        String expectedMessage = TreatmentCost.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, treatment::toModelType);
    }

    @Test
    public void toModelType_nullCost_throwsIllegalValueException() {
        JsonAdaptedTreatment treatment = new JsonAdaptedTreatment(VALID_NAME, null, VALID_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
            TreatmentCost.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, treatment::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedTreatment treatment =
            new JsonAdaptedTreatment(VALID_NAME, VALID_COST, INVALID_TIME);
        String expectedMessage = TreatmentTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, treatment::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedTreatment treatment =
            new JsonAdaptedTreatment(VALID_NAME, VALID_COST, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
            TreatmentTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, treatment::toModelType);

    }


}
