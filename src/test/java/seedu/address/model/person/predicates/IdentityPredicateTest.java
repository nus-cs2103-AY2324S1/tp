package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IdentityPredicateTest {

    @Test
    public void equals() {
        IdentityPredicate firstPredicate = new IdentityPredicate();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        assertTrue(firstPredicate.equals(new IdentityPredicate()));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(Objects.hash(true), new IdentityPredicate().hashCode());
    }

    @Test
    public void test_anyValue_returnsTrue() {
        IdentityPredicate predicate = new IdentityPredicate();

        assertTrue(predicate.test(null));
        assertTrue(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        String expected = IdentityPredicate.class.getCanonicalName() + "{identity=" + true + "}";
        assertEquals(expected, new IdentityPredicate().toString());
    }
}
