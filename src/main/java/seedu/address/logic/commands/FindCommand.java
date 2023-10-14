package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.LicenceContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified fields (case-insensitive for values) and displays them as a list with index numbers.\n"
            + "Parameters: [n/NAME] [l/LICENCE PLATE] ...\n"
            + "Example: " + COMMAND_WORD + " n/Alice Rodriguez";

    private final NameContainsKeywordsPredicate namePredicate;
    private final LicenceContainsKeywordsPredicate licencePredicate;

    /**
     * Constructor for FindCommand
     *
     * @param namePredicate the predicate to be used for finding by name
     * @param licencePredicate the predicate to be used for finding by licence plate number
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate,
                       LicenceContainsKeywordsPredicate licencePredicate) {
        this.namePredicate = namePredicate;
        this.licencePredicate = licencePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (!namePredicate.isEmpty()) {
            model.updateFilteredPersonList(namePredicate);
        }
        if (!licencePredicate.isEmpty()) {
            model.updateFilteredPersonList(licencePredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
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
        return namePredicate.equals(otherFindCommand.namePredicate)
                && licencePredicate.equals(otherFindCommand.licencePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name predicate", namePredicate)
                .add("licence predicate", licencePredicate)
                .toString();
    }
}
