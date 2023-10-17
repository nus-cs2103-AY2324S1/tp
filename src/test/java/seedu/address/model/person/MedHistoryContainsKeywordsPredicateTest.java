package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.predicates.MedHistoryContainsKeywordsPredicate;
import seedu.address.testutil.PatientBuilder;


public class MedHistoryContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MedHistoryContainsKeywordsPredicate firstPredicate =
                new MedHistoryContainsKeywordsPredicate(firstPredicateKeywordList);
        MedHistoryContainsKeywordsPredicate secondPredicate =
                new MedHistoryContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MedHistoryContainsKeywordsPredicate firstPredicateCopy =
                new MedHistoryContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_medicalHistoryContainsKeywords_returnsTrue() {
        // One keyword
        MedHistoryContainsKeywordsPredicate predicate =
                new MedHistoryContainsKeywordsPredicate(Collections.singletonList("Osteoporosis"));
        assertTrue(predicate.test(new PatientBuilder().withMedicalHistory("Osteoporosis").build()));

        // Multiple keywords
        predicate = new MedHistoryContainsKeywordsPredicate(Arrays.asList("Osteoporosis", "Diabetes"));
        assertTrue(predicate.test(new PatientBuilder().withMedicalHistory("Osteoporosis").build()));

        // One Keyword with Multiple Medical History
        predicate = new MedHistoryContainsKeywordsPredicate(Collections.singletonList("Osteoporosis"));
        assertTrue(predicate.test(new PatientBuilder().withMedicalHistory("Diabetes", "Osteoporosis").build()));

        // Multiple Keyword with Multiple Medical History
        predicate = new MedHistoryContainsKeywordsPredicate(Arrays.asList("Osteoporosis", "Asthma"));
        assertTrue(predicate.test(new PatientBuilder()
                .withMedicalHistory("Diabetes", "Osteoporosis", "Asthma").build()));

        // Only one matching keyword
        predicate = new MedHistoryContainsKeywordsPredicate(Arrays.asList("Osteoporosis", "Diabetes"));
        assertTrue(predicate.test(new PatientBuilder()
                .withMedicalHistory("Osteoporosis primary type 1").build()));

        // Mixed-case keywords
        predicate = new MedHistoryContainsKeywordsPredicate(Arrays.asList("oSteOpoROsis", "dIaBeTeS"));
        assertTrue(predicate.test(new PatientBuilder()
                .withMedicalHistory("Osteoporosis", "Diabetes").build()));
    }

    @Test
    public void test_medicalHistoryDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MedHistoryContainsKeywordsPredicate predicate =
                new MedHistoryContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PatientBuilder().withMedicalHistory("Osteoporosis").build()));

        // Non-matching keyword
        predicate =
                new MedHistoryContainsKeywordsPredicate(Arrays.asList("Osteoporosis"));
        assertFalse(predicate.test(new PatientBuilder().withMedicalHistory("Anemia").build()));

        // Keywords match phone, email and address, but does not match Medical History
        predicate =
                new MedHistoryContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PatientBuilder().withMedicalHistory("Anemia").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        MedHistoryContainsKeywordsPredicate predicate = new MedHistoryContainsKeywordsPredicate(keywords);

        String expected = MedHistoryContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
