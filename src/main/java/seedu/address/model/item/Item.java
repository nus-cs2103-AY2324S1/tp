package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.item.review.ItemReview;
import seedu.address.model.item.review.exceptions.DuplicateItemReviewException;
import seedu.address.model.item.review.exceptions.ItemReviewNotFoundException;

/**
 * Represents an Item in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Item {

    // Identity fields
    private final ItemName itemName;
    private final Price price;
    private ItemReview itemReview;

    /**
     * Every field must be present and not null.
     */
    public Item(ItemName itemName, Price price) {
        requireAllNonNull(itemName, price);
        this.itemName = itemName;
        this.price = price;
        this.itemReview = null;
    }

    /**
     * Constructor with review
     */
    public Item(ItemName itemName, Price price, ItemReview itemReview) {
        requireAllNonNull(itemName, price, itemReview);
        this.itemName = itemName;
        this.price = price;
        this.itemReview = itemReview;
    }

    public ItemName getName() {
        return itemName;
    }

    public String getNameString() {
        return itemName.toString();
    }

    public Price getPrice() {
        return price;
    }
    public String getPriceString() {
        return "$" + price.toString();
    }

    public ItemReview getItemReview() {
        return itemReview;
    }

    /**
     * Returns the item rating string.
     * @return the item rating string.
     */
    public String getItemRatingString() {
        if (itemReview == null) {
            return "No ratings yet.";
        } else {
            return itemReview.getRatingString();
        }
    }

    /**
     * Returns the item description string.
     * @return the item description string.
     */
    public String getItemDescriptionString() {
        if (itemReview == null) {
            return "No review yet.";
        } else {
            return itemReview.getDescriptionString();
        }
    }

    /**
     * Adds an item review to the item.
     *
     * @param itemReview the item review to be added.
     */
    public void setItemReview(ItemReview itemReview) {
        requireAllNonNull(itemReview);
        if (hasItemReview()) {
            throw new DuplicateItemReviewException();
        }
        this.itemReview = itemReview;
    }

    /**
     * Deletes the item review from the item.
     */
    public void deleteItemReview() {
        if (!hasItemReview()) {
            throw new ItemReviewNotFoundException();
        }
        this.itemReview = null;
    }

    /**
     * @return true if the item has an item review.
     */
    public boolean hasItemReview() {
        return this.itemReview != null;
    }


    /**
     * Returns true if both items have the same itemName.
     * This defines a weaker notion of equality between two items.
     * @param otherItem the other item to compare to.
     * @returns true if both items have the same itemName.
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getName().equals(getName());
    }

    /**
     * Returns true if both items have the same identity and data fields.
     * This defines a stronger notion of equality between two items.
     * @param other the other item to compare to.
     * @return true if both items have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return itemName.equals(otherItem.itemName) && price.equals(otherItem.price);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(itemName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("itemName", itemName)
                .add("price", price)
                .toString();
    }

}
