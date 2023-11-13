package seedu.letsgethired.logic.parser;

/**
 * Represents the order in which the list of applications are sorted.
 */
public class SortOrder {
    /**
     * The two possible sort orders.
     */
    public static final SortOrder ASCENDING = new SortOrder(true);
    public static final SortOrder DESCENDING = new SortOrder(false);

    public static final String MESSAGE_CONSTRAINTS = "Sort order can only be 'a' or 'd'.";
    private final boolean isAscending;


    /**
     * SortOrder can only be created from the static methods below.
     */
    private SortOrder(boolean isAscending) {
        this.isAscending = isAscending;
    }

    /**
     * Returns true if the sort order is ascending.
     */
    public boolean isAscending() {
        return isAscending;
    }

    /**
     * Returns true if the given string is a valid ascending sort order.
     */
    public static boolean isValidAscendingSortOrder(String test) {
        return test.equals("a");
    }

    /**
     * Returns true if the given string is a valid descending sort order.
     */
    public static boolean isValidDescendingSortOrder(String test) {
        return test.equals("d");
    }
}
