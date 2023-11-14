package seedu.address.model.plan;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PlanNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.plan.PlanName(null));
    }

    @Test
    public void constructor_invalidPlanName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.plan.PlanName(invalidName));
    }

    @Test
    public void isValidPlanName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.plan.PlanName.isValidPlanName(null));

        // invalid name
        assertFalse(PlanName.isValidPlanName("")); // empty string
        assertFalse(PlanName.isValidPlanName(" ")); // spaces only
        assertFalse(PlanName.isValidPlanName("^")); // only non-alphanumeric characters
        assertFalse(PlanName.isValidPlanName("dota*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(PlanName.isValidPlanName("play some dota")); // alphabets only
        assertTrue(PlanName.isValidPlanName("12345")); // numbers onlyß
        assertTrue(PlanName.isValidPlanName("play some dota 2")); // alphanumeric characters
        assertTrue(PlanName.isValidPlanName("Capital Tan")); // with capital letters
        assertTrue(PlanName.isValidPlanName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        seedu.address.model.plan.PlanName planName = new seedu.address.model.plan.PlanName("Valid Name");

        // same values -> returns true
        assertTrue(planName.equals(new seedu.address.model.plan.PlanName("Valid Name")));

        // same object -> returns true
        assertTrue(planName.equals(planName));

        // null -> returns false
        assertFalse(planName.equals(null));

        // different types -> returns false
        assertFalse(planName.equals(5.0f));

        // different values -> returns false
        assertFalse(planName.equals(new seedu.address.model.plan.PlanName("Other Valid Name")));
    }
}
