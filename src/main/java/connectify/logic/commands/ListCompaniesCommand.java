package connectify.logic.commands;

import static connectify.model.Model.PREDICATE_SHOW_ALL_COMPANIES;
import static java.util.Objects.requireNonNull;

import connectify.model.InvalidEntityException;
import connectify.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCompaniesCommand extends Command {

    public static final String COMMAND_WORD = "companies";

    public static final String MESSAGE_SUCCESS = "Listed all companies.";
    public static final String EMPTY_LIST_MESSAGE = "There are no companies in Connectify.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        Integer size = model.getNumberOfCompanies();
        try {
            model.setCurrEntity("companies");
        } catch (InvalidEntityException e) {
            throw new AssertionError("An exception was thrown when setting the current entity to 'companies'");
        }

        if (size == 0) {
            return new CommandResult(EMPTY_LIST_MESSAGE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        } else if (other instanceof ListCompaniesCommand) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "ListCompaniesCommand";
    }
}
