package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRecords.ALLERGIC_REACTION2;
import static seedu.address.testutil.TypicalRecords.FEVER0;
import static seedu.address.testutil.TypicalRecords.FEVER_AND_COLD0;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RecordContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        RecordContainsKeywordsPredicate firstPredicate =
                new RecordContainsKeywordsPredicate(Arrays.asList("Tylenol", "Pepto-Bismol"));
        RecordContainsKeywordsPredicate secondPredicate =
                new RecordContainsKeywordsPredicate(Arrays.asList("Tylenol"));
        assertTrue(firstPredicate.equals(firstPredicate));

        RecordContainsKeywordsPredicate firstPredicateCopy =
                new RecordContainsKeywordsPredicate(Arrays.asList("Tylenol", "Pepto-Bismol"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));
        assertFalse(firstPredicate.equals(secondPredicate));
        assertFalse(firstPredicate.equals(0));
    }
    @Test
    public void test_fieldContainsKeywords_returnsTrue() {
        RecordContainsKeywordsPredicate predicate =
                new RecordContainsKeywordsPredicate(Arrays.asList("Tylenol"));
        assertTrue(predicate.test(FEVER0));

        predicate = new RecordContainsKeywordsPredicate(Arrays.asList("Fever", "Cold"));
        assertTrue(predicate.test(FEVER_AND_COLD0));

        predicate = new RecordContainsKeywordsPredicate(Arrays.asList("Fever", "Allergic Reaction"));
        assertTrue(predicate.test(FEVER_AND_COLD0));

        predicate = new RecordContainsKeywordsPredicate(Arrays.asList("FeVer", "cOLd"));
        assertTrue(predicate.test(FEVER_AND_COLD0));
    }
    @Test
    public void test_fieldDoesNotContainKeywords_returnFalse() {
        RecordContainsKeywordsPredicate predicate =
                new RecordContainsKeywordsPredicate(Arrays.asList("Tylenol"));
        assertFalse(predicate.test(ALLERGIC_REACTION2));

        predicate = new RecordContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(ALLERGIC_REACTION2));

    }
    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("Tylenol", "Pepto-Bismol");
        RecordContainsKeywordsPredicate predicate = new RecordContainsKeywordsPredicate(keywords);
        String expected = RecordContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
