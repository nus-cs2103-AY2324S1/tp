package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.month.DeleteMonth;

public class PolicyDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PolicyDate(null));
    }

    @Test
    public void constructor_invalidPolicyDate_throwsIllegalArgumentException() {
        String invalidPolicyDate = "19-15-2000";
        assertThrows(IllegalArgumentException.class, () -> new PolicyDate(invalidPolicyDate));
    }

    @Test
    public void isValidPolicyDate() {
        // null policy date
        assertThrows(NullPointerException.class, () -> PolicyDate.isValidPolicyDate(null));

        // invalid policy date
        assertFalse(PolicyDate.isValidPolicyDate("")); // empty string
        assertFalse(PolicyDate.isValidPolicyDate(" ")); // spaces only
        assertFalse(PolicyDate.isValidPolicyDate("12-30-2000")); // MM-dd-yyyy format
        assertFalse(PolicyDate.isValidPolicyDate("30122000")); // ddMMyyyy format
        assertFalse(PolicyDate.isValidPolicyDate("01-01-23")); // dd-MM-yy format
        assertFalse(PolicyDate.isValidPolicyDate("29-02-2001")); // 29 feb in non leap year
        assertFalse(PolicyDate.isValidPolicyDate("31-04-2001")); // 31 in month with only 30 days
        assertFalse(PolicyDate.isValidPolicyDate("1-02-2001")); // no leading 0

        // valid policy date
        assertTrue(PolicyDate.isValidPolicyDate("01-01-2000")); // start of year
        assertTrue(PolicyDate.isValidPolicyDate("29-02-2004")); // 29 feb in leap year
        assertTrue(PolicyDate.isValidPolicyDate("31-12-2004")); // end of year
    }

    @Test
    public void isInMonth() {
        // Stub not used for DeleteMonth as it is a wrapper for YearMonth
        DeleteMonth dm = new DeleteMonth("01-2000");

        // different month, different year
        PolicyDate pd1 = new PolicyDate("15-02-2001");
        assertFalse(pd1.isInMonth(dm));

        // different month, same year
        PolicyDate pd2 = new PolicyDate("15-02-2000");
        assertFalse(pd2.isInMonth(dm));

        // same month, different year
        PolicyDate pd3 = new PolicyDate("15-01-2001");
        assertFalse(pd3.isInMonth(dm));

        // same month, same year
        PolicyDate pd4 = new PolicyDate("15-01-2000");
        assertTrue(pd4.isInMonth(dm));
    }

    @Test
    public void equals() {
        PolicyDate policyDate = new PolicyDate("20-01-2020");

        // same values -> returns true
        assertTrue(policyDate.equals(new PolicyDate("20-01-2020")));

        // same object -> returns true
        assertTrue(policyDate.equals(policyDate));

        // null -> returns false
        assertFalse(policyDate.equals(null));

        // different types -> returns false
        assertFalse(policyDate.equals(5.0f));

        // different values -> returns false
        assertFalse(policyDate.equals(new PolicyDate("20-02-2020")));
    }
}
