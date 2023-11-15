package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */

    private final Person personToView;

    private final Index targetIndex;

    private final boolean isFostererEdited;

    private final CommandType commandType;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(
            String feedbackToUser,
            Person personToView,
            Index targetIndex,
            CommandType commandType,
            boolean isFostererEdited
    ) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.personToView = personToView;
        this.targetIndex = targetIndex;
        this.commandType = commandType;
        this.isFostererEdited = isFostererEdited;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.personToView = null;
        this.targetIndex = null;
        this.commandType = commandType;
        this.isFostererEdited = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Person getPersonToView() {
        return personToView;
    }

    public Index getTargetIndex() {
        return targetIndex;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public boolean getIsFostererEdited() {
        return isFostererEdited;
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
                && Objects.equals(personToView, otherCommandResult.personToView)
                && Objects.equals(targetIndex, otherCommandResult.targetIndex)
                && commandType == otherCommandResult.commandType
                && isFostererEdited == otherCommandResult.isFostererEdited;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, personToView, targetIndex, commandType, isFostererEdited);
    }

    @Override
    public String toString() {
        ToStringBuilder t = new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("person", personToView)
                .add("targetIndex", targetIndex)
                .add("commandType", commandType)
                .add("isFostererEdited", isFostererEdited);
        return t.toString();
    }

}

