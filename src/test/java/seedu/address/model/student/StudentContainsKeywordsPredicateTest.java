package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentContainsKeywordsPredicateTest {

    @Test
    public void equals() {

        StudentContainsKeywordsPredicate firstPredicate = new StudentContainsKeywordsPredicate("T11",
                null, "first", null, null, null);
        StudentContainsKeywordsPredicate secondPredicate = new StudentContainsKeywordsPredicate(null,
                "second@example.com", "second", "12345678", "A099999X", "test");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentContainsKeywordsPredicate firstPredicateCopy = new StudentContainsKeywordsPredicate("T11",
                null, "first", null, null, null);
        StudentContainsKeywordsPredicate secondPredicateCopy = new StudentContainsKeywordsPredicate(null,
                "second@example.com", "second", "12345678", "A099999X", "test");
        assertTrue(firstPredicate.equals(firstPredicateCopy));
        assertTrue(secondPredicate.equals(secondPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personContainsKeywords_returnsTrue() {
        // One keyword
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(null,
                null, "Alice", null, null, null);
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
        predicate = new StudentContainsKeywordsPredicate("T100",
                null, null, null, null, null);
        assertTrue(predicate.test(new StudentBuilder().withClassNumber("T100").build()));

        // Multiple keywords
        predicate = new StudentContainsKeywordsPredicate(StudentBuilder.DEFAULT_CLASS_NUMBER,
                null, "Alice", StudentBuilder.DEFAULT_PHONE, null, "tag2");
        assertTrue(predicate.test(new StudentBuilder()
                .withName("Alice Bob")
                .withTags("tag1", "tag2").build()));

        // Mixed-case keywords
        predicate = new StudentContainsKeywordsPredicate(null,
                null, "aLiCe", null, null, "TeSttAG");
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").withTags("testTag").build()));

        // No keywords (The expected behaviour is to return true)
        // Example command: lookup c/ (Which means lookup all classes)
        predicate = new StudentContainsKeywordsPredicate(null, null,
                null, null, null, null);
        assertTrue(predicate.test(new StudentBuilder().build()));
    }

    @Test
    public void test_personDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(null,
                null, "Carol", null, null, null);
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Keywords match everything except for Name
        predicate = new StudentContainsKeywordsPredicate("T11", "alice@email.com",
                "Carol", "12345", "A02481972A", "testTag");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withStudentNumber("A02481972A")
                .withClassNumber("T11").withTags("testTag").build()));
    }

    @Test
    public void toStringMethod() {
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(null, null, "keyword1",
                null, "keyword2", "keyword3");

        String expected = StudentContainsKeywordsPredicate.class.getCanonicalName()
                + "{classNumber=Optional.empty, email=Optional.empty, name=Optional[keyword1], "
                + "phone=Optional.empty, studentNumber=Optional[keyword2], tag=Optional[keyword3]}";
        assertEquals(expected, predicate.toString());
    }
}
