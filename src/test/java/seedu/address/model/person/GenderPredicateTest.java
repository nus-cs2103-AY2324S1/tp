package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class GenderPredicateTest {

    @Test
    public void testGenderPredicate() {
        GenderPredicate malePredicate = new GenderPredicate("M");
        GenderPredicate femalePredicate = new GenderPredicate("F");

        // Test with a person having MALE gender
        assertTrue(malePredicate.test(new PatientBuilder().withGender("M").build()));

        // Test with a person having FEMALE gender
        assertTrue(femalePredicate.test(new PatientBuilder().withGender("F").build()));

        // Test with a person having a different gender
        assertFalse(malePredicate.test(new PatientBuilder().withGender("F").build()));
        assertFalse(femalePredicate.test(new PatientBuilder().withGender("M").build()));
    }

    @Test
    public void equals() {
        String firstPredicateKeyword = "M";
        String secondPredicateKeyword = "F";

        GenderPredicate firstPredicate = new GenderPredicate(firstPredicateKeyword);
        GenderPredicate secondPredicate = new GenderPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GenderPredicate firstPredicateCopy = new GenderPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        GenderPredicate predicate = new GenderPredicate(keyword);

        String expected = GenderPredicate.class.getCanonicalName() + "{keywords=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}

