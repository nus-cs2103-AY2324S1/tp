package seedu.address.testutil;

import static seedu.address.testutil.TypicalItemReviews.ITEM_REVIEW_1;

import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Price;
import seedu.address.model.item.review.ItemReview;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Chicken Rice";
    public static final String DEFAULT_PRICE = "5.50";
    public static final ItemReview DEFAULT_ITEM_REVIEW = null;
    public static final ItemReview VALID_ITEM_REVIEW_1 = ITEM_REVIEW_1;
    private ItemName name;
    private Price price;
    private ItemReview itemReview;


    /**
     * Creates a {@code StallBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new ItemName(DEFAULT_NAME);
        price = new Price(DEFAULT_PRICE);
        itemReview = DEFAULT_ITEM_REVIEW;
    }

    /**
     * Initializes the StallBuilder with the data of {@code stallToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getName();
        price = itemToCopy.getPrice();
        itemReview = itemToCopy.getItemReview();
    }

    /**
     * Sets the {@code Name} of the {@code Stall} that we are building.
     */
    public ItemBuilder withName(String name) {
        this.name = new ItemName(name);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Stall} that we are building.
     */
    public ItemBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Sets the {@code ItemReview} of the {@code Stall} that we are building.
     */
    public ItemBuilder withItemReview(ItemReview itemReview) {
        this.itemReview = itemReview;
        return this;
    }

    /**
     * Builds the item with the given parameters.
     *
     * @return the item
     */
    public Item build() {
        if (itemReview != null) {
            return new Item(name, price, itemReview);
        }
        return new Item(name, price);
    }

}
