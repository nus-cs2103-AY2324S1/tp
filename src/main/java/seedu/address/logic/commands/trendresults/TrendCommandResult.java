package seedu.address.logic.commands.trendresults;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.count.EnrolDateCommandResult;

/**
 * Represents the command result for trend graph generation.
 */
public class TrendCommandResult extends EnrolDateCommandResult {
    private static final String MESSAGE_SUCCESS = "A trend graph is shown.";
    private Map<String, Integer> columnValueMapping;

    /**
     * Constructor for {@code TrendCommandResult}.
     * @param columnValueMapping hash map instance containing month as keys and counts as values.
     */
    public TrendCommandResult(Map<String, Integer> columnValueMapping) {
        super(MESSAGE_SUCCESS, columnValueMapping);
        this.columnValueMapping = columnValueMapping;
    }

    /**
     * Check if this CommandResult instance is meant for showing trend window.
     * @return true if this is a TrendCommandResult instance, false otherwise.
     */
    @Override
    public boolean isShowTrend() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TrendCommandResult)) {
            return false;
        }

        TrendCommandResult otherCommandResult = (TrendCommandResult) other;
        return super.equals(otherCommandResult)
                && otherCommandResult.isShowTrend() == isShowTrend();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowTrend(), super.isExit(), columnValueMapping);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", MESSAGE_SUCCESS)
                .add("showHelp", isShowHelp())
                .add("showTable", isShowTable())
                .add("showBarChart", isShowBarChart())
                .add("showTrend", isShowTrend())
                .add("exit", isExit())
                .toString();
    }
}
