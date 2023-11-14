package seedu.address.logic.commands.contacts;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.CompanyContainsKeywordsPredicate;

/**
 * Filters all persons in address book whose company contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FilterContactCompanyCommand extends Command {
    public static final String COMMAND_WORD = "filter-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons whose company name contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Google Shopee Citadel";

    private final CompanyContainsKeywordsPredicate predicate;

    public FilterContactCompanyCommand(CompanyContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterContactCompanyCommand)) {
            return false;
        }

        FilterContactCompanyCommand otherFilterContactCompanyCommand = (FilterContactCompanyCommand) other;
        return predicate.equals(otherFilterContactCompanyCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
