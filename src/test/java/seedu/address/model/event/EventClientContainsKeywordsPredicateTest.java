package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;



public class EventClientContainsKeywordsPredicateTest {

    @Test
    public void testConstructor_validKeywords_success() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        EventClientContainsKeywordsPredicate predicate = new EventClientContainsKeywordsPredicate(keywords);
        assertNotNull(predicate);
    }

    @Test
    public void testTest_eventClientContainsKeyword_returnsTrue() {
        Event event = null;
        try {
            event = new EventBuilder().withClient(new PersonBuilder().withName("Name with keyword1").build()).build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<String> keywords = Arrays.asList("keyword1");
        EventClientContainsKeywordsPredicate predicate = new EventClientContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(event));
    }

    @Test
    public void testTest_eventClientDoesNotContainKeyword_returnsFalse() {
        Event event = null;
        try {
            event = new EventBuilder().withClient(new PersonBuilder().withName("Name without keyword").build())
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<String> keywords = Arrays.asList("keyword1");
        EventClientContainsKeywordsPredicate predicate = new EventClientContainsKeywordsPredicate(keywords);
        assertFalse(predicate.test(event));
    }

    @Test
    public void testEquals_equalPredicates_returnsTrue() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        EventClientContainsKeywordsPredicate predicate1 = new EventClientContainsKeywordsPredicate(keywords);
        EventClientContainsKeywordsPredicate predicate2 = new EventClientContainsKeywordsPredicate(keywords);
        assertEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_unequalPredicates_returnsFalse() {
        List<String> keywords1 = Arrays.asList("keyword1");
        List<String> keywords2 = Arrays.asList("keyword2");
        EventClientContainsKeywordsPredicate predicate1 = new EventClientContainsKeywordsPredicate(keywords1);
        EventClientContainsKeywordsPredicate predicate2 = new EventClientContainsKeywordsPredicate(keywords2);
        assertNotEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_differentType_returnsFalse() {
        List<String> keywords1 = Arrays.asList("keyword1");
        EventClientContainsKeywordsPredicate predicate1 = new EventClientContainsKeywordsPredicate(keywords1);
        assertNotEquals(predicate1, 1);
    }

    @Test
    public void testToString_validKeywords_returnsExpectedString() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        EventClientContainsKeywordsPredicate predicate = new EventClientContainsKeywordsPredicate(keywords);
        String expectedString = EventClientContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=[keyword1, keyword2]}";
        assertEquals(expectedString, predicate.toString());
    }
}
