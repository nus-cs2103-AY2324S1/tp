package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.application.model.job.JobType.JobTypes;
import seedu.application.model.job.Status.JobStatus;
import seedu.application.testutil.JobBuilder;

public class FieldContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FieldContainsKeywordsPredicate firstPredicate = new FieldContainsKeywordsPredicate(
                PREFIX_ROLE, firstPredicateKeywordList);
        FieldContainsKeywordsPredicate secondPredicate = new FieldContainsKeywordsPredicate(
                PREFIX_ROLE, secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        FieldContainsKeywordsPredicate firstPredicateCopy =
                new FieldContainsKeywordsPredicate(PREFIX_ROLE, firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(firstPredicate, 5.0f);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different predicate -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void getPreamblePredicate() {
        List<String> oneKeyword = Arrays.asList("one");
        List<String> oneAndTwoKeywords = Arrays.asList("one", "two");

        // One keyword
        assertTrue(FieldContainsKeywordsPredicate.getPreamblePredicate(oneKeyword).test(
                new JobBuilder().withCompany("one").build()));

        // Multiple keywords
        assertTrue(FieldContainsKeywordsPredicate.getPreamblePredicate(oneAndTwoKeywords).test(
                new JobBuilder().withCompany("two").withRole("one").build()));
    }

    @Test
    public void test_companyContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_COMPANY, Collections.singletonList("Google"));
        assertTrue(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_COMPANY, Arrays.asList("Google", "Singapore"));
        assertTrue(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_COMPANY, Arrays.asList("gOOgLe", "SinGApOre"));
        assertTrue(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_COMPANY, Arrays.asList("Google", "Malaysia"));
        assertFalse(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));

        // Non-matching keyword
        predicate = new FieldContainsKeywordsPredicate(PREFIX_COMPANY, Arrays.asList("Apple"));
        assertFalse(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));

        // Keywords match role, but does not match company
        predicate = new FieldContainsKeywordsPredicate(PREFIX_COMPANY, Arrays.asList("Software"));
        assertFalse(predicate.test(new JobBuilder().withRole("Software Engineer").withCompany("Google").build()));
    }

    @Test
    public void test_roleContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_ROLE, Collections.singletonList("Software"));
        assertTrue(predicate.test(new JobBuilder().withRole("Software Engineer").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_ROLE, Arrays.asList("Software", "Engineer"));
        assertTrue(predicate.test(new JobBuilder().withRole("Software Engineer").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_ROLE, Arrays.asList("sOFtwaRe", "EnGIneer"));
        assertTrue(predicate.test(new JobBuilder().withRole("Software Engineer").build()));
    }

    @Test
    public void test_roleDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword
        FieldContainsKeywordsPredicate predicate =
                new FieldContainsKeywordsPredicate(PREFIX_ROLE, Arrays.asList("Mechanical", "Engineer"));
        assertFalse(predicate.test(new JobBuilder().withRole("Software Engineer").build()));

        // Non-matching keyword
        predicate = new FieldContainsKeywordsPredicate(PREFIX_ROLE, Arrays.asList("Chef"));
        assertFalse(predicate.test(new JobBuilder().withRole("Software Engineer").build()));

        // Keywords match company, but does not match role
        predicate = new FieldContainsKeywordsPredicate(PREFIX_ROLE, Arrays.asList("Google"));
        assertFalse(predicate.test(new JobBuilder().withRole("Software Engineer").withCompany("Google").build()));
    }



    @Test
    public void test_statusContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_STATUS, Collections.singletonList(JobStatus.PENDING.toString()));
        assertTrue(predicate.test(new JobBuilder().withStatus(JobStatus.PENDING.toString()).build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_STATUS, Arrays.asList("pEnDing"));
        assertTrue(predicate.test(new JobBuilder().withStatus(JobStatus.PENDING.toString()).build()));
    }

    @Test
    public void test_statusDoesNotContainKeywords_returnsFalse() {
        // Multiple keywords
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_STATUS, Arrays.asList(JobStatus.PENDING.toString(), JobStatus.APPROVED.toString()));
        assertFalse(predicate.test(new JobBuilder().withStatus(JobStatus.PENDING.toString()).build()));

        // Non-matching keyword
        predicate = new FieldContainsKeywordsPredicate(PREFIX_STATUS, Arrays.asList("Apple"));
        assertFalse(predicate.test(new JobBuilder().withStatus(JobStatus.PENDING.toString()).build()));

        // Keywords match company, but does not match status
        predicate = new FieldContainsKeywordsPredicate(PREFIX_STATUS, Arrays.asList(JobStatus.PENDING.toString()));
        assertFalse(predicate.test(new JobBuilder()
                .withRole(JobStatus.APPROVED.toString())
                .withCompany(JobStatus.PENDING.toString()).build()));
    }

    @Test
    public void test_industryContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_INDUSTRY, Collections.singletonList("Tech"));
        assertTrue(predicate.test(new JobBuilder().withIndustry("Tech").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_INDUSTRY, Arrays.asList("Food", "Science"));
        assertTrue(predicate.test(new JobBuilder().withIndustry("Food Science").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_INDUSTRY, Arrays.asList("fOOd", "sCIEnce"));
        assertTrue(predicate.test(new JobBuilder().withIndustry("Food Science").build()));
    }

    @Test
    public void test_industryDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_INDUSTRY, Arrays.asList("Food", "Science"));
        assertFalse(predicate.test(new JobBuilder().withIndustry("Computer Science").build()));

        // Non-matching keyword
        predicate = new FieldContainsKeywordsPredicate(PREFIX_INDUSTRY, Arrays.asList("Apple"));
        assertFalse(predicate.test(new JobBuilder().withIndustry("Food Science").build()));

        // Keywords match role, but does not match industry
        predicate = new FieldContainsKeywordsPredicate(PREFIX_INDUSTRY, Arrays.asList("Food"));
        assertFalse(predicate.test(new JobBuilder().withRole("Food Science").withIndustry("Computer Science").build()));
    }

    @Test
    public void test_deadlineContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_DEADLINE, Collections.singletonList("Dec"));
        assertTrue(predicate.test(new JobBuilder().withDeadline("Dec 01 2030 1200").build()));

        // Multiple keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_DEADLINE, Arrays.asList("Dec", "01"));
        assertTrue(predicate.test(new JobBuilder().withDeadline("Dec 01 2030 1200").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_DEADLINE, Arrays.asList("deC", "01"));
        assertTrue(predicate.test(new JobBuilder().withDeadline("Dec 01 2030 1200").build()));
    }

    @Test
    public void test_deadlineDoesNotContainKeywords_returnsFalse() {
        // Only one matching keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_COMPANY, Arrays.asList("Sep", "01"));
        assertFalse(predicate.test(new JobBuilder().withDeadline("Dec 01 2030 1200").build()));

        // Non-matching keyword
        predicate = new FieldContainsKeywordsPredicate(PREFIX_DEADLINE, Arrays.asList("Sep"));
        assertFalse(predicate.test(new JobBuilder().withDeadline("Dec 01 2030 1200").build()));

        // Keywords match company, but does not match deadline
        predicate = new FieldContainsKeywordsPredicate(PREFIX_DEADLINE, Arrays.asList("Dec"));
        assertFalse(predicate.test(new JobBuilder().withDeadline("Sep 01 2030 1200").withCompany("Dec").build()));
    }

    @Test
    public void test_jobTypeContainsKeywords_returnsTrue() {
        // One keyword
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_COMPANY, Collections.singletonList("Google"));
        assertTrue(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));

        // Mixed-case keywords
        predicate = new FieldContainsKeywordsPredicate(PREFIX_COMPANY, Arrays.asList("gOOgLe", "SinGApOre"));
        assertTrue(predicate.test(new JobBuilder().withCompany("Google Singapore").build()));
    }

    @Test
    public void test_jobTypeDoesNotContainKeywords_returnsFalse() {
        // Multiple keywords
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(
                PREFIX_JOB_TYPE, Arrays.asList(JobTypes.CONTRACT.toString(), JobTypes.FREELANCE.toString()));
        assertFalse(predicate.test(new JobBuilder().withJobType(JobTypes.CONTRACT.toString()).build()));

        // Non-matching keyword
        predicate = new FieldContainsKeywordsPredicate(PREFIX_JOB_TYPE, Arrays.asList(JobTypes.FREELANCE.toString()));
        assertFalse(predicate.test(new JobBuilder().withJobType(JobTypes.CONTRACT.toString()).build()));

        // Keywords match role, but does not match company
        predicate = new FieldContainsKeywordsPredicate(PREFIX_JOB_TYPE, Arrays.asList(JobTypes.FREELANCE.toString()));
        assertFalse(predicate.test(new JobBuilder()
                .withRole(JobTypes.FREELANCE.toString())
                .withCompany(JobTypes.CONTRACT.toString()).build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(PREFIX_COMPANY, keywords);
        String expected = FieldContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
