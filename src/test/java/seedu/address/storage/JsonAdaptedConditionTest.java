package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Condition;
public class JsonAdaptedConditionTest {
    private static final String INVALID_CONDITION = "";
    private static final String INVALID_CONDITION2 = "Fever+";
    private static final Condition VALID_CONDITION = new Condition("Allergic Reaction");

    @Test
    public void toModelType_validRecordDetails_returnsCondition() throws Exception {
        JsonAdaptedCondition condition = new JsonAdaptedCondition(VALID_CONDITION);
        assertEquals(VALID_CONDITION, condition.toModelType());
    }
    @Test
    public void toModelType_invalidCondition_throwsIllegalArgumentException() {
        JsonAdaptedCondition condition = new JsonAdaptedCondition(INVALID_CONDITION);
        String expectedMessage = Condition.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, condition::toModelType);
    }
    @Test
    public void toModelType_invalidCondition2_throwsIllegalArgumentException() {
        JsonAdaptedCondition condition = new JsonAdaptedCondition(INVALID_CONDITION2);
        String expectedMessage = Condition.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, condition::toModelType);
    }
    @Test
    public void toModelType_nullCondition_throwsIllegalValueException() {
        JsonAdaptedCondition condition = new JsonAdaptedCondition((String) null);
        String expectedMessage = Condition.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, condition::toModelType);
    }

}
