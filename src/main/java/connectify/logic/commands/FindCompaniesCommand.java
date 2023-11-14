package connectify.logic.commands;

import static java.util.Objects.requireNonNull;

import connectify.commons.util.ToStringBuilder;
import connectify.logic.Messages;
import connectify.model.Model;
import connectify.model.company.CompanyNameContainsKeywordsPredicate;

/**
 * Finds and lists all companies in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCompaniesCommand extends Command {

    public static final String COMMAND_WORD = "findCompany";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all companies whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " apple google microsoft";

    private final CompanyNameContainsKeywordsPredicate predicate;

    public FindCompaniesCommand(CompanyNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCompanyList(predicate);

        int companyListSize = model.getFilteredCompanyList().size();

        return new CommandResult(
                String.format(Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW, companyListSize));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCompaniesCommand)) {
            return false;
        }

        FindCompaniesCommand otherFindCompaniesCommand = (FindCompaniesCommand) other;
        return predicate.equals(otherFindCompaniesCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
