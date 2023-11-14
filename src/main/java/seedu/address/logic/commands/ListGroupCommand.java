package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all groups in the address book to the user.
 */
public class ListGroupCommand extends Command {

    public static final String COMMAND_WORD = "listGroup";

    public static final String MESSAGE_SUCCESS = "Viewing all groups";
    public static final String MESSAGE_FAILURE = "Error: Unable to retrieve group entries. Please try again.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}
