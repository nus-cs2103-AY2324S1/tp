package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
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
    private final List<Integer> indexes;
    /** The application should read a specific field of a particular employee. */
    private final boolean read;
    private final String fieldToRead;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.indexes = null;
        this.read = false;
        this.fieldToRead = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.indexes = null;
        this.exit = false;
        this.read = false;
        this.fieldToRead = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}
     * To store read field and set read to true to display a different person card.
     */
    public CommandResult(String feedbackToUser, boolean read, String fieldToRead) {
        this.feedbackToUser = feedbackToUser;
        this.indexes = null;
        this.exit = false;
        this.showHelp = false;
        this.read = read;
        this.fieldToRead = fieldToRead;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, List<Integer> indexes) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.indexes = indexes;
        this.read = false;
        this.fieldToRead = null;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public String getFieldToRead() {
        return fieldToRead;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isRead() {
        return read;
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
                .add("exit", exit)
                .toString();
    }

    public List<Integer> getIndexes() {
        return this.indexes;
    }

}
