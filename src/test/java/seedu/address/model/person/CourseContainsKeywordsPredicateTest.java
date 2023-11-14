package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CourseContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CourseContainsKeywordsPredicate firstPredicate =
                new CourseContainsKeywordsPredicate(firstPredicateKeywordList);
        CourseContainsKeywordsPredicate secondPredicate =
                new CourseContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CourseContainsKeywordsPredicate firstPredicateCopy =
                new CourseContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_courseContainsKeywords_returnsTrue() {
        // One keyword
        CourseContainsKeywordsPredicate predicate =
                new CourseContainsKeywordsPredicate(Collections.singletonList("CS2103T"));
        assertTrue(predicate.test(new PersonBuilder().withCourses(new Course("CS2103T")).build()));

        // Only one matching keyword
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("CS2103T", "CS1111"));
        assertTrue(predicate.test(new PersonBuilder().withCourses(new Course("CS2103T"),
                new Course("CS1111")).build()));

        // Mixed-case keywords
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("cs2103T"));
        assertTrue(predicate.test(new PersonBuilder().withCourses(new Course("cs2103T")).build()));

        // Multiple keywords
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("CS2103T", "CS2100"));
        assertTrue(predicate.test(new PersonBuilder().withCourses(new Course("CS2103T"),
                new Course("CS2100")).build()));

    }

    @Test
    public void test_courseDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CourseContainsKeywordsPredicate predicate = new CourseContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withCourses(new Course("CS2103T")).build()));

        // Non-matching keyword
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("C2111"));
        assertFalse(predicate.test(new PersonBuilder().withCourses(new Course("CS2103T")).build()));

        // Keywords match phone, email and address, but does not match course
        predicate = new CourseContainsKeywordsPredicate(Arrays.asList("Student", "alice@email.com",
                VALID_COURSE_1.courseName, VALID_TUTORIAL_1.tutorialName));
        assertFalse(predicate.test(new PersonBuilder().withRoles("Student")
                .withContacts("alice@email.com").withCoursesAndTutorials(VALID_TUTORIAL_2).build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        CourseContainsKeywordsPredicate predicate = new CourseContainsKeywordsPredicate(keywords);

        String expected = CourseContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
