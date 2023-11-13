package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all applicants in the address book to the user.
 */
public class ViewMembersCommand extends Command {

    public static final String COMMAND_WORD = "viewmembers";
    public static final String COMMAND_ALIAS = "viewm";

    public static final String MESSAGE_SUCCESS = "Listed all members";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
