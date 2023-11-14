package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

/**
 * Contains tests for {@code CompanyNameContainsKeywordsPredicate}.
 * This class is responsible for testing the behavior of {@link CompanyNameContainsKeywordsPredicate}
 * to ensure that it correctly identifies if an {@code Internship}'s {@code CompanyName} matches any
 * of the given keywords.
 *
 * @see CompanyNameContainsKeywordsPredicate
 */
public class CompanyNameContainsKeywordsPredicateTest {

    @Test
    public void test_companyNameContainsKeywords_returnsTrue() {
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("Google"));
        Internship internship = new InternshipBuilder().withCompanyName("Google").build();
        assertTrue(predicate.test(internship));
    }

    @Test
    public void test_companyNameDoesNotContainKeywords_returnsFalse() {
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("Facebook"));
        Internship internship = new InternshipBuilder().withCompanyName("Google").build();
        assertFalse(predicate.test(internship));
    }

    @Test
    public void testMultipleKeywords_oneMatch_returnsTrue() {
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Arrays.asList("Big", "Apple"));
        Internship internship = new InternshipBuilder().withCompanyName("Big Apple").build();
        assertTrue(predicate.test(internship));
    }

    @Test
    public void testMultipleKeywords_noMatch_returnsFalse() {
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Arrays.asList("Facebook", "Apple"));
        Internship internship = new InternshipBuilder().withCompanyName("Google").build();
        assertFalse(predicate.test(internship));
    }


    @Test
    public void equals() {
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Arrays.asList("Facebook", "Apple"));
        // equals self -> returns True
        assertTrue(predicate.equals(predicate));

        // equals null -> returns False
        assertFalse(predicate.equals(null));

        // same string representation -> returns True
        assertTrue(predicate.toString().equals(
                CompanyNameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=[Facebook, Apple]}")
        );
    }

}
