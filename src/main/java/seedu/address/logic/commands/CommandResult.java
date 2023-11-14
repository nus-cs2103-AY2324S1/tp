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

    /**
     * Retrieves the feedback message to be displayed to the user.
     *
     * @return The feedback message.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Checks if help information should be displayed to the user.
     *
     * @return True if help information should be displayed, otherwise returns false.
     */
    public boolean isShowHelp() {
        return showHelp;
    }

    /**
     * Checks if the application should exit.
     *
     * @return True if the application should exit, otherwise returns false.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Checks equality of the CommandResult with another object.
     *
     * @param other The reference object with which to compare for equality.
     * @return True if the objects are equal, otherwise returns false.
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
                && exit == otherCommandResult.exit;
    }

    /**
     * Returns a hash code value for the CommandResult object.
     *
     * @return A hash code value for this CommandResult.
     */
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of this CommandResult.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .toString();
    }

}
