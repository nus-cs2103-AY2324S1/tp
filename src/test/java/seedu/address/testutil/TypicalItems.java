package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CHICKEN_RICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_NASI_LEMAK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_2;
import static seedu.address.testutil.TypicalItemReviews.ITEM_REVIEW_1;
import static seedu.address.testutil.TypicalItemReviews.ITEM_REVIEW_2;

import seedu.address.model.item.Item;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item FRIED_RICE = new ItemBuilder().withName("Fried Rice").withPrice("5.50").build();
    public static final Item LAKSA = new ItemBuilder().withName("Laksa").withPrice("4.50").build();
    public static final Item NASI_LEMAK = new ItemBuilder()
            .withName(VALID_ITEM_NAME_NASI_LEMAK).withPrice(VALID_ITEM_PRICE_1).build();
    public static final Item CHICKEN_RICE = new ItemBuilder()
            .withName(VALID_ITEM_NAME_CHICKEN_RICE).withPrice(VALID_ITEM_PRICE_2).build();
    public static final Item DUCK_RICE = new ItemBuilder()
            .withName("Duck Rice").withPrice("3.50").withItemReview(ITEM_REVIEW_2)
            .build();
    public static final Item FISH_BALL_NOODLES = new ItemBuilder()
            .withName("Fish Ball Noodles").withPrice("3.00").withItemReview(ITEM_REVIEW_1)
            .build();

    private TypicalItems() {} // prevents instantiation

}
