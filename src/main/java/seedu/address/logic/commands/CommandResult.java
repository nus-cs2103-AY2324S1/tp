package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private final String commandWord;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, String commandWord) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandWord = commandWord;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, "");
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public boolean isListToday() {
        return commandWord.equals(ListTodayCommand.COMMAND_WORD);
    }

    public boolean isListCompleted() {
        return commandWord.equals(ListCompletedCommand.COMMAND_WORD);
    }

    /**
     * Checks if the command can alter a {@code Prescription}'s the expiry date or total stock.
     */
    public boolean affectsReminders() {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
        case TakeCommand.COMMAND_WORD:
        case UntakeCommand.COMMAND_WORD:
        case EditCommand.COMMAND_WORD:
            return true;
        default:
            return false;
        }
    }

    public boolean isShowHelp() {
        return commandWord.equals(HelpCommand.COMMAND_WORD);
    }

    public boolean isExit() {
        return commandWord.equals(ExitCommand.COMMAND_WORD);
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
                && commandWord.equals(otherCommandResult.commandWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandWord);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("commandWord", commandWord)
                .toString();
    }

}
