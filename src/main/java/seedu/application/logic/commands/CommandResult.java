package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.application.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;
    /**
     * The window should be cleared.
     */
    private final boolean clear;
    /**
     * The index of the interview to be shown.
     */
    private final int interview;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean clear, int interview) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.clear = clear;
        this.interview = interview;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, -1);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isClear() {
        return clear;
    }

    public int interviewIndex() {
        return interview;
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
                   && clear == otherCommandResult.clear
                   && interview == otherCommandResult.interview;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, clear, interview);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                   .add("feedbackToUser", feedbackToUser)
                   .add("showHelp", showHelp)
                   .add("exit", exit)
                   .add("clear", clear)
                   .add("interview", interview)
                   .toString();
    }

}
