package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.logic.commands.DeleteCommand.MESSAGE_USAGE;

public class DeleteMeetingCommand extends Command {

    public static final String COMMAND_WORD = "delete_meeting";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes the meeting identified by the index number used in the displayed meeting list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from delete_meeting");
    }

}
