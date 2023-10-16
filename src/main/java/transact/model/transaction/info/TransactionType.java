package transact.model.transaction.info;
/**
 * Represents the different types of a transaction in the system.
 * TransactionType can be changed.
 */
public enum TransactionType {
    Revenue,
    Expense;

    public static final String MESSAGE_CONSTRAINTS = "This is an invalid type!";
    public static boolean isValidType(String type) {
        return (Revenue.name().equals(type) || Expense.name().equals(type));
    }

    public static TransactionType getType(String type) {
        if (Revenue.name().equals(type)) {
            return Revenue;
        }
        if (Expense.name().equals(type)) {
            return Expense;
        }
        //code should never reach here.
        assert type != null;
        return null;
    }
}
