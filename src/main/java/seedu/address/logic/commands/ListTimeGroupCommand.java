package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.TimeIntervalList;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

import static java.util.Objects.requireNonNull;

public class ListTimeGroupCommand extends ListTimeCommand{
    public static final String MESSAGE_LISTTIME_GROUP_SUCCESS = "Listed times of Group: %1$s";
    public static final String MESSAGE_NO_GROUP_WITH_NAME_FOUND = "No group with such name found.\n"
            + "Please provide the group's full name as in the existing contactlist.";
    private final Group group;

    public ListTimeGroupCommand(Group group) {
        this.group = group;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasGroup(group)) {
            throw new CommandException(MESSAGE_NO_GROUP_WITH_NAME_FOUND);
        }
        TimeIntervalList timeIntervalList = model.getTimeFromGroup(group);
        return new CommandResult(String.format(MESSAGE_LISTTIME_GROUP_SUCCESS, group.getGroupName()) + timeIntervalList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListTimeGroupCommand)) {
            return false;
        }

        ListTimeGroupCommand otherListTimeGroupCommand = (ListTimeGroupCommand) other;
        return group.equals(otherListTimeGroupCommand.group);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("group name", group)
                .toString();
    }
}
