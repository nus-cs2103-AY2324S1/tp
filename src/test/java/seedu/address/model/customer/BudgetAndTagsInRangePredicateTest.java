package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.CustomerBuilder;

public class BudgetAndTagsInRangePredicateTest {
    @Test
    public void equals() {
        Budget firstBudget = new Budget("1000000");
        Budget secondBudget = new Budget("2000000");

        Tag firstTag = new Tag("sunny");
        Tag secondTag = new Tag("bright");

        Set<Tag> firstTags = new HashSet<>();
        Set<Tag> secondTags = new HashSet<>();

        firstTags.add(firstTag);
        secondTags.add(secondTag);

        BudgetAndTagsInRangePredicate firstPredicate = new BudgetAndTagsInRangePredicate(firstBudget, firstTags);
        BudgetAndTagsInRangePredicate secondPredicate = new BudgetAndTagsInRangePredicate(secondBudget, firstTags);
        BudgetAndTagsInRangePredicate thirdPredicate = new BudgetAndTagsInRangePredicate(firstBudget, secondTags);
        BudgetAndTagsInRangePredicate fourthPredicate = new BudgetAndTagsInRangePredicate(secondBudget, secondTags);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BudgetAndTagsInRangePredicate firstPredicateCopy = new BudgetAndTagsInRangePredicate(firstBudget, firstTags);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different budget -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different tags -> return false
        assertFalse(firstPredicate.equals(thirdPredicate));

        // different tags and budget -> return false
        assertFalse(firstPredicate.equals(fourthPredicate));
    }

    @Test
    public void test_budgetAndTagsInRangeReturnTrue() {
        String smallBudgetString = "100000";
        String bigBudgetString = "1000000000";

        Budget smallBudget = new Budget(smallBudgetString);

        String firstTagString = "sunny";
        String secondTagString = "bright";

        Tag firstTag = new Tag(firstTagString);
        Tag secondTag = new Tag(secondTagString);

        Set<Tag> emptyTags = new HashSet<>();
        Set<Tag> singleTags = new HashSet<>();
        Set<Tag> someTags = new HashSet<>();

        singleTags.add(firstTag);
        someTags.add(firstTag);
        someTags.add(secondTag);

        // Null tags
        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(smallBudget, null);

        assertTrue(predicate.test(new CustomerBuilder().withBudget(smallBudgetString).build()));
        assertTrue(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString).build()));

        // Same budget with empty tag
        predicate = new BudgetAndTagsInRangePredicate(smallBudget, emptyTags);
        assertTrue(predicate.test(new CustomerBuilder().withBudget(smallBudgetString).build()));
        assertTrue(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString).build()));

        // Lower budget with empty tag
        assertTrue(predicate.test(new CustomerBuilder().withBudget(bigBudgetString).build()));
        assertTrue(predicate.test(new CustomerBuilder().withBudget(bigBudgetString).withTags(firstTagString).build()));


        // No budget with single tag
        predicate = new BudgetAndTagsInRangePredicate(null, singleTags);
        assertTrue(predicate.test(new CustomerBuilder().withTags(firstTagString).build()));
        assertTrue(predicate.test(new CustomerBuilder().withTags(firstTagString, secondTagString).build()));

        // Same budget with single tag
        predicate = new BudgetAndTagsInRangePredicate(smallBudget, singleTags);
        assertTrue(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString).build()));
        assertTrue(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString, secondTagString).build()));

        // Lower budget with single tag
        assertTrue(predicate.test(new CustomerBuilder().withBudget(bigBudgetString).withTags(firstTagString).build()));
        assertTrue(predicate.test(new CustomerBuilder()
                .withBudget(bigBudgetString).withTags(firstTagString, secondTagString).build()));

        // No budget with multiple tags
        predicate = new BudgetAndTagsInRangePredicate(null, someTags);
        assertTrue(predicate.test(new CustomerBuilder().withTags(firstTagString, secondTagString).build()));

        // Same budget with multiple tags
        predicate = new BudgetAndTagsInRangePredicate(smallBudget, someTags);
        assertTrue(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString, secondTagString).build()));

        // Lower budget with multiple tags
        assertTrue(predicate.test(new CustomerBuilder()
                .withBudget(bigBudgetString).withTags(firstTagString, secondTagString).build()));
    }

    @Test
    public void test_budgetAndTagsInRangeReturnFalse() {
        String smallBudgetString = "100000";
        String bigBudgetString = "1000000000";

        Budget bigBudget = new Budget(bigBudgetString);

        String firstTagString = "sunny";
        String secondTagString = "bright";
        String thirdTagString = "square";

        Tag firstTag = new Tag(firstTagString);
        Tag secondTag = new Tag(secondTagString);

        Set<Tag> emptyTags = new HashSet<>();
        Set<Tag> singleTags = new HashSet<>();
        Set<Tag> someTags = new HashSet<>();

        singleTags.add(firstTag);
        someTags.add(firstTag);
        someTags.add(secondTag);

        // Bigger budget with empty tag
        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(bigBudget, emptyTags);
        assertFalse(predicate.test(new CustomerBuilder().withBudget(smallBudgetString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString).build()));

        // No budget with single tag
        predicate = new BudgetAndTagsInRangePredicate(null, singleTags);
        assertFalse(predicate.test(new CustomerBuilder().withTags(secondTagString).build()));

        // Same budget with single tag
        predicate = new BudgetAndTagsInRangePredicate(bigBudget, singleTags);
        assertFalse(predicate.test(new CustomerBuilder().withBudget(bigBudgetString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(bigBudgetString).withTags(secondTagString).build()));

        // Bigger budget with single tag
        assertFalse(predicate.test(new CustomerBuilder().withBudget(smallBudgetString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(secondTagString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString, secondTagString).build()));

        // No budget with multiple tags
        predicate = new BudgetAndTagsInRangePredicate(null, someTags);
        assertFalse(predicate.test(new CustomerBuilder().build()));
        assertFalse(predicate.test(new CustomerBuilder().withTags(firstTagString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withTags(firstTagString, thirdTagString).build()));

        // Same budget with multiple tags
        predicate = new BudgetAndTagsInRangePredicate(bigBudget, someTags);
        assertFalse(predicate.test(new CustomerBuilder().withBudget(bigBudgetString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(bigBudgetString).withTags(firstTagString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(bigBudgetString).withTags(thirdTagString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(bigBudgetString).withTags(firstTagString, thirdTagString).build()));

        // Bigger budget with multiple tags
        predicate = new BudgetAndTagsInRangePredicate(bigBudget, someTags);
        assertFalse(predicate.test(new CustomerBuilder().withBudget(smallBudgetString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString, secondTagString).build()));
        assertFalse(predicate.test(new CustomerBuilder()
                .withBudget(smallBudgetString).withTags(firstTagString, thirdTagString).build()));

    }
}
