package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ListTimeGroupCommand extends ListTimeCommand{
    public static final String MESSAGE_LISTTIME_GROUP_SUCCESS = "Listed times of Group: %1$s";
    private final String groupName;

    public ListTimeGroupCommand(String groupName) {
        this.groupName = groupName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
//        model.getFreeTimeFromGroup(groupName);
        return new CommandResult(String.format(MESSAGE_LISTTIME_GROUP_SUCCESS, groupName));
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
        return groupName.equals(otherListTimeGroupCommand.groupName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("group name", groupName)
                .toString();
    }
}
