package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ApplicantContainsKeywordsPredicate;


/**
 * Finds and lists all members in address book whose fields contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindApplicantCommand extends Command {

    public static final String COMMAND_WORD = "findapplicant";
    public static final String COMMAND_ALIAS = "finda";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_ALIAS
            + ": Finds all applicants whose information contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice 91119111 design";

    private final ApplicantContainsKeywordsPredicate predicate;

    /**
     * Creates a FindApplicantCommand to find the specified {@code Applicant}
     *
     * @param predicate The predicate to find the applicant.
     */
    public FindApplicantCommand(ApplicantContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindApplicantCommand)) {
            return false;
        }

        FindApplicantCommand otherFindCommand = (FindApplicantCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
