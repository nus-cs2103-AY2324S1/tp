package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.displayable.NameContainsKeywordsPredicate;

/**
 * Filters and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all buyers and sellers whose names contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;
    private final CommandWarnings commandWarnings;

    /**
     * Creates a FilterCommand that filters displayables based on whether their names are contained
     * in the given keywords of the predicate.
     * @param predicate the predicate to filter by.
     * @param commandWarnings a container for any warnings that occur.
     */
    public FilterCommand(NameContainsKeywordsPredicate predicate, CommandWarnings commandWarnings) {
        this.predicate = predicate;
        this.commandWarnings = commandWarnings;
    }
    public FilterCommand(NameContainsKeywordsPredicate predicate) {
        this(predicate, new CommandWarnings());
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(predicate);
        model.updateFilteredSellerList(predicate);
        if (commandWarnings.containsWarnings()) {
            return new CommandResult(commandWarnings.getWarningMessage());
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredBuyerList().size(),
                        model.getFilteredSellerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

}
