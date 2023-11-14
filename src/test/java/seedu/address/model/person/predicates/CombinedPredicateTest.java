package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CombinedPredicateTest {
    @Test
    public void constructor_containsNull_throwsException() {
        assertThrows(NullPointerException.class, () ->
                new CombinedPredicate(new FinancialPlanContainsKeywordsPredicate(Arrays.asList("foo")),
                        null));
    }
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CombinedPredicate firstPredicate =
                new CombinedPredicate(new FinancialPlanContainsKeywordsPredicate(firstPredicateKeywordList));
        CombinedPredicate secondPredicate =
                new CombinedPredicate(new FinancialPlanContainsKeywordsPredicate(secondPredicateKeywordList));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CombinedPredicate firstPredicateCopy =
                new CombinedPredicate(new FinancialPlanContainsKeywordsPredicate(firstPredicateKeywordList));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different financial plan -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personContainsKeywords_returnsTrue() {
        // One keyword
        CombinedPredicate predicate =
                new CombinedPredicate(
                        new FinancialPlanContainsKeywordsPredicate(Collections.singletonList("Senior")));
        assertTrue(predicate.test(new PersonBuilder().withFinancialPlans("Senior").build()));

        // Multiple keywords for same person
        predicate = new CombinedPredicate(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords in different fields
        predicate = new CombinedPredicate(
                new FinancialPlanContainsKeywordsPredicate(Collections.singletonList("Senior")),
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withFinancialPlans("Senior").build()));

        // Only one matching keyword
        predicate = new CombinedPredicate(
                new FinancialPlanContainsKeywordsPredicate(Collections.singletonList("Senior")),
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice")));
        assertTrue(predicate.test(new PersonBuilder().withFinancialPlans("Senior", "Koopa").build()));

        // Mixed-case keywords
        predicate = new CombinedPredicate(
                new FinancialPlanContainsKeywordsPredicate(Collections.singletonList("chILD")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withFinancialPlans("Child Premium").build()));
    }

    @Test
    public void test_personDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CombinedPredicate predicate = new CombinedPredicate();
        assertFalse(predicate.test(new PersonBuilder().withFinancialPlans("Child").build()));

        // Non-matching keyword
        predicate = new CombinedPredicate(new FinancialPlanContainsKeywordsPredicate(Arrays.asList("Senior")));
        assertFalse(predicate.test(new PersonBuilder().withFinancialPlans("Child Premium").build()));

        // Keywords match phone and address, but does not match financial plans
        predicate = new CombinedPredicate(new FinancialPlanContainsKeywordsPredicate(
                Arrays.asList("12345", "Main", "Street")));
        assertFalse(predicate.test(new PersonBuilder().withFinancialPlans("Child").withPhone("12345")
                .withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate = new NameContainsKeywordsPredicate(keywords);
        FinancialPlanContainsKeywordsPredicate financialPlanContainsKeywordsPredicate =
                new FinancialPlanContainsKeywordsPredicate(keywords);
        CombinedPredicate predicate = new CombinedPredicate(nameContainsKeywordsPredicate,
                financialPlanContainsKeywordsPredicate);

        String expected = CombinedPredicate.class.getCanonicalName() + "{"
                + nameContainsKeywordsPredicate.getClass().getCanonicalName()
                + "=" + nameContainsKeywordsPredicate + ", "
                + financialPlanContainsKeywordsPredicate.getClass().getCanonicalName()
                + "=" + financialPlanContainsKeywordsPredicate
                + "}";
        assertEquals(expected, predicate.toString());
    }
}
