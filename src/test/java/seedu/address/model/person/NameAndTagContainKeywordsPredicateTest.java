package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameAndTagContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateNameKeywordList = Collections.singletonList("first");
        List<String> secondPredicateNameKeywordList = Arrays.asList("first", "second");

        List<String> firstPredicateTagKeywordList = Collections.singletonList("tag1");
        List<String> secondPredicateTagKeywordList = Arrays.asList("tag1", "tag2");

        NameAndTagContainKeywordsPredicate firstPredicate =
            new NameAndTagContainKeywordsPredicate(firstPredicateNameKeywordList, firstPredicateTagKeywordList);
        NameAndTagContainKeywordsPredicate secondPredicate =
            new NameAndTagContainKeywordsPredicate(secondPredicateNameKeywordList, secondPredicateTagKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameAndTagContainKeywordsPredicate firstPredicateCopy =
            new NameAndTagContainKeywordsPredicate(firstPredicateNameKeywordList, firstPredicateTagKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameOrTagContainsKeywords_returnsTrue() {
        // One keyword in name
        NameAndTagContainKeywordsPredicate predicate =
            new NameAndTagContainKeywordsPredicate(Collections.singletonList("Alice"), Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // One keyword in tag
        predicate =
            new NameAndTagContainKeywordsPredicate(Collections.emptyList(), Collections.singletonList("tag1"));
        assertTrue(predicate.test(new PersonBuilder().withTags("tag1").build()));

        // Multiple keywords in name
        predicate =
            new NameAndTagContainKeywordsPredicate(Arrays.asList("Alice", "Bob"), Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords in tags
        predicate =
            new NameAndTagContainKeywordsPredicate(Collections.emptyList(), Arrays.asList("tag1", "tag2"));
        assertTrue(predicate.test(new PersonBuilder().withTags("tag1", "tag2").build()));

        // Keywords in both name and tags
        predicate =
            new NameAndTagContainKeywordsPredicate(Arrays.asList("Alice"), Arrays.asList("tag1"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("tag1").build()));
    }

    @Test
    public void test_nameAndTagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameAndTagContainKeywordsPredicate predicate =
            new NameAndTagContainKeywordsPredicate(Collections.emptyList(), Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword in name
        predicate =
            new NameAndTagContainKeywordsPredicate(Arrays.asList("Carol"), Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Non-matching keyword in tags
        predicate =
            new NameAndTagContainKeywordsPredicate(Collections.emptyList(), Arrays.asList("tag3"));
        assertFalse(predicate.test(new PersonBuilder().withTags("tag1", "tag2").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> nameKeywords = List.of("keyword1", "keyword2");
        List<String> tagKeywords = List.of("tag1", "tag2");
        NameAndTagContainKeywordsPredicate predicate =
            new NameAndTagContainKeywordsPredicate(nameKeywords, tagKeywords);

        String expected = NameAndTagContainKeywordsPredicate.class.getCanonicalName()
            + "{nameKeywords=" + nameKeywords + ", tagKeywords=" + tagKeywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
