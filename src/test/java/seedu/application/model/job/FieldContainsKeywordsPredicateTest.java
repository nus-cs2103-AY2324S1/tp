package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.model.job.Company.COMPANY_SPECIFIER;
import static seedu.application.model.job.Role.ROLE_SPECIFIER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.JobBuilder;

public class FieldContainsKeywordsPredicateTest {

    public static final String INVALID_SPECIFIER = "Scndkcjnkcsjn";

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FieldContainsKeywordsPredicate firstPredicate = new FieldContainsKeywordsPredicate(
                ROLE_SPECIFIER, firstPredicateKeywordList);
        FieldContainsKeywordsPredicate secondPredicate = new FieldContainsKeywordsPredicate(
                ROLE_SPECIFIER, secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        FieldContainsKeywordsPredicate firstPredicateCopy =
                new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different predicate -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void isValidSpecifier() {
        // valid specifier -> does not throw error
        assertTrue(FieldContainsKeywordsPredicate.isValidSpecifier(COMPANY_SPECIFIER));
        assertTrue(FieldContainsKeywordsPredicate.isValidSpecifier(ROLE_SPECIFIER));

        // invalid specifier -> throws error
        assertFalse(FieldContainsKeywordsPredicate.isValidSpecifier(INVALID_SPECIFIER));
    }

    @Test
    public void test_roleContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                ROLE_SPECIFIER, Collections.singletonList("Software"));
        assertTrue(predicate.test(new JobBuilder().withRole("Software Engineer").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Arrays.asList("Software", "Engineer"));
        assertTrue(predicate.test(new JobBuilder().withRole("Software Engineer").build()));

        // Only one matching keyword
        predicate = new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Arrays.asList("Mechanical", "Engineer"));
        assertTrue(predicate.test(new JobBuilder().withRole("Software Engineer").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Arrays.asList("sOFtwaRe", "EnGIneer"));
        assertTrue(predicate.test(new JobBuilder().withRole("Software Engineer").build()));
    }

    @Test
    public void test_roleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                ROLE_SPECIFIER, Collections.emptyList());
        assertFalse(predicate.test(new JobBuilder().withRole("Engineer").build()));

        // Non-matching keyword
        predicate = new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Arrays.asList("Chef"));
        assertFalse(predicate.test(new JobBuilder().withRole("Software Engineer").build()));

        // Keywords match company, but does not match role
        predicate = new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Arrays.asList("Google"));
        assertFalse(predicate.test(new JobBuilder().withRole("Software Engineer").withCompany("Google").build()));
    }

    @Test
    public void test_companyContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                COMPANY_SPECIFIER, Collections.singletonList("Google"));
        assertTrue(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(COMPANY_SPECIFIER, Arrays.asList("Google", "Singapore"));
        assertTrue(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));

        // Only one matching keyword
        predicate = new FieldContainsKeywordsPredicate(COMPANY_SPECIFIER, Arrays.asList("Google", "Malaysia"));
        assertTrue(predicate.test(new JobBuilder().withCompany("Google Malaysia").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(COMPANY_SPECIFIER, Arrays.asList("gOOgLe", "SinGApOre"));
        assertTrue(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                COMPANY_SPECIFIER, Collections.emptyList());
        assertFalse(predicate.test(new JobBuilder().withCompany("Google").build()));

        // Non-matching keyword
        predicate = new FieldContainsKeywordsPredicate(COMPANY_SPECIFIER, Arrays.asList("Apple"));
        assertFalse(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));

        // Keywords match role, but does not match company
        predicate = new FieldContainsKeywordsPredicate(COMPANY_SPECIFIER, Arrays.asList("Software"));
        assertFalse(predicate.test(new JobBuilder().withRole("Software Engineer").withCompany("Google").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, keywords);
        String expected = FieldContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
