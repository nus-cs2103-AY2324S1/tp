package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Benefit;
import seedu.address.model.person.Deduction;
import seedu.address.model.person.Reason;

public class JsonAdaptedDeductionTest {

    private static final String INVALID_REASON = "Sick";
    private static final String INVALID_DEDUCTIONSTRING = "200";

    private static final String VALID_REASON = "ABSENCE";
    private static final String VALID_DEDUCTIONSTRING = "200.00";
    private static final Deduction VALID_DEDUCTION = new Deduction(VALID_DEDUCTIONSTRING, Reason.valueOf(VALID_REASON));

    @Test
    public void toModelType_validDeductionDetails_returnsDeduction() {
        JsonAdaptedDeduction deduction = new JsonAdaptedDeduction(VALID_REASON, VALID_DEDUCTIONSTRING);
        assertEquals(VALID_DEDUCTION, deduction.toModelType());
    }

    @Test
    public void toModelType_invalidReason_throwsIllegalArgumentException() {
        JsonAdaptedDeduction deduction = new JsonAdaptedDeduction(INVALID_REASON, VALID_DEDUCTIONSTRING);

        assertThrows(IllegalArgumentException.class, deduction::toModelType);
    }

    @Test
    public void toModelType_nullReason_throwsNullPointerException() {
        JsonAdaptedDeduction deduction = new JsonAdaptedDeduction(null, VALID_DEDUCTIONSTRING);

        assertThrows(NullPointerException.class, deduction::toModelType);
    }

    @Test
    public void toModelType_invalidDeduction_throwsIllegalArgumentException() {
        JsonAdaptedDeduction deduction = new JsonAdaptedDeduction(VALID_REASON, INVALID_DEDUCTIONSTRING);
        String expectedMessage = Benefit.MESSAGE_CONSTRAINTS;

        assertThrows(IllegalArgumentException.class, expectedMessage, deduction::toModelType);
    }

    @Test
    public void toModelType_nullDeduction_throwsNullPointerException() {
        JsonAdaptedDeduction deduction = new JsonAdaptedDeduction(VALID_REASON, null);

        assertThrows(NullPointerException.class, deduction::toModelType);
    }
}
