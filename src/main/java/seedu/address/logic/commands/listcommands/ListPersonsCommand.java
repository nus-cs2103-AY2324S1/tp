package seedu.address.logic.commands.listcommands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListPersonsCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new RegularCommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // instanceof handles nulls
        if (!(other instanceof ListPersonsCommand)) {
            return false;
        }

        return true;
    }
}
