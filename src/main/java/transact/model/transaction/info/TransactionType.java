package transact.model.transaction.info;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the different types of a transaction in the system.
 * TransactionType can be changed.
 */
public enum TransactionType {
    REVENUE("Revenue", "R", "r"), EXPENSE("Expense", "E", "e");

    public static final String MESSAGE_CONSTRAINTS = "This is an invalid type!";

    // The first value is the display string
    private final List<String> validStrings;

    TransactionType(String... validStrings) {
        this.validStrings = Arrays.asList(validStrings);
    }

    public static boolean isValidType(String type) {
        return (REVENUE.validStrings.contains(type) || EXPENSE.validStrings.contains(type));
    }

    public static TransactionType getType(String type) {
        if (REVENUE.validStrings.contains(type)) {
            return REVENUE;
        }
        if (EXPENSE.validStrings.contains(type)) {
            return EXPENSE;
        }
        // code should never reach here.
        assert type != null;
        return null;
    }

    @Override
    public String toString() {
        return validStrings.get(0);
    }
}
