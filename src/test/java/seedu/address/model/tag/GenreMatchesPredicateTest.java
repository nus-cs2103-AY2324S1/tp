package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MusicianBuilder;
public class GenreMatchesPredicateTest {
    @Test
    public void equals() {
        GenreMatchesPredicate firstPredicate = new GenreMatchesPredicate(Arrays.asList("pop"));
        GenreMatchesPredicate secondPredicate = new GenreMatchesPredicate(Arrays.asList("pop", "rock"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GenreMatchesPredicate firstPredicateCopy = new GenreMatchesPredicate(Arrays.asList("pop"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag names -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    void test_tagMatches_returnsFalse() {
        // Zero tags
        GenreMatchesPredicate predicate = new GenreMatchesPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MusicianBuilder().withGenres("pop").build()));

        // Incomplete-matching tag
        predicate = new GenreMatchesPredicate(Arrays.asList("po"));
        assertFalse(predicate.test(new MusicianBuilder().withGenres("pop").build()));

        // Non-matching tag
        predicate = new GenreMatchesPredicate(Arrays.asList("jazz", "rock"));
        assertFalse(predicate.test(new MusicianBuilder().withGenres("pop").build()));
    }

    @Test
    void testToString() {
        List<String> tags = List.of("pop", "rock");
        GenreMatchesPredicate predicate = new GenreMatchesPredicate(tags);

        String expected = GenreMatchesPredicate.class.getCanonicalName() + "{genres=" + tags + "}";
        assertEquals(expected, predicate.toString());
    }
}
