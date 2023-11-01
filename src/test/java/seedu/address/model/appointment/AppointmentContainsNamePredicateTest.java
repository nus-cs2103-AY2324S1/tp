package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentContainsNamePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AppointmentContainsNamePredicate firstPredicate =
                new AppointmentContainsNamePredicate(firstPredicateKeywordList);
        AppointmentContainsNamePredicate secondPredicate =
                new AppointmentContainsNamePredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentContainsNamePredicate firstPredicateCopy =
                new AppointmentContainsNamePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // Matching first name
        AppointmentContainsNamePredicate predicate =
                new AppointmentContainsNamePredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));

        // Matching full Name
        predicate = new AppointmentContainsNamePredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));

        // Matching last name
        predicate = new AppointmentContainsNamePredicate(Arrays.asList("Carol"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new AppointmentContainsNamePredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AppointmentContainsNamePredicate predicate = new AppointmentContainsNamePredicate(Collections.emptyList());
        assertFalse(predicate.test(new AppointmentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new AppointmentContainsNamePredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new AppointmentBuilder().withName("Alice Bob").build()));

        // Keywords match datetime and description, but does not match name
        predicate = new AppointmentContainsNamePredicate(Arrays.asList("2023-12-31", "15:30", "16:30", "description"));
        assertFalse(predicate.test(new AppointmentBuilder().withName("Alice").withDate("2023-12-31")
                .withStartTime("15:30").withEndTime("16:30").withDescription("description").build()));
    }
}
