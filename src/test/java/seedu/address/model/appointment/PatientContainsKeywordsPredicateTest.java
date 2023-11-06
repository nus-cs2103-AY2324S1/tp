package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class PatientContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PatientContainsKeywordsPredicate firstPredicate =
                new PatientContainsKeywordsPredicate(firstPredicateKeywordList);
        PatientContainsKeywordsPredicate secondPredicate =
                new PatientContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PatientContainsKeywordsPredicate firstPredicateCopy =
                new PatientContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_patientContainsKeywords_returnsTrue() {
        // One keyword
        PatientContainsKeywordsPredicate predicate =
                new PatientContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new AppointmentBuilder().withPatient(ALICE).build()));

        // Only one matching keyword
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new AppointmentBuilder().withPatient(ALICE).build()));

        // Mixed-case keywords
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new AppointmentBuilder().withPatient(ALICE).build()));
    }

    @Test
    public void test_patientDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PatientContainsKeywordsPredicate predicate =
                new PatientContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AppointmentBuilder().withPatient(ALICE).build()));

        // Non-matching keyword
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new AppointmentBuilder().withPatient(ALICE).build()));;
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PatientContainsKeywordsPredicate predicate = new PatientContainsKeywordsPredicate(keywords);

        String expected = PatientContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
