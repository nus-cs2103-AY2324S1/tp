package seedu.address.commons.core.tab;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a zero-based or one-based tab index.
 */
public class Tab {
    public static final int CONTACTS_TAB_INDEX = 0;
    public static final int FINANCE_TAB_INDEX = 1;
    public static final int EVENTS_TAB_INDEX = 2;
    public static final String CONTACT_PARAMETER = "contacts";
    public static final String FINANCE_PARAMETER = "finance";
    public static final String EVENTS_PARAMETER = "events";
    private static final String MESSAGE_INVALID_PARAMETER = "Parameter is not valid";

    private final int zeroBasedTabIndex;
    private final String tabParameter;

    /**
     * Tab can only be created by calling {@link Tab#fromParameter(String)}.
     */
    private Tab(String tabParameter) {
        zeroBasedTabIndex = getIndexFromParameter(tabParameter);
        this.tabParameter = tabParameter;
    }

    public int getZeroBasedTabIndex() {
        return zeroBasedTabIndex;
    }

    /**
     * Creates a new {@code Tab} using parameter.
     */
    public static Tab fromParameter(String parameter) {
        requireNonNull(parameter);
        return new Tab(parameter);
    }

    private static int getIndexFromParameter(String parameter) {
        switch (parameter) {
        case CONTACT_PARAMETER:
            return CONTACTS_TAB_INDEX;
        case FINANCE_PARAMETER:
            return FINANCE_TAB_INDEX;
        case EVENTS_PARAMETER:
            return EVENTS_TAB_INDEX;
        default:
            // Unable to create new exception specifically for this since this is created after v1.3
            throw new RuntimeException(MESSAGE_INVALID_PARAMETER);
        }
    }

    /**
     * Checks if parameter is valid
     */
    public static boolean isValidTabParameter(String parameter) {
        requireNonNull(parameter);
        return Objects.equals(parameter, CONTACT_PARAMETER)
                || Objects.equals(parameter, EVENTS_PARAMETER)
                || Objects.equals(parameter, FINANCE_PARAMETER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tab)) {
            return false;
        }

        Tab otherTab = (Tab) other;
        return zeroBasedTabIndex == otherTab.zeroBasedTabIndex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tabParameter", tabParameter)
                .add("zeroBasedTabIndex", zeroBasedTabIndex)
                .toString();
    }
}
