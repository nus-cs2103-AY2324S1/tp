package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PhoneContainsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateValueList = Collections.singletonList("0");
        List<String> secondPredicateValueList = Arrays.asList("1", "9");

        PhoneContainsPredicate firstPredicate = new PhoneContainsPredicate(firstPredicateValueList);
        PhoneContainsPredicate secondPredicate = new PhoneContainsPredicate(secondPredicateValueList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsPredicate firstPredicateCopy = new PhoneContainsPredicate(firstPredicateValueList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsValues_returnsTrue() {
        // One keyword
        PhoneContainsPredicate predicate = new PhoneContainsPredicate(Collections.singletonList("01"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("90199029").build()));

        // Multiple keywords
        predicate = new PhoneContainsPredicate(Arrays.asList("01", "02"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("90199029").build()));

        // Only one matching keyword
        predicate = new PhoneContainsPredicate(Arrays.asList("29", "55"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("90199029").build()));
    }

    @Test
    public void test_phoneDoesNotContainValues_returnsFalse() {
        // Zero keywords
        PhoneContainsPredicate predicate = new PhoneContainsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("90199029").build()));

        // Non-matching keyword
        predicate = new PhoneContainsPredicate(Arrays.asList("9o19"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("90199029").build()));

        // Keywords match name and email, but does not match phone
        predicate = new PhoneContainsPredicate(Arrays.asList("Alice", "123456", "alice@email.com"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> values = List.of("123", "456");
        PhoneContainsPredicate predicate = new PhoneContainsPredicate(values);

        String expected = PhoneContainsPredicate.class.getCanonicalName() + "{values=" + values + "}";
        assertEquals(expected, predicate.toString());
    }
}
