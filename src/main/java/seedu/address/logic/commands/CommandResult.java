package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** The command type producing the result, used for deciding application UI action **/
    private final CommandType commandType;

    /** The tab index to switch to **/
    private final Index tabIndex;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType, Index index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = commandType;
        this.tabIndex = index;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}
     * and {@code commandType}, and index field set to their default value.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        this(feedbackToUser, commandType, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public CommandType getCommandType() {
        return commandType;
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
                && commandType == otherCommandResult.commandType;
    }

    /**
     * A method to return the target tab index.
     *
     * @return The zero based index of the target tab.
     */
    public int getTabIndex() {
        return this.tabIndex.getZeroBased();
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandType);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("commandType", commandType)
                .toString();
    }

}
