package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CompanyContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CompanyContainsKeywordsPredicate firstPredicate =
                new CompanyContainsKeywordsPredicate(firstPredicateKeywordList);
        CompanyContainsKeywordsPredicate secondPredicate =
                new CompanyContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompanyContainsKeywordsPredicate firstPredicateCopy =
                new CompanyContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_companyContainsKeywords_returnsTrue() {
        // One keyword
        CompanyContainsKeywordsPredicate predicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("Google"));
        assertTrue(predicate.test(new PersonBuilder().withCompany("Google").build()));

        // Multiple keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Google", "Shopee"));
        assertTrue(predicate.test(new PersonBuilder().withCompany("Google Shopee").build()));

        // Only one matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Shopee", "Citadel"));
        assertTrue(predicate.test(new PersonBuilder().withCompany("Google Citadel").build()));

        // Mixed-case keywords
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("goOGle", "shOpeE"));
        assertTrue(predicate.test(new PersonBuilder().withCompany("Google Shopee").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withCompany("Google").build()));

        // Non-matching keyword
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Citadel"));
        assertFalse(predicate.test(new PersonBuilder().withCompany("Google Shopee").build()));

        // Keywords match name, phone, email, and address, but does not match company
        predicate = new CompanyContainsKeywordsPredicate(Arrays.asList("Alice", "12345",
                "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withCompany("Google").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(keywords);

        String expected = CompanyContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
