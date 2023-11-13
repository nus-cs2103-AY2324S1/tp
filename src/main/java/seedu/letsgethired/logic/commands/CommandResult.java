package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    private final Optional<InternApplication> internApplication;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, InternApplication internApplication, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.internApplication = Optional.ofNullable(internApplication);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields {@code feedbackToUser} and {@code detailsToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, InternApplication internApplication) {
        this(feedbackToUser, internApplication, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showHelp} and {@code exit},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, null, showHelp, exit);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Optional<InternApplication> getInternApplicationResult() {
        return internApplication;
    }

    public boolean isShowHelp() {
        return showHelp;
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
                && exit == otherCommandResult.exit
                && internApplication.equals(otherCommandResult.internApplication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, internApplication, showHelp, exit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("internApplicationResult", internApplication)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .toString();
    }
}
