package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class SubjectContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SubjectContainsKeywordsPredicate firstPredicate =
                new SubjectContainsKeywordsPredicate(firstPredicateKeywordList);
        SubjectContainsKeywordsPredicate secondPredicate =
                new SubjectContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SubjectContainsKeywordsPredicate firstPredicateCopy =
                new SubjectContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_subjectContainsKeywords_returnsTrue() {
        // Zero keywords
        SubjectContainsKeywordsPredicate subject = new SubjectContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(subject.test(new PersonBuilder().withSubject("Maths").build()));

        // One keyword
        SubjectContainsKeywordsPredicate predicate = new SubjectContainsKeywordsPredicate(
                Collections.singletonList("Maths"));
        assertTrue(predicate.test(new PersonBuilder().withSubject("Maths Chemistry").build()));

        // Multiple keywords
        predicate = new SubjectContainsKeywordsPredicate(Arrays.asList("Maths", "Chemistry"));
        assertTrue(predicate.test(new PersonBuilder().withSubject("Maths Chemistry").build()));

        // Only one matching keyword
        predicate = new SubjectContainsKeywordsPredicate(Arrays.asList("Chemistry", "Biology"));
        assertTrue(predicate.test(new PersonBuilder().withSubject("Chemistry Biology").build()));

        // Mixed-case keywords
        predicate = new SubjectContainsKeywordsPredicate(Arrays.asList("mAtHs", "cheMistRy"));
        assertTrue(predicate.test(new PersonBuilder().withSubject("Maths Chemistry").build()));
    }

    @Test
    public void test_subjectDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        SubjectContainsKeywordsPredicate subject = new SubjectContainsKeywordsPredicate(Arrays.asList("Biology"));
        assertFalse(subject.test(new PersonBuilder().withSubject("Maths Chemistry").build()));

        // Keywords match phone, email and address, but does not match name
        subject = new SubjectContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(subject.test(new PersonBuilder().withSubject("Maths").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }


    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        SubjectContainsKeywordsPredicate predicate = new SubjectContainsKeywordsPredicate(keywords);

        String expected = SubjectContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}

