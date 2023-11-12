package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeyword);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate("friend");
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "colleague").build()));

        // Trailing and Leading white spaces
        predicate = new TagContainsKeywordsPredicate("   friend    ");
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "colleague").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate("fRiEnd");
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "colleague").build()));

        // Partial keyword
        predicate = new TagContainsKeywordsPredicate("fri");
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "colleague").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate("family");
        assertFalse(predicate.test(new PersonBuilder().withTags("friend", "colleague").build()));

        // Keyword only matches word that is separated by white space
        predicate = new TagContainsKeywordsPredicate("friend colleague");
        assertFalse(predicate.test(new PersonBuilder().withTags("friend", "colleague").build()));

        // Keyword do not match exact
        predicate = new TagContainsKeywordsPredicate("friends");
        assertFalse(predicate.test(new PersonBuilder().withTags("friend", "colleague").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(keyword);

        String expected = TagContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=[" + keyword + "]}";
        assertEquals(expected, predicate.toString());
    }
}
