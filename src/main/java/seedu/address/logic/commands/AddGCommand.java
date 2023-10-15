package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds GitHub account to candidates existing details.
 */
public class AddGCommand extends Command {
    public static final String COMMAND_WORD = "addL";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds LinkedIn to details of a candidate. "
            + "Parameters: " + "[" + COMMAND_WORD + " <USERID> <USERNAME>]...\n"
            + "Example: " + COMMAND_WORD + " 2 alexyeoh";

    public static final String MESSAGE_SUCCESS = "LinkedIn account added for: %1$s";

    private final int targetIndex;

    private final String username;

    /**
     * Creates an AddG Command to add Github.
     * @param targetIndex
     * @param username
     */
    public AddGCommand(int targetIndex, String username) {
        this.targetIndex = targetIndex;
        this.username = username;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(MESSAGE_SUCCESS, "PLACEHOLDER"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGCommand)) {
            return false;
        }

        AddGCommand otherAddGCommand = (AddGCommand) other;
        return this.targetIndex == otherAddGCommand.targetIndex && this.username.equals(otherAddGCommand.username);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("targetIndex", targetIndex).add("username", username).toString();
    }

}
