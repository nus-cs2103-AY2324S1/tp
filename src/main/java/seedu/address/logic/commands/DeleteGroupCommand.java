package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Deletes a group identified by its group number.
 */
public class DeleteGroupCommand extends Command {
    public static final String COMMAND_WORD = "deleteGroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the group identified by its group number.\n"
            + "Parameters: gr/GROUP_NUMBER\n"
            + "Example: " + COMMAND_WORD + " gr/1";

    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Group deleted successfully! Deleted Group: %1$s";
    public static final String MESSAGE_DELETE_GROUP_NOT_FOUND = "Group with the provided group number not found.";

    private final int groupNumber;

    /**
     * Creates a DeleteGroupCommand to delete the group with the specified group number.
     *
     * @param groupNumber The group number of the group to be deleted.
     */
    public DeleteGroupCommand(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        requireNonNull(model);

        // Check if the group with the provided group number exists
        Group groupToDelete = model.getGroupWithNumber(groupNumber)
                .orElseThrow(() -> new CommandException(MESSAGE_DELETE_GROUP_NOT_FOUND));

        // Delete the group from the model
        model.deleteGroup(groupToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete.getNumber()),
                false, false, true, false);
    }

    /**
     * Gets the group number of the group to be deleted.
     *
     * @return The group number.
     */
    public int getGroupNumber() {
        return groupNumber;
    }
}
