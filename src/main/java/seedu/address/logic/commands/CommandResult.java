package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private static final int INVALID_INDEX = -2;

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should show the task list in the bottom list slot. */
    private final boolean switchBottomList;

    private final ViewEventsIndicator eventViewIndex;

    /** Application should display calendar comparison result */
    private final boolean showCalendarComparison;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showCalendarComparison,
                         boolean switchBottomList) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showCalendarComparison = showCalendarComparison;
        this.switchBottomList = switchBottomList;
        this.eventViewIndex = new ViewEventsIndicator(INVALID_INDEX);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code eventViewIndex}, and other fields set to their default value
     */
    public CommandResult(String feedbackToUser, Index eventViewIndex) {
        this.feedbackToUser = feedbackToUser;
        this.showHelp = false;
        this.exit = false;
        this.showCalendarComparison = false;
        this.switchBottomList = false;
        this.eventViewIndex = new ViewEventsIndicator(eventViewIndex.getOneBased());
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

    public boolean isSwitchBottomList() {
        return switchBottomList;
    }

    public Index getEventViewIndex() {
        return eventViewIndex.getIndex();
    }

    public boolean isViewEvents() {
        return eventViewIndex.isViewEvents();
    }

    public boolean isShowCalendarComparison() {
        return showCalendarComparison;
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
                && switchBottomList == otherCommandResult.switchBottomList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, switchBottomList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("switchBottomList", switchBottomList)
                .toString();
    }

    /**
     * An indicator class to indicate if an event list is being viewed.
     */
    public static class ViewEventsIndicator {
        private static final int MINIMUM_INDEX = 1;
        private final Index index;
        private final boolean isViewEvents;

        /**
         * Constructs a valid indicator if the index is above 1 and an invalid one otherwise.
         */
        public ViewEventsIndicator(int index) {
            if (index >= MINIMUM_INDEX) {
                this.index = Index.fromOneBased(index);
                isViewEvents = true;
            } else {
                this.index = Index.fromOneBased(MINIMUM_INDEX);
                isViewEvents = false;
            }
        }

        /**
         * Checks if the result requires events to be viewed.
         */
        public boolean isViewEvents() {
            return isViewEvents;
        }

        public Index getIndex() {
            return index;
        }
    }

}
