package seedu.address.model.financialplan;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FINANCIAL_PLAN_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FINANCIAL_PLAN_2;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
    public void containsSubstring_failure() {
        FinancialPlan fp = new FinancialPlan(VALID_FINANCIAL_PLAN_1);
        String substring = "plan 2";
        assertFalse(fp.containsSubstring(substring));
    }

    @Test
    public void containsSubstring_success() {
        FinancialPlan fp = new FinancialPlan(VALID_FINANCIAL_PLAN_1);
        String substring = "plan 1";
        assertTrue(fp.containsSubstring(substring));
    }

    @Test
    public void isValidFinancialName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> FinancialPlan.isValidFinancialPlanName(null));
    }

    @Test
    public void equals() {
        FinancialPlan fp1 = new FinancialPlan(VALID_FINANCIAL_PLAN_1);
        FinancialPlan fp2 = new FinancialPlan(VALID_FINANCIAL_PLAN_1);
        FinancialPlan fp3 = new FinancialPlan(VALID_FINANCIAL_PLAN_2);

        assertTrue(fp1.equals(fp1));
        assertTrue(fp1.equals(fp2));
        assertFalse(fp1.equals(fp3));
        assertFalse(fp2.equals(null));
    }
}
