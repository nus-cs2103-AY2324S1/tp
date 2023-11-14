package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CommissionBuilder;
class ClientNameContainsKeywordsPredicateTest {
    @Test
    public void testConstructor_validKeywords_success() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        ClientNameContainsKeywordsPredicate predicate = new ClientNameContainsKeywordsPredicate(keywords);
        assertNotNull(predicate);
    }

    @Test
    public void testTest_financeClientIsNull_returnsFalse() {
        Finance finance = new Expense(
                new Amount("10"),
                null,
                new Description("Test"),
                new TimeDue(LocalDateTime.now())
        );
        List<String> keywords = Arrays.asList("John");
        ClientNameContainsKeywordsPredicate predicate = new ClientNameContainsKeywordsPredicate(keywords);
        assertFalse(predicate.test(finance));
    }
    @Test
    public void testTest_clientNameContainsKeyword_returnsTrue() {
        Finance finance = new CommissionBuilder().withPerson("John Doe").build();
        List<String> keywords = Arrays.asList("John");
        ClientNameContainsKeywordsPredicate predicate = new ClientNameContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(finance));
    }

    @Test
    public void testTest_eventNameDoesNotContainKeyword_returnsFalse() {
        Finance finance = new CommissionBuilder().withPerson("John Doe").build();
        List<String> keywords = Arrays.asList("Adam");
        ClientNameContainsKeywordsPredicate predicate = new ClientNameContainsKeywordsPredicate(keywords);
        assertFalse(predicate.test(finance));
    }

    @Test
    public void testEquals_differentPredicateType_returnsFalse() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        ClientNameContainsKeywordsPredicate predicate1 = new ClientNameContainsKeywordsPredicate(keywords);
        Predicate<Finance> predicate2 = null;
        assertNotEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_equalPredicates_returnsTrue() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        ClientNameContainsKeywordsPredicate predicate1 = new ClientNameContainsKeywordsPredicate(keywords);
        ClientNameContainsKeywordsPredicate predicate2 = new ClientNameContainsKeywordsPredicate(keywords);
        assertEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_unequalPredicates_returnsFalse() {
        List<String> keywords1 = Arrays.asList("keyword1");
        List<String> keywords2 = Arrays.asList("keyword2");
        ClientNameContainsKeywordsPredicate predicate1 = new ClientNameContainsKeywordsPredicate(keywords1);
        ClientNameContainsKeywordsPredicate predicate2 = new ClientNameContainsKeywordsPredicate(keywords2);
        assertNotEquals(predicate1, predicate2);
    }

    @Test
    public void testToString_validKeywords_returnsExpectedString() {
        List<String> keywords = Arrays.asList("keyword1", "keyword2");
        ClientNameContainsKeywordsPredicate predicate = new ClientNameContainsKeywordsPredicate(keywords);
        String expectedString = ClientNameContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=[keyword1, keyword2]}";
        assertEquals(expectedString, predicate.toString());
    }
}

