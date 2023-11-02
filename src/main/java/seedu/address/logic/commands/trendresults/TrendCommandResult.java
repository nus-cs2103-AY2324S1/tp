package seedu.address.logic.commands.trendresults;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TrendCommand;

import java.util.Objects;

/**
 * Represents the command result for trend graph generation.
 */
public class TrendCommandResult extends CommandResult {
    private static final String MESSAGE_SUCCESS = "A trend graph is shown.";

    /**
     * Constructor for {@code TrendCommandResult}.
     */
    public TrendCommandResult() {
        super(MESSAGE_SUCCESS);
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
        return super.equals(otherCommandResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowHelp(), super.isExit());
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
