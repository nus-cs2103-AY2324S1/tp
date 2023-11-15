package seedu.address.model.item.review.exceptions;

/**
 * Signals that the item review already exists.
 */
public class DuplicateItemReviewException extends RuntimeException {
    public DuplicateItemReviewException() {
        super("Item review already exists");
    }
}
