package seedu.address.model.risklevel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RiskLevelTest {


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RiskLevel(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        // EP: empty strings
        assertThrows(IllegalArgumentException.class, () -> new RiskLevel(""));
        assertThrows(IllegalArgumentException.class, () -> new RiskLevel("   "));

        // EP: not valid string
        assertThrows(IllegalArgumentException.class, () -> new RiskLevel("oogabooga"));
    }

    @Test
    public void isValidRiskLevel() {
        // null tag name
        assertThrows(NullPointerException.class, () -> RiskLevel.isValidRiskLevel(null));

        // EP: empty strings
        assertFalse(RiskLevel.isValidRiskLevel(""));
        assertFalse(RiskLevel.isValidRiskLevel("  "));

        // EP: not valid string
        assertFalse(RiskLevel.isValidRiskLevel("heehaa"));

        // EP: Invalid due to upper case
        assertFalse(RiskLevel.isValidRiskLevel("HIGH"));
        assertFalse(RiskLevel.isValidRiskLevel("meDium"));
        assertFalse(RiskLevel.isValidRiskLevel("LoW"));

        // EP: Valid strings
        assertTrue(RiskLevel.isValidRiskLevel("high"));
        assertTrue(RiskLevel.isValidRiskLevel("medium"));
        assertTrue(RiskLevel.isValidRiskLevel("low"));
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
