package seedu.address.model.risklevel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RiskLevelTest {


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RiskLevel(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new RiskLevel(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> RiskLevel.isValidRiskLevel(null));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        RiskLevel riskLevel = new RiskLevel("high");
        assertEquals(true, riskLevel.equals(riskLevel));
    }

    @Test
    public void equals_withNull_returnsFalse() {
        RiskLevel riskLevel = new RiskLevel("high");
        assertEquals(false, riskLevel.equals(null));
    }
}
