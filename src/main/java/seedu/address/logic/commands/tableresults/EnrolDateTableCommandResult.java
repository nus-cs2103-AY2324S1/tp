package seedu.address.logic.commands.tableresults;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.count.EnrolDateCommandResult;

/**
 * Represents for the command result for enrol date table generation.
 */
public class EnrolDateTableCommandResult extends EnrolDateCommandResult {
    private static final String MESSAGE_SUCCESS = "A table categorized by EnrolDate is shown";
    private Map<String, Integer> columnValueMapping;

    /**
     * Constructor for EnrolDateTableCommandResult.
     * @param columnValueMapping hash map instance containing column titles(String) as keys and counts(Long) as values.
     */
    public EnrolDateTableCommandResult(Map<String, Integer> columnValueMapping) {
        super(MESSAGE_SUCCESS, columnValueMapping);
        this.columnValueMapping = columnValueMapping;
    }

    /**
     * Check if this CommandResult instance is meant for creating table window.
     * @return always return true for EnrolDateTableCommandResult instance.
     */
    @Override
    public boolean isShowTable() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrolDateTableCommandResult)) {
            return false;
        }

        EnrolDateTableCommandResult otherCommandResult = (EnrolDateTableCommandResult) other;

        return super.equals(otherCommandResult)
                && otherCommandResult.isShowTable() == isShowTable();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowTable(), super.isExit(), this.columnValueMapping);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", MESSAGE_SUCCESS)
                .add("showHelp", isShowHelp())
                .add("showTable", isShowTable())
                .add("showBarChart", isShowBarChart())
                .add("exit", isExit())
                .toString();
    }

}
