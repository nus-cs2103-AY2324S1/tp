package networkbook.logic.commands;

import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import networkbook.commons.util.ToStringBuilder;

/**
 * Represents the result of a filter command execution.
 */
public class FilterCommandResult extends CommandResult {

    private final String filterField;

    /**
     * Constructs a {@code FilterCommandResult} with the specified fields.
     */
    public FilterCommandResult(String feedbackToUser, String filterField) {
        super(feedbackToUser);
        requireAllNonNull(filterField);
        this.filterField = filterField;
    }

    /**
     * Returns filter field.
     */
    public String getFilterField() {
        return filterField; // Can return directly as field is final
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommandResult)) {
            return false;
        }

        FilterCommandResult otherFilterCommandResult = (FilterCommandResult) other;
        return super.equals(otherFilterCommandResult)
                && filterField == otherFilterCommandResult.filterField;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackToUser(), filterField);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", getFeedbackToUser())
                .add("filterField", filterField)
                .toString();
    }

}
