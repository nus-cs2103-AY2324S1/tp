package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MusicianBuilder;

class TagMatchesPredicateTest {

    @Test
    public void equals() {
        TagMatchesPredicate firstPredicate = new TagMatchesPredicate(Arrays.asList("firstTag"));
        TagMatchesPredicate secondPredicate = new TagMatchesPredicate(Arrays.asList("firstTag", "secondTag"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagMatchesPredicate firstPredicateCopy = new TagMatchesPredicate(Arrays.asList("firstTag"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag names -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
    @Test
    void test_tagMatches_returnsTrue() {
        // One tag
        TagMatchesPredicate predicate = new TagMatchesPredicate(Collections.singletonList("firstTag"));
        assertTrue(predicate.test(new MusicianBuilder().withTags("firstTag").build()));

        // Fully matching tags
        predicate = new TagMatchesPredicate(Arrays.asList("firstTag", "secondTag"));
        assertTrue(predicate.test(new MusicianBuilder().withTags("firstTag", "secondTag").build()));

        // Partially matching tag
        predicate = new TagMatchesPredicate(Arrays.asList("firstTag", "secondTag"));
        assertTrue(predicate.test(new MusicianBuilder().withTags("firstTag", "thirdTag").build()));

        // Repeated tags
        predicate = new TagMatchesPredicate(Arrays.asList("firstTag", "firstTag"));
        assertTrue(predicate.test(new MusicianBuilder().withTags("firstTag").build()));

        // Mixed-case tags
        predicate = new TagMatchesPredicate(Arrays.asList("caseInsensitiveTag"));
        assertTrue(predicate.test(new MusicianBuilder().withTags("CASEinsenSITIVEtag").build()));
    }

    @Test
    void test_tagMatches_returnsFalse() {
        // Zero tags
        TagMatchesPredicate predicate = new TagMatchesPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MusicianBuilder().withTags("firstTag").build()));

        // Incomplete-matching tag
        predicate = new TagMatchesPredicate(Arrays.asList("first"));
        assertFalse(predicate.test(new MusicianBuilder().withTags("firstTag").build()));

        // Non-matching tag
        predicate = new TagMatchesPredicate(Arrays.asList("thirdTag", "fourthTag", "fifthTag"));
        assertFalse(predicate.test(new MusicianBuilder().withTags("firstTag", "secondTag").build()));
    }

    @Test
    void testToString() {
        List<String> tags = List.of("tag1", "tag2");
        TagMatchesPredicate predicate = new TagMatchesPredicate(tags);

        String expected = TagMatchesPredicate.class.getCanonicalName() + "{tags=" + tags + "}";
        assertEquals(expected, predicate.toString());
    }
}
