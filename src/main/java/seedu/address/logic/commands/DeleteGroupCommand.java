package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Removes group from the addressbook and person.
 */
public class DeleteGroupCommand extends DeleteCommand {
    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";
    private final String groupName;

    public DeleteGroupCommand(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Group groupToDelete = model.deleteGroup(this.groupName);

        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete.getGroupName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteGroupCommand)) {
            return false;
        }

        DeleteGroupCommand otherDeleteGroupCommand = (DeleteGroupCommand) other;
        return groupName.equals(otherDeleteGroupCommand.groupName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("group name", groupName)
            .toString();
    }
}
