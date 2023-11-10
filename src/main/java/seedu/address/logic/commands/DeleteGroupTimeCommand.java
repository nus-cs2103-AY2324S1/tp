package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;

/**
 * Deletes a group's meeting time
 */
public class DeleteGroupTimeCommand extends DeleteTimeCommand {
    private final ArrayList<TimeInterval> timeIntervalsToDelete;
    private final Group group;

    /**
     * Creates a DeleteGroupTimeCommand to Delete the specified {@code timeIntervalsToDelete}
     */
    public DeleteGroupTimeCommand(Group group, ArrayList<TimeInterval> timeIntervalsToDelete) {
        requireNonNull(group);
        requireNonNull(timeIntervalsToDelete);
        this.group = group;
        this.timeIntervalsToDelete = timeIntervalsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String commandOutcome;
        if (model.hasGroup(group)) {
            commandOutcome = model.deleteTimeFromGroup(group, timeIntervalsToDelete);
        } else {
            throw new CommandException("Group does not exists");
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TIME + "\n" + commandOutcome, group.getGroupName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteGroupTimeCommand)) {
            return false;
        }

        DeleteGroupTimeCommand otherDeleteGroupTimeCommand = (DeleteGroupTimeCommand) other;
        if (timeIntervalsToDelete.size() != otherDeleteGroupTimeCommand.timeIntervalsToDelete.size()) {
            return false;
        }
        boolean isSameArray = true;
        for (int i = 0; i < timeIntervalsToDelete.size(); i++) {
            isSameArray = isSameArray && timeIntervalsToDelete.get(i).equals(
                    otherDeleteGroupTimeCommand.timeIntervalsToDelete.get(i));
        }
        return group.nameEquals(otherDeleteGroupTimeCommand.group.getGroupName())
                && isSameArray;
    }
}
