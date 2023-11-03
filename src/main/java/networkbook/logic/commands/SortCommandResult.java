package networkbook.logic.commands;

import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import networkbook.commons.util.ToStringBuilder;
import networkbook.model.person.PersonSortComparator.SortField;
import networkbook.model.person.PersonSortComparator.SortOrder;

/**
 * Represents the result of a sort command execution.
 */
public class SortCommandResult extends CommandResult {

    private final SortField sortField;
    private final SortOrder sortOrder;

    /**
     * Constructs a {@code SortCommandResult} with the specified fields.
     */
    public SortCommandResult(String feedbackToUser, SortField sortField, SortOrder sortOrder) {
        super(feedbackToUser);
        requireAllNonNull(sortOrder, sortField);
        this.sortOrder = sortOrder;
        this.sortField = sortField;
    }

    /**
     * Returns sort field.
     */
    public SortField getSortField() {
        return sortField; // Can return directly as field is final
    }

    /**
     * Returns sort order.
     */
    public SortOrder getSortOrder() {
        return sortOrder; // Can return directly as field is final
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

        SortCommandResult otherSortCommandResult = (SortCommandResult) other;
        return super.equals(otherSortCommandResult)
                && sortField == otherSortCommandResult.sortField
                && sortOrder == otherSortCommandResult.sortOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackToUser(), sortField, sortOrder);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", getFeedbackToUser())
                .add("sortField", sortField)
                .add("sortOrder", sortOrder)
                .toString();
    }

}
