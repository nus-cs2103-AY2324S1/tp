package seedu.address.logic.commands.commandresults;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution where there is no need for any entity to be displayed.
 */
public class RegularCommandResult extends CommandResult {

    /**
     * Constructs a {@code RegularCommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public RegularCommandResult(String feedbackToUser) {
        super(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code RegularCommandResult} with the specified fields.
     */
    public RegularCommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        super(feedbackToUser, showHelp, exit);
    }

    @Override
    public boolean isDisplayableCommandResult() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RegularCommandResult)) {
            return false;
        }

        RegularCommandResult otherCommandResult = (RegularCommandResult) other;
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
                .add("exit", exit)
                .toString();
    }
}
