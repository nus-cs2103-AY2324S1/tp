package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GenderPredicateTest {

    @Test
    public void testGenderPredicate() {
        GenderPredicate malePredicate = new GenderPredicate("M");
        GenderPredicate femalePredicate = new GenderPredicate("F");

        // Test with a person having MALE gender
        assertTrue(malePredicate.test(new PersonBuilder().withGender("M").build()));

        // Test with a person having FEMALE gender
        assertTrue(femalePredicate.test(new PersonBuilder().withGender("F").build()));

        // Test with a person having a different gender
        assertFalse(malePredicate.test(new PersonBuilder().withGender("F").build()));
    }
}

