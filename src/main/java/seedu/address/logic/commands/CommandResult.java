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

    /** The application should list all teams. */
    private final boolean listTeam;

    /** The application should list all persons. */
    private final boolean listPerson;

    /** The application should show the Tree */
    private final boolean showTree;
    /** The application should show the Persons found */
    private final boolean findPerson;
    /** The application should show the Teams found */
    private final boolean findTeam;
    /** The application should refresh statistics */
    private final boolean isAddingOrDeleting;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean listTeam, boolean listPerson, boolean showTree, boolean findPerson,
                         boolean findTeam, boolean isAddingOrDeleting) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.listTeam = listTeam;
        this.listPerson = listPerson;
        this.showTree = showTree;
        this.findPerson = findPerson;
        this.findTeam = findTeam;
        this.isAddingOrDeleting = isAddingOrDeleting;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false, false, false);
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

    public boolean isListTeam() {
        return listTeam;
    }

    public boolean isListPerson() {
        return listPerson;
    }

    public boolean isShowTree() {
        return showTree;
    }
    public boolean isFindPerson() {
        return findPerson;
    }
    public boolean isFindTeam() {
        return findTeam;
    }
    public boolean isAddingOrDeleting() {
        return isAddingOrDeleting;
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
                && listTeam == otherCommandResult.listTeam
                && listPerson == otherCommandResult.listPerson
                && showTree == otherCommandResult.showTree
                && findPerson == otherCommandResult.findPerson
                && findTeam == otherCommandResult.findTeam
                && isAddingOrDeleting == otherCommandResult.isAddingOrDeleting;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, listTeam, listPerson, showTree, findPerson, findTeam,
                isAddingOrDeleting);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("listTeam", listTeam)
                .add("listPerson", listPerson)
                .add("showTree", showTree)
                .add("findPerson", findPerson)
                .add("findTeam", findTeam)
                .add("isAddingOrDeleting", isAddingOrDeleting)
                .toString();
    }
}
