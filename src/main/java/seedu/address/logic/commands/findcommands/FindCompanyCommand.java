package seedu.address.logic.commands.findcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.model.Model;
import seedu.address.model.company.CompanyNameAndTagContainKeywordsPredicate;

/**
 * Finds and lists all companies in address book whose companyName
 * contains any of the argument keywords in their companyNames or tags.
 * Keyword matching is case-insensitive.
 */
public class FindCompanyCommand extends FindCommand {
    public static final String MESSAGE_SUCCESS = "Success message for the find command!";

    private final CompanyNameAndTagContainKeywordsPredicate predicate;

    /**
     * Creates a FindCompanyCommand to find companies
     * with the specified {@code CompanyNameAndTagContainKeywordsPredicate}
     */
    public FindCompanyCommand(CompanyNameAndTagContainKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCompanyList(predicate);
        return new RegularCommandResult(
            String.format(Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW, model.getFilteredCompanyList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCompanyCommand)) {
            return false;
        }

        FindCompanyCommand otherFindCompanyCommand = (FindCompanyCommand) other;
        return predicate.equals(otherFindCompanyCommand.predicate);
    }
}
