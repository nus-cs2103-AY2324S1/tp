package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ConditionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Condition(null));
    }

    @Test
    public void constructor_invalidCondition_throwsIllegalArgumentException() {
        String invalidCondition1 = ""; // empty string
        String invalidCondition2 = " something"; // string with whitespace in front
        assertThrows(IllegalArgumentException.class, () -> new Condition(invalidCondition1));
        assertThrows(IllegalArgumentException.class, () -> new Condition(invalidCondition2));
    }

    @Test
    public void isValidCondition() {
        // null Condition
        assertThrows(NullPointerException.class, () -> Condition.isValidCondition(null));

        // invalid Condition
        assertFalse(Condition.isValidCondition("")); // empty string
        assertFalse(Condition.isValidCondition(" ")); // spaces only
        assertFalse(Condition.isValidCondition(" Condition")); // string with whitespace in front

        // valid Condition
        assertTrue(Condition.isValidCondition("something"));
    }

    @Test
    public void equals() {
        Condition condition = new Condition("something");

        // same values -> returns true
        assertTrue(condition.equals(new Condition("something")));

        // same object -> returns true
        assertTrue(condition.equals(condition));

        // null -> returns false
        assertFalse(condition.equals(null));

        // different types -> returns false
        assertFalse(condition.equals(5.0f));

        // different values -> returns false
        assertFalse(condition.equals(new Condition("something else")));
    }
}
