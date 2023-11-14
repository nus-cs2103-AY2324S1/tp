package seedu.address.logic.commands.listcommands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPANIES;

import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.model.Model;

/**
 * Lists all companies in the address book to the user.
 */
public class ListCompaniesCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all companies";

    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        return new RegularCommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // instanceof handles nulls
        if (!(other instanceof ListCompaniesCommand)) {
            return false;
        }

        return true;
    }
}
