package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class DeleteGroupTimeCommand extends DeleteTimeCommand {
    private final ArrayList<TimeInterval> timeIntervalsToDelete;
    private final Group group;

    public DeleteGroupTimeCommand(Group group, ArrayList<TimeInterval> timeIntervalsToDelete) {
        requireNonNull(group);
        requireNonNull(timeIntervalsToDelete);
        this.group = group;
        this.timeIntervalsToDelete = timeIntervalsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasGroup(group)) {
            model.deleteTimeFromGroup(group, timeIntervalsToDelete);
        } else {
            throw new CommandException("Group does not exists");
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TIME_SUCCESS, group.getGroupName()));
    }
}
