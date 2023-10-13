package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate("T11",
                null, "first", null, null, null);
        PersonContainsKeywordsPredicate secondPredicate = new PersonContainsKeywordsPredicate(null,
                "second@example.com", "second", "12345678", "A099999X", "test");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate("T11",
                null, "first", null, null, null);
        PersonContainsKeywordsPredicate secondPredicateCopy = new PersonContainsKeywordsPredicate(null,
                "second@example.com", "second", "12345678", "A099999X", "test");
        assertTrue(firstPredicate.equals(firstPredicateCopy));
        assertTrue(secondPredicate.equals(secondPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personContainsKeywords_returnsTrue() {
        // One keyword
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(null,
                null, "Alice", null, null, null);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
        predicate = new PersonContainsKeywordsPredicate("T100",
                null, null, null, null, null);
        assertTrue(predicate.test(new PersonBuilder().withClassNumber("T100").build()));

        // Multiple keywords
        predicate = new PersonContainsKeywordsPredicate(PersonBuilder.DEFAULT_CLASSNUMBER,
                null, "Alice", PersonBuilder.DEFAULT_PHONE, null, "tag2");
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withTags("tag1", "tag2").build()));

        // Mixed-case keywords
        predicate = new PersonContainsKeywordsPredicate(null,
                null, "aLiCe", null, null, "TeSttAG");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("testTag").build()));

        // No keywords (The expected behaviour is to return true)
        // Example command: lookup c/ (Which means lookup all classes)
        predicate = new PersonContainsKeywordsPredicate(null, null,
                null, null, null, null);
        assertTrue(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void test_personDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(null,
                null, "Carol", null, null, null);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match everything except for Name
        predicate = new PersonContainsKeywordsPredicate("T11", "alice@email.com",
                "Carol", "12345", "A02481972A", "testTag");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withStudentNumber("A02481972A")
                .withClassNumber("T11").withTags("testTag").build()));
    }

    @Test
    public void toStringMethod() {
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(null, null, "keyword1",
                null, "keyword2", "keyword3");

        String expected = PersonContainsKeywordsPredicate.class.getCanonicalName()
                + "{classNumber=Optional.empty, email=Optional.empty, name=Optional[keyword1], "
                + "phone=Optional.empty, studentNumber=Optional[keyword2], tag=Optional[keyword3]}";
        assertEquals(expected, predicate.toString());
    }
}
