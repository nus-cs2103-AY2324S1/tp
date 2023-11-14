package seedu.address.logic.commands.commandresults;

import static java.util.Objects.requireNonNull;

/**
 * Represents the result of a command execution.
 */
public abstract class CommandResult {

    /** Feedback to the user after running a command. */
    protected final String feedbackToUser;

    /** Help information should be shown to the user. */
    protected final boolean showHelp;

    /** The application should exit. */
    protected final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }


    /**
     * Returns the feedbackToUser from running the command.
     *
     * @return The feedback to the user.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Returns true if the command is to show the Help Dialog Box to the user.
     *
     * @return true if command needs to show the Help box.
     */
    public boolean isShowHelp() {
        return showHelp;
    }

    /**
     * Returns true if the command exits the program.
     *
     * @return true if the command exits the program.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * To be Overridden - true if the command displays an entity in the display box.
     *
     * @return true if the command displays an entity in the display box.
     */
    public abstract boolean isDisplayableCommandResult();

}
