package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CombinedPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FieldContainsKeywordsPredicate first = new FieldContainsKeywordsPredicate(
                PREFIX_ROLE, firstPredicateKeywordList);
        FieldContainsKeywordsPredicate second = new FieldContainsKeywordsPredicate(
                PREFIX_ROLE, secondPredicateKeywordList);

        CombinedPredicate firstPredicate = new CombinedPredicate(Arrays.asList(first));
        CombinedPredicate secondPredicate = new CombinedPredicate((Arrays.asList(first, second)));
        CombinedPredicate thirdPredicate = new CombinedPredicate((Arrays.asList(second, first)));

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        CombinedPredicate firstPredicateCopy = new CombinedPredicate(Arrays.asList(first));
        assertEquals(firstPredicate, firstPredicateCopy);

        // same predicates -> returns true
        assertEquals(secondPredicate, thirdPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 5.0f);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different predicate -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }
}
