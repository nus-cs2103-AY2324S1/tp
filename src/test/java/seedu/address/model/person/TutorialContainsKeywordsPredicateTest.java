package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TutorialContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TutorialContainsKeywordsPredicate firstPredicate =
                new TutorialContainsKeywordsPredicate(firstPredicateKeywordList);
        TutorialContainsKeywordsPredicate secondPredicate =
                new TutorialContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorialContainsKeywordsPredicate firstPredicateCopy =
                new TutorialContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tutorialContainsKeywords_returnsTrue() {
        // One keyword
        TutorialContainsKeywordsPredicate predicate =
                new TutorialContainsKeywordsPredicate(Collections.singletonList("CS2103T/F08"));
        assertTrue(predicate.test(new PersonBuilder().withCoursesAndTutorials(VALID_TUTORIAL_1).build()));

        // Only one matching keyword
        predicate = new TutorialContainsKeywordsPredicate(Arrays.asList("CS2103T/F08", "CS2100/T32"));
        assertTrue(predicate.test(new PersonBuilder()
                .withCoursesAndTutorials(VALID_TUTORIAL_1, VALID_TUTORIAL_3).build()));

        // Mixed-case keywords
        predicate = new TutorialContainsKeywordsPredicate(Arrays.asList("Cs2103t/F08", "cs2100/T32"));
        assertTrue(predicate.test(new PersonBuilder()
                .withCoursesAndTutorials(VALID_TUTORIAL_1, VALID_TUTORIAL_2).build()));

        // Multiple keywords
        predicate = new TutorialContainsKeywordsPredicate(Arrays.asList("CS2103T/F08", "CS2100/T32"));
        assertTrue(predicate.test(new PersonBuilder()
                .withCoursesAndTutorials(VALID_TUTORIAL_1, VALID_TUTORIAL_2).build()));

    }

    @Test
    public void test_tutorialDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TutorialContainsKeywordsPredicate predicate = new TutorialContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withCoursesAndTutorials(VALID_TUTORIAL_1).build()));

        // Non-matching keyword
        predicate = new TutorialContainsKeywordsPredicate(Arrays.asList("CS2100/T32"));
        assertFalse(predicate.test(new PersonBuilder()
                .withCoursesAndTutorials(VALID_TUTORIAL_1, VALID_TUTORIAL_3).build()));

        // Keywords match phone, email and address, but does not match tutorial
        predicate = new TutorialContainsKeywordsPredicate(Arrays.asList("Student", "alice@email.com",
                VALID_COURSE_1.courseName, VALID_TUTORIAL_1.tutorialName));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withRoles("Student")
                .withContacts("alice@email.com").withCoursesAndTutorials(VALID_TUTORIAL_2).build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TutorialContainsKeywordsPredicate predicate = new TutorialContainsKeywordsPredicate(keywords);

        String expected = TutorialContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
