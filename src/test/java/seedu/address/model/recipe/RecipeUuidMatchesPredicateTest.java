package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipeBuilder;

public class RecipeUuidMatchesPredicateTest {
    @Test
    public void equals() {
        UniqueId uuid1 = new UniqueId(1);
        UniqueId uuid2 = new UniqueId(2);

        RecipeUuidMatchesPredicate firstPredicate = new RecipeUuidMatchesPredicate(uuid1);
        RecipeUuidMatchesPredicate secondPredicate = new RecipeUuidMatchesPredicate(uuid2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RecipeUuidMatchesPredicate firstPredicateCopy = new RecipeUuidMatchesPredicate(uuid1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different ingredient -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_recipeContainsSameUuid_returnsTrue() {
        UniqueId uuid = new UniqueId(1);

        RecipeUuidMatchesPredicate predicate = new RecipeUuidMatchesPredicate(uuid);
        assertTrue(predicate.test(new RecipeBuilder().withId(1).build()));
    }

    @Test
    public void test_recipeContainsDifferentUuid_returnsFalse() {
        UniqueId uuid = new UniqueId(1);

        RecipeUuidMatchesPredicate predicate = new RecipeUuidMatchesPredicate(uuid);
        assertFalse(predicate.test(new RecipeBuilder().withId(2).build()));
    }

    @Test
    public void toStringMethod() {
        UniqueId uuid = new UniqueId(1);

        RecipeUuidMatchesPredicate predicate = new RecipeUuidMatchesPredicate(uuid);

        String expected = RecipeUuidMatchesPredicate.class.getCanonicalName() + "{uuid=1}";
        assertEquals(expected, predicate.toString());
    }
}
