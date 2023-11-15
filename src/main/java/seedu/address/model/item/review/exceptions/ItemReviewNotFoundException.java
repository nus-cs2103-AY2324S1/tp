package seedu.address.model.item.review.exceptions;

/**
 * Signals that the item review does not exist.
 */
public class ItemReviewNotFoundException extends RuntimeException {
    public ItemReviewNotFoundException() {
        super("Item review does not exist");
    }
}
