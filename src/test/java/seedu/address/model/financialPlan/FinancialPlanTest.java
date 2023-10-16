package seedu.address.model.financialPlan;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class FinancialPlanTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FinancialPlan(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidFinancialPlanName = "-_";
        assertThrows(IllegalArgumentException.class, () -> new FinancialPlan(invalidFinancialPlanName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> FinancialPlan.isValidFinancialPlanName(null));
    }
}
