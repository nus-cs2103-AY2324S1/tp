package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

/**
 * Contains tests for {@code RequirementContainsKeywordsPredicate}.
 * This class is responsible for testing the behavior of {@link RequirementContainsKeywordsPredicate}
 * to ensure that it correctly identifies if an {@code Internship}'s {@code Requirement} matches any
 * of the given keywords.
 *
 * @see RequirementContainsKeywordsPredicate
 */
public class RequirementContainsKeywordsPredicateTest {

    @Test
    public void test_requirementContainsKeywords_returnsTrue() {
        RequirementContainsKeywordsPredicate predicate =
                new RequirementContainsKeywordsPredicate(Collections.singletonList("Java"));
        Internship internship = new InternshipBuilder().withRequirements("Java", "Python").build();
        assertTrue(predicate.test(internship));
    }

    @Test
    public void test_requirementDoesNotContainKeywords_returnsFalse() {
        RequirementContainsKeywordsPredicate predicate =
                new RequirementContainsKeywordsPredicate(Collections.singletonList("JavaScript"));
        Internship internship = new InternshipBuilder().withRequirements("Java", "Python").build();
        assertFalse(predicate.test(internship));
    }

    @Test
    public void testMultipleKeywords_oneMatch_returnsTrue() {
        RequirementContainsKeywordsPredicate predicate =
                new RequirementContainsKeywordsPredicate(Arrays.asList("Java", "C++"));
        Internship internship = new InternshipBuilder().withRequirements("Java", "C++", "Python").build();
        assertTrue(predicate.test(internship));
    }

    @Test
    public void testMultipleKeywords_noMatch_returnsFalse() {
        RequirementContainsKeywordsPredicate predicate =
                new RequirementContainsKeywordsPredicate(Arrays.asList("JavaScript", "C++"));
        Internship internship = new InternshipBuilder().withRequirements("Java", "Python").build();
        assertFalse(predicate.test(internship));
    }

    @Test
    public void equals() {
        RequirementContainsKeywordsPredicate predicate =
                new RequirementContainsKeywordsPredicate(Arrays.asList("JavaScript", "C++"));
        assertTrue(predicate.equals(predicate));
        assertFalse(predicate.equals(null));
        assertTrue(predicate.toString().equals(
                RequirementContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=[JavaScript, C++]}")
        );
    }
}
