package seedu.application.logic.commands;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Deadline;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets submission deadline for an existing application in the list. \n"
            + "by the index number used in the last Job listing. "
            + "Existing deadline will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) d/ [DEADLINE]\n"
            + "Example: " + COMMAND_WORD + " 1 " + "d/ Dec 31 2030 1200.";

    public static final String MESSAGE_SET_DEADLINE_SUCCESS = "Set Deadline: %1$s";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    private final Index index;
    private final Deadline deadline;

    /**
     * @param index    of the job in the job list to edit the deadline
     * @param deadline of the job to be updated to
     */
    public DeadlineCommand(Index index, Deadline deadline) {
        requireAllNonNull(index, deadline);
        this.index = index;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, index.getOneBased(), deadline));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineCommand)) {
            return false;
        }

        DeadlineCommand e = (DeadlineCommand) other;
        return index.equals(e.index)
                && deadline.equals(e.deadline);
    }
}
