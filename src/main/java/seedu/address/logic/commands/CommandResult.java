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

    private final boolean isView;

    private final boolean showEvent;
    private final boolean listTags;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean isView,
                         boolean showEvent, boolean listTags) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isView = isView;
        this.showEvent = showEvent;
        this.listTags = listTags;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * @param feedbackToUser feedback to user
     * @param isView whether to show the view
     */
    public CommandResult(String feedbackToUser, boolean isView) {
        this(feedbackToUser, false, false, isView, false, false);
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

    /**
     * Returns true if the command result is to show the view.
     * @return  true if the command result is to show the view
     */
    public boolean isView() {
        return isView;
    }

    public boolean isShowEvent() {
        return showEvent;
    }

    public boolean isListTags() {
        return listTags;
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
                && isView == otherCommandResult.isView
                && showEvent == otherCommandResult.showEvent
                && listTags == otherCommandResult.listTags;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, isView, showEvent, listTags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("isView", isView)
                .add("showEvent", showEvent)
                .add("listTags", listTags)
                .toString();
    }

}
