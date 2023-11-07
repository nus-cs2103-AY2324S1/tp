package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Benefit;
import seedu.address.model.person.Reason;

public class JsonAdaptedBenefitTest {

    private static final String INVALID_REASON = "Sick";
    private static final String INVALID_BENEFITSTRING = "200";

    private static final String VALID_REASON = "ANNUAL_BONUS";
    private static final String VALID_BENEFITSTRING = "200.00";
    private static final Benefit VALID_BENEFIT = new Benefit(VALID_BENEFITSTRING, Reason.valueOf(VALID_REASON));

    @Test
    public void toModelType_validBenefitDetails_returnsBenefit() {
        JsonAdaptedBenefit benefit = new JsonAdaptedBenefit(VALID_REASON, VALID_BENEFITSTRING);
        assertEquals(VALID_BENEFIT, benefit.toModelType());
    }

    @Test
    public void toModelType_invalidReason_throwsIllegalArgumentException() {
        JsonAdaptedBenefit benefit = new JsonAdaptedBenefit(INVALID_REASON, VALID_BENEFITSTRING);

        assertThrows(IllegalArgumentException.class, benefit::toModelType);
    }

    @Test
    public void toModelType_nullReason_throwsNullPointerException() {
        JsonAdaptedBenefit benefit = new JsonAdaptedBenefit(null, VALID_BENEFITSTRING);

        assertThrows(NullPointerException.class, benefit::toModelType);
    }

    @Test
    public void toModelType_invalidBenefit_throwsIllegalArgumentException() {
        JsonAdaptedBenefit benefit = new JsonAdaptedBenefit(VALID_REASON, INVALID_BENEFITSTRING);
        String expectedMessage = Benefit.MESSAGE_CONSTRAINTS;

        assertThrows(IllegalArgumentException.class, expectedMessage, benefit::toModelType);
    }

    @Test
    public void toModelType_nullBenefit_throwsNullPointerException() {
        JsonAdaptedBenefit benefit = new JsonAdaptedBenefit(VALID_REASON, null);

        assertThrows(NullPointerException.class, benefit::toModelType);
    }
}
