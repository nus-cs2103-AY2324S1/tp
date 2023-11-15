package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CourseContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("MA21001");
        List<String> secondPredicateKeywordList = Arrays.asList("MA2001", "CS2030");

        CourseContainsKeywordsPredicate firstPredicate = new CourseContainsKeywordsPredicate(firstPredicateKeywordList);
        CourseContainsKeywordsPredicate secondPredicate =
                new CourseContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CourseContainsKeywordsPredicate firstPredicateCopy =
                new CourseContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals("blah"));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_courseContainsKeywords_returnsTrue() {
        // One keyword
        CourseContainsKeywordsPredicate predicate = new CourseContainsKeywordsPredicate(Collections
                .singletonList("MA2001"));
        assertTrue(predicate.test(new PersonBuilder().withCourses("MA2001").build()));

        // Only one matching keyword
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("MA2001", "CS2030"));
        assertTrue(predicate.test(new PersonBuilder().withCourses("CS2030").build()));

        // Mixed-case keywords
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("mA2001", "cs2030"));
        assertTrue(predicate.test(new PersonBuilder().withCourses("MA2001").build()));
    }

    @Test
    public void test_courseDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CourseContainsKeywordsPredicate predicate =
                new CourseContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withCourses("MA2001").build()));

        // Non-matching keyword
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("MA2001"));
        assertFalse(predicate.test(new PersonBuilder().withCourses("CS2030").build()));

        // Keywords match name, phone, email and address, but does not match course
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("Alice", "12345",
                                                                          "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withCourses("MA2001").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("KEYWORD1", "KEYWORD2");
        CourseContainsKeywordsPredicate predicate = new CourseContainsKeywordsPredicate(keywords);

        String expected = CourseContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
