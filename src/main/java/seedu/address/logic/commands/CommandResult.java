package seedu.address.logic.commands;

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
    private final boolean showRoomStatistics;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     *
     * @param feedbackToUser The feedback message to display to the user.
     * @param showHelp       Whether help information should be displayed to the user.
     * @param exit           Whether the application should exit.
     * @param showRoomStatistics Whether room statistics should be displayed.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showRoomStatistics) {
        assert feedbackToUser != null : "Feedback to user cannot be empty.";
        this.feedbackToUser = feedbackToUser;
        this.showHelp = showHelp;
        this.exit = exit;
        this.showRoomStatistics = showRoomStatistics;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     *
     * @param feedbackToUser The feedback message to display to the user.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, true);
    }

    /**
     * Gets the feedback message to display to the user.
     *
     * @return The feedback message.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Checks if help information should be shown to the user.
     *
     * @return {@code true} if help information should be shown, {@code false} otherwise.
     */
    public boolean isShowHelp() {
        return showHelp;
    }

    /**
     * Checks if the application should exit.
     *
     * @return {@code true} if the application should exit, {@code false} otherwise.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Checks if room statistics should be shown to the user.
     *
     * @return {@code true} if room statistics should be shown, {@code false} otherwise.
     */
    public boolean isShowRoomStatistics() {
        return showRoomStatistics;
    }

    /**
     * Checks if this {@code CommandResult} is equal to another object.
     *
     * @param other The object to compare with this {@code CommandResult}.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
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
                && exit == otherCommandResult.exit
                && showRoomStatistics == otherCommandResult.showRoomStatistics;
    }

    /**
     * Computes a hash code for this {@code CommandResult} based on its attributes.
     *
     * @return The computed hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showRoomStatistics);
    }

    /**
     * Returns a string representation of this {@code CommandResult} for debugging and logging purposes.
     *
     * @return The string representation of this object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("showRoomStatistics", showRoomStatistics)
                .toString();
    }

}
