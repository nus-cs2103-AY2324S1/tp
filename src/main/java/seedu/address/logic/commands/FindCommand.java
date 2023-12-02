package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.company.predicates.NameContainsKeywordsPredicate;

/**
 * Finds and lists all companies in the address book whose name matches the criteria
 * specified in the provided predicate.
 *
 * For detailed matching behavior, see {@link NameContainsKeywordsPredicate}.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private static final Logger logger = LogsCenter.getLogger(FindCommand.class);

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all companies whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " micro ";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.info("Executing FindCommand with predicate: " + predicate);
        model.findCompanies(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW, model.getFilteredCompanyList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
