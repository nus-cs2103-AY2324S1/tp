package transact.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import transact.commons.util.ToStringBuilder;
import transact.ui.MainWindow.TabWindow;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final TabWindow tabWindow;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    private final boolean clearResultDisplay;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, TabWindow tabWindow, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.tabWindow = tabWindow;
        this.showHelp = showHelp;
        this.exit = exit;
        this.clearResultDisplay = false;
    }
    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, TabWindow tabWindow, boolean showHelp, boolean exit, boolean clearResultDisplay) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.tabWindow = tabWindow;
        this.showHelp = showHelp;
        this.exit = exit;
        this.clearResultDisplay = clearResultDisplay;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * ,switches to the specified {@code tabWindow}
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, TabWindow tabWindow) {
        this(feedbackToUser, tabWindow, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, TabWindow.CURRENT, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public TabWindow getTabWindow() {
        return tabWindow;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isClearResultDisplay() {
        return clearResultDisplay;
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
        return Objects.hash(feedbackToUser, tabWindow, showHelp, exit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("tabWindow", tabWindow)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .toString();
    }

}
