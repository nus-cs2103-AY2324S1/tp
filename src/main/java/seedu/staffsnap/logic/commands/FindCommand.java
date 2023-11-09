package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.NameContainsKeywordsPredicate;

/**
 * Finds and lists all applicants in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all applicants whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_WRONG_FORMAT = "Keyword(s) must be alphanumerical!"
            + "\nExample: LEE2, lee, Johnny Haw";

    public static final String MESSAGE_TOO_LONG =
            "Please keep the keyword(s) to 55 or less characters including spaces!";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the find command.
     * Find Command performs an OR search of the names of applicants.
     * Any applicant whose name contains any of the keywords will be included in the resulting list.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which contains the list of applicants whose name contains any of the keywords given.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()));
    }

    /**
     * Checks if the applicant exists.
     * @param other Other applicant.
     * @return true if equals, false if not equals.
     */
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
