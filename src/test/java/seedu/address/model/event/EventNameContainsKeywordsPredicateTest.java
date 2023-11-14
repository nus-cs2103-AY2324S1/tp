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

public class EventNameContainsKeywordsPredicateTest {

    @Test
    public void testConstructor_validKeywords_success() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        EventNameContainsKeywordsPredicate predicate = new EventNameContainsKeywordsPredicate(keywords);
        assertNotNull(predicate);
    }

    @Test
    public void testTest_eventNameContainsKeyword_returnsTrue() throws ParseException {
        Event event = new EventBuilder().withName("Event with keyword1").build();
        List<String> keywords = Arrays.asList("keyword1");
        EventNameContainsKeywordsPredicate predicate = new EventNameContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(event));
    }

    @Test
    public void testTest_eventNameDoesNotContainKeyword_returnsFalse() throws ParseException {
        Event event = new EventBuilder().withName("Event without keyword").build();
        List<String> keywords = Arrays.asList("keyword1");
        EventNameContainsKeywordsPredicate predicate = new EventNameContainsKeywordsPredicate(keywords);
        assertFalse(predicate.test(event));
    }

    @Test
    public void testEquals_equalPredicates_returnsTrue() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        EventNameContainsKeywordsPredicate predicate1 = new EventNameContainsKeywordsPredicate(keywords);
        EventNameContainsKeywordsPredicate predicate2 = new EventNameContainsKeywordsPredicate(keywords);
        assertEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_unequalPredicates_returnsFalse() {
        List<String> keywords1 = Arrays.asList("keyword1");
        List<String> keywords2 = Arrays.asList("keyword2");
        EventNameContainsKeywordsPredicate predicate1 = new EventNameContainsKeywordsPredicate(keywords1);
        EventNameContainsKeywordsPredicate predicate2 = new EventNameContainsKeywordsPredicate(keywords2);
        assertNotEquals(predicate1, predicate2);
    }

    @Test
    public void testToString_validKeywords_returnsExpectedString() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        EventNameContainsKeywordsPredicate predicate = new EventNameContainsKeywordsPredicate(keywords);
        String expectedString = EventNameContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=[keyword1, keyword2]}";
        assertEquals(expectedString, predicate.toString());
    }
}
