package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    /**
     * Check if this CommandResult instance is meant for showing table window.
     * @return true if this is a XXXTableCommandResult instance, false otherwise.
     */
    public boolean isShowTable() {
        return false;
    }

    /**
     * Check if this CommandResult instance is meant for showing bar chart window.
     * @return true if this is a XXXBarChartCommandResult instance, false otherwise.
     */
    public boolean isShowBarChart() {
        return false;
    }

    /**
     * Check if this CommandResult instance is meant for showing trend window.
     * @return true if this is a TrendCommandResult instance, false otherwise.
     */
    public boolean isShowTrend() {
        return false;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("showTable", this.isShowTable())
                .add("showBarChart", this.isShowBarChart())
                .add("showTrend", this.isShowTrend())
                .add("exit", exit)
                .toString();
    }

}
