package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    enum ViewType {
        DEFAULT, SHOW_HELP, EXIT, STALL_DETAIL, VIEW_ITEM
    }

    private final String feedbackToUser;
    private final ViewType viewType;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, ViewType viewType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.viewType = viewType;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, ViewType.DEFAULT);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }
    public String getViewTypeString() {
        return viewType.toString();
    }

    public boolean isShowHelp() {
        return viewType == ViewType.SHOW_HELP;
    }

    public boolean isExit() {
        return viewType == ViewType.EXIT;
    }

    public boolean isStallDetail() {
        return viewType == ViewType.STALL_DETAIL;
    }

    public boolean isViewItem() {
        return viewType == ViewType.VIEW_ITEM;
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
                && viewType == otherCommandResult.viewType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, viewType);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("viewType", viewType)
                .toString();
    }

}
