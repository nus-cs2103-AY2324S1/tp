package seedu.address.model.finance.exceptions;

/**
 * Signals that the operation will result in duplicate Expense (Expenses are considered duplicates if they have the same
 * identity).
 */
public class DuplicateExpenseException extends RuntimeException {
    public DuplicateExpenseException() {
        super("Operation would result in duplicate expenses");
    }
}
