package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

/**
 * Finds and lists all applicants in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all applicants whose fields contain any of "
            + "the specified keyword (case-insensitive) or number and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "KEYWORD(S)] "
            + "[" + PREFIX_PHONE + "NUMBER] "
            + "[" + PREFIX_EMAIL + "KEYWORD(S)] "
            + "[" + PREFIX_ADDRESS + "KEYWORD(S)] "
            + "[" + PREFIX_TAG + "KEYWORD(S)]\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie p/98765432 e/example@mail.com";

    private final Predicate<Applicant> predicate;

    public FindCommand(Predicate<Applicant> predicate) {
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
