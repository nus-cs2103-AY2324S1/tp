package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

public class CompanyNameAndTagContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateCompanyNameKeywordList = Collections.singletonList("first");
        List<String> secondPredicateCompanyNameKeywordList = Arrays.asList("first", "second");

        List<String> firstPredicateTagKeywordList = Collections.singletonList("tag1");
        List<String> secondPredicateTagKeywordList = Arrays.asList("tag1", "tag2");

        CompanyNameAndTagContainKeywordsPredicate firstPredicate =
            new CompanyNameAndTagContainKeywordsPredicate(firstPredicateCompanyNameKeywordList,
                firstPredicateTagKeywordList);
        CompanyNameAndTagContainKeywordsPredicate secondPredicate =
            new CompanyNameAndTagContainKeywordsPredicate(secondPredicateCompanyNameKeywordList,
                secondPredicateTagKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompanyNameAndTagContainKeywordsPredicate firstPredicateCopy =
            new CompanyNameAndTagContainKeywordsPredicate(firstPredicateCompanyNameKeywordList,
                firstPredicateTagKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_companyNameOrTagContainsKeywords_returnsTrue() {
        // One keyword in companyName
        CompanyNameAndTagContainKeywordsPredicate predicate =
            new CompanyNameAndTagContainKeywordsPredicate(Collections.singletonList("Alice"), Collections.emptyList());
        assertTrue(predicate.test(new CompanyBuilder().withCompanyName("Alice Bob").build()));

        // One keyword in tag
        predicate =
            new CompanyNameAndTagContainKeywordsPredicate(Collections.emptyList(), Collections.singletonList("tag1"));
        assertTrue(predicate.test(new CompanyBuilder().withTags("tag1").build()));

        // Multiple keywords in companyName
        predicate =
            new CompanyNameAndTagContainKeywordsPredicate(Arrays.asList("Alice", "Bob"), Collections.emptyList());
        assertTrue(predicate.test(new CompanyBuilder().withCompanyName("Alice Bob").build()));

        // Multiple keywords in tags
        predicate =
            new CompanyNameAndTagContainKeywordsPredicate(Collections.emptyList(), Arrays.asList("tag1", "tag2"));
        assertTrue(predicate.test(new CompanyBuilder().withTags("tag1", "tag2").build()));

        // Keywords in both companyName and tags
        predicate =
            new CompanyNameAndTagContainKeywordsPredicate(Arrays.asList("Alice"), Arrays.asList("tag1"));
        assertTrue(predicate.test(new CompanyBuilder().withCompanyName("Alice Bob").withTags("tag1").build()));
    }

    @Test
    public void test_companyNameAndTagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompanyNameAndTagContainKeywordsPredicate predicate =
            new CompanyNameAndTagContainKeywordsPredicate(Collections.emptyList(), Collections.emptyList());
        assertFalse(predicate.test(new CompanyBuilder().withCompanyName("Alice").build()));

        // Non-matching keyword in companyName
        predicate =
            new CompanyNameAndTagContainKeywordsPredicate(Arrays.asList("Carol"), Collections.emptyList());
        assertFalse(predicate.test(new CompanyBuilder().withCompanyName("Alice Bob").build()));

        // Non-matching keyword in tags
        predicate =
            new CompanyNameAndTagContainKeywordsPredicate(Collections.emptyList(), Arrays.asList("tag3"));
        assertFalse(predicate.test(new CompanyBuilder().withTags("tag1", "tag2").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> companyNameKeywords = List.of("keyword1", "keyword2");
        List<String> tagKeywords = List.of("tag1", "tag2");
        CompanyNameAndTagContainKeywordsPredicate predicate =
            new CompanyNameAndTagContainKeywordsPredicate(companyNameKeywords, tagKeywords);

        String expected = CompanyNameAndTagContainKeywordsPredicate.class.getCanonicalName()
            + "{companyNameKeywords=" + companyNameKeywords + ", tagKeywords=" + tagKeywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
