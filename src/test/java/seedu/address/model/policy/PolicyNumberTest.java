package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PolicyNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PolicyNumber(null));
    }

    @Test
    public void constructor_invalidPolicyNumber_throwsIllegalArgumentException() {
        String invalidPolicyNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new PolicyNumber(invalidPolicyNumber));
    }

    @Test
    public void isValidPolicyNumber() {
        // null company
        assertThrows(NullPointerException.class, () -> PolicyNumber.isValidPolicyNumber(null));

        // invalid company
        assertFalse(PolicyNumber.isValidPolicyNumber("")); // empty string
        assertFalse(PolicyNumber.isValidPolicyNumber(" ")); // spaces only
        assertFalse(PolicyNumber.isValidPolicyNumber("^")); // only non-alphanumeric characters
        assertFalse(PolicyNumber.isValidPolicyNumber("NTUC123*")); // contains non-alphanumeric characters
        assertFalse(PolicyNumber.isValidPolicyNumber("NTUC12345")); // exceeds max length

        // valid company
        assertTrue(PolicyNumber.isValidPolicyNumber("1")); // minimal length
        assertTrue(PolicyNumber.isValidPolicyNumber("NTUC1234")); // maximal length
        assertTrue(PolicyNumber.isValidPolicyNumber("AAAA")); // only letters
        assertTrue(PolicyNumber.isValidPolicyNumber("aaaa")); // only small letters
        assertTrue(PolicyNumber.isValidPolicyNumber("1234")); // only numbers
    }

    @Test
    public void equals() {
        PolicyNumber policyNumber = new PolicyNumber("NTUC1234");

        // same values -> returns true
        assertTrue(policyNumber.equals(new PolicyNumber("NTUC1234")));

        // same object -> returns true
        assertTrue(policyNumber.equals(policyNumber));

        // null -> returns false
        assertFalse(policyNumber.equals(null));

        // different types -> returns false
        assertFalse(policyNumber.equals(5.0f));

        // different values -> returns false
        assertFalse(policyNumber.equals(new PolicyNumber("AIA1234")));
    }
}
