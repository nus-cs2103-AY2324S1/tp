package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PropertyBuilder;

public class PriceAndOneTagsPredicateTest {
    @Test
    public void equals() {
        Price firstPrice = new Price("1000000");
        Price secondPrice = new Price("2000000");

        Tag firstTag = new Tag("sunny");
        Tag secondTag = new Tag("bright");

        Set<Tag> firstTags = new HashSet<>();
        Set<Tag> secondTags = new HashSet<>();

        firstTags.add(firstTag);
        secondTags.add(secondTag);

        PriceAndOneTagsPredicate firstPredicate = new PriceAndOneTagsPredicate(firstPrice, firstTags);
        PriceAndOneTagsPredicate secondPredicate = new PriceAndOneTagsPredicate(secondPrice, firstTags);
        PriceAndOneTagsPredicate thirdPredicate = new PriceAndOneTagsPredicate(firstPrice, secondTags);
        PriceAndOneTagsPredicate fourthPredicate = new PriceAndOneTagsPredicate(secondPrice, secondTags);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PriceAndOneTagsPredicate firstPredicateCopy = new PriceAndOneTagsPredicate(firstPrice, firstTags);
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
    public void test_budgetAndOneTagsReturnTrue() {
        String lowPriceString = "100000";
        String highPriceString = "1000000000";

        Price highPrice = new Price(highPriceString);

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

        // Same price with empty tag
        PriceAndOneTagsPredicate predicate = new PriceAndOneTagsPredicate(highPrice, emptyTags);
        assertTrue(predicate.test(new PropertyBuilder().withPrice(highPriceString).build()));
        assertTrue(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString).build()));

        // Higher price with empty tag
        assertTrue(predicate.test(new PropertyBuilder().withPrice(lowPriceString).build()));
        assertTrue(predicate.test(new PropertyBuilder().withPrice(lowPriceString).withTags(firstTagString).build()));


        // No price with single tag
        predicate = new PriceAndOneTagsPredicate(null, singleTags);
        assertTrue(predicate.test(new PropertyBuilder().withTags(firstTagString).build()));
        assertTrue(predicate.test(new PropertyBuilder().withTags(firstTagString, secondTagString).build()));

        // Same price with single tag
        predicate = new PriceAndOneTagsPredicate(highPrice, singleTags);
        assertTrue(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString).build()));
        assertTrue(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString, secondTagString).build()));

        // Higher price with single tag
        assertTrue(predicate.test(new PropertyBuilder().withPrice(lowPriceString).withTags(firstTagString).build()));
        assertTrue(predicate.test(new PropertyBuilder()
                .withPrice(lowPriceString).withTags(firstTagString, secondTagString).build()));

        // No price with multiple tags
        predicate = new PriceAndOneTagsPredicate(null, someTags);
        assertTrue(predicate.test(new PropertyBuilder().withTags(firstTagString, secondTagString).build()));

        // Same price with multiple tags
        predicate = new PriceAndOneTagsPredicate(highPrice, someTags);
        assertTrue(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString, secondTagString).build()));

        // Higher price with multiple tags
        assertTrue(predicate.test(new PropertyBuilder()
                .withPrice(lowPriceString).withTags(firstTagString, secondTagString).build()));
    }

    @Test
    public void test_budgetAndTagsInRangeReturnFalse() {
        String lowPriceString = "100000";
        String highPriceString = "1000000000";

        Price lowPrice = new Price(lowPriceString);

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

        // Lower price with empty tag
        PriceAndOneTagsPredicate predicate = new PriceAndOneTagsPredicate(lowPrice, emptyTags);
        assertFalse(predicate.test(new PropertyBuilder().withPrice(highPriceString).build()));
        assertFalse(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString).build()));

        // No price with single tag
        predicate = new PriceAndOneTagsPredicate(null, singleTags);
        assertFalse(predicate.test(new PropertyBuilder().withTags(secondTagString).build()));

        // Same price with single tag
        predicate = new PriceAndOneTagsPredicate(lowPrice, singleTags);
        assertFalse(predicate.test(new PropertyBuilder().withPrice(lowPriceString).build()));
        assertFalse(predicate.test(new PropertyBuilder()
                .withPrice(lowPriceString).withTags(secondTagString).build()));

        // Lower price with single tag
        assertFalse(predicate.test(new PropertyBuilder().withPrice(highPriceString).build()));
        assertFalse(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString).build()));
        assertFalse(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(secondTagString).build()));
        assertFalse(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString, secondTagString).build()));

        // Lower price with multiple tags
        predicate = new PriceAndOneTagsPredicate(lowPrice, someTags);
        assertFalse(predicate.test(new PropertyBuilder().withPrice(highPriceString).build()));
        assertFalse(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString).build()));
        assertFalse(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString, secondTagString).build()));
        assertFalse(predicate.test(new PropertyBuilder()
                .withPrice(highPriceString).withTags(firstTagString, thirdTagString).build()));

    }
}
