package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MusicianBuilder;
public class InstrumentMatchesPredicateTest {
    @Test
    public void equals() {
        InstrumentMatchesPredicate firstPredicate = new InstrumentMatchesPredicate(Arrays.asList("violin"));
        InstrumentMatchesPredicate secondPredicate = new InstrumentMatchesPredicate(Arrays.asList("violin", "guitar"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InstrumentMatchesPredicate firstPredicateCopy = new InstrumentMatchesPredicate(Arrays.asList("violin"));
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
        InstrumentMatchesPredicate predicate = new InstrumentMatchesPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MusicianBuilder().withInstruments("violin").build()));

        // Incomplete-matching tag
        predicate = new InstrumentMatchesPredicate(Arrays.asList("violi"));
        assertFalse(predicate.test(new MusicianBuilder().withInstruments("violin").build()));

        // Non-matching tag
        predicate = new InstrumentMatchesPredicate(Arrays.asList("drums", "guitar"));
        assertFalse(predicate.test(new MusicianBuilder().withInstruments("violin").build()));
    }

    @Test
    void testToString() {
        List<String> tags = List.of("violin", "guitar");
        InstrumentMatchesPredicate predicate = new InstrumentMatchesPredicate(tags);

        String expected = InstrumentMatchesPredicate.class.getCanonicalName() + "{instruments=" + tags + "}";
        assertEquals(expected, predicate.toString());
    }
}
