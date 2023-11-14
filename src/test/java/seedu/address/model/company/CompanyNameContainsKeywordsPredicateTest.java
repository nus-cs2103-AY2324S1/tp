package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

public class CompanyNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CompanyNameContainsKeywordsPredicate firstPredicate =
                new CompanyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        CompanyNameContainsKeywordsPredicate secondPredicate =
                new CompanyNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        CompanyNameContainsKeywordsPredicate firstPredicateCopy =
                new CompanyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different company -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertTrue(predicate.test(new CompanyBuilder().withCompanyName("Apple Inc.").build()));

        // Multiple keywords
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("Apple", "Microsoft"));
        assertTrue(predicate.test(new CompanyBuilder().withCompanyName("Apple Microsoft Corp.").build()));

        // Only one matching keyword
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("Microsoft", "Google"));
        assertTrue(predicate.test(new CompanyBuilder().withCompanyName("Apple Google LLC").build()));

        // Mixed-case keywords
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("aPPle", "MiCROsoft"));
        assertTrue(predicate.test(new CompanyBuilder().withCompanyName("Apple Microsoft").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new CompanyBuilder().withCompanyName("Apple").build()));

        // Non-matching keyword
        predicate = new CompanyNameContainsKeywordsPredicate(List.of("Google"));
        assertFalse(predicate.test(new CompanyBuilder().withCompanyName("Apple Microsoft").build()));

        // Keywords match phone, email and description, but does not match name
        predicate = new CompanyNameContainsKeywordsPredicate(Arrays.asList("12345", "apple@example.com", "technology"));
        assertFalse(predicate.test(new CompanyBuilder().withCompanyName("Apple").withCompanyPhone("12345")
            .withCompanyEmail("apple@example.com").withDescription("A technology company.").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        CompanyNameContainsKeywordsPredicate predicate = new CompanyNameContainsKeywordsPredicate(keywords);

        String expected = CompanyNameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}

