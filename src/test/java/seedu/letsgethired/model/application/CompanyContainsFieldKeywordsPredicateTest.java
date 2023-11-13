package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.letsgethired.testutil.InternApplicationBuilder;


public class CompanyContainsFieldKeywordsPredicateTest {
    @Test
    public void equals() {
        String firstPredicateSearchString = "first";
        String secondPredicateSearchString = "second";

        Object notPredicateTest = new Object();

        CompanyContainsFieldKeywordsPredicate firstPredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, firstPredicateSearchString)));
        CompanyContainsFieldKeywordsPredicate secondPredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, secondPredicateSearchString)));

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        CompanyContainsFieldKeywordsPredicate firstPredicateCopy = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, firstPredicateSearchString)));
        assertEquals(firstPredicate, firstPredicateCopy);

        // different predicate test or other object type
        assertNotEquals(firstPredicate, notPredicateTest);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different intern application -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_fieldPartialMatch_returnsTrue() {
        // Empty search string
        CompanyContainsFieldKeywordsPredicate emptyPredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "")));
        assertTrue(emptyPredicate.test(new InternApplicationBuilder().withCompany("Google").build()));

        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "Jane")));
        assertTrue(predicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Partial match
        CompanyContainsFieldKeywordsPredicate partialMatchPredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "J S")));
        assertTrue(partialMatchPredicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));

        // Mixed case
        CompanyContainsFieldKeywordsPredicate mixedCasePredicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "jnsT")));
        assertTrue(mixedCasePredicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Non-matching
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "Google")));
        assertFalse(predicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));
    }

    @Test
    public void test_companyContainsKeywords_returnsTrue() {
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, "Jane Street")));
        assertTrue(predicate.test(new InternApplicationBuilder().withCompany("Jane Street").build()));
    }

    @Test
    public void test_statusDoesNotContainKeywords_returnFalse() {
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_STATUS, "Pending")));
        assertFalse(predicate.test(new InternApplicationBuilder().withStatus("Accepted").build()));
    }

    @Test
    public void test_statusContainsKeywords_returnsTrue() {
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_STATUS, "Pending")));
        assertTrue(predicate.test(new InternApplicationBuilder().withStatus("Pending").build()));
    }

    @Test
    public void test_roleDoesNotContainKeywords_returnFalse() {
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_ROLE, "Economist")));
        assertFalse(predicate.test(new InternApplicationBuilder().withRole("Software Engineer").build()));
    }

    @Test
    public void test_roleContainsKeywords_returnsTrue() {
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_ROLE, "Software Engineer")));
        assertTrue(predicate.test(new InternApplicationBuilder().withRole("Software Engineer").build()));
    }

    @Test
    public void test_noteDoesNotContainKeywords_returnFalse() {
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_NOTE_INSERT, "mental math")));
        assertFalse(predicate.test(new InternApplicationBuilder().withNotes("require MERN").build()));
    }

    @Test
    public void test_noteContainsKeywords_returnsTrue() {
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_NOTE_INSERT, "require MERN")));
        assertTrue(predicate.test(new InternApplicationBuilder().withNotes("require MERN").build()));
    }

    @Test
    public void test_cycleDoesNotContainKeywords_returnFalse() {
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_CYCLE, "Winter")));
        assertFalse(predicate.test(new InternApplicationBuilder().withCycle("Summer").build()));
    }

    @Test
    public void test_cycleContainsKeywords_returnsTrue() {
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_CYCLE, "Summer")));
        assertTrue(predicate.test(new InternApplicationBuilder().withCycle("Summer").build()));
    }

    @Test
    public void toStringMethod() {
        String searchStringCompany = "Jane Street";
        String searchStringCycle = "Summer";
        CompanyContainsFieldKeywordsPredicate predicate = new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, searchStringCompany),
                        new Pair<>(PREFIX_CYCLE, searchStringCycle)));

        String expected =
                CompanyContainsFieldKeywordsPredicate.class.getCanonicalName()
                        + "{keywords=[n/=" + searchStringCompany
                        + ", c/=" + searchStringCycle + "]}";
        assertEquals(expected, predicate.toString());
    }
}
