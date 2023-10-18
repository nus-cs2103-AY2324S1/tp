package seedu.staffsnap.logic.commands;

import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.CustomFilterPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_POSITION;

/**
 * Finds and lists all applicants in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all applicants who match the descriptor.";
    public static final String MESSAGE_FAILURE = "Please add at least one field to filter by. " +
            "Possible fields include:" + "\n" +
            PREFIX_NAME + " [NAME], " +
            PREFIX_EMAIL + " [EMAIL], " +
            PREFIX_POSITION + " [POSITION], " +
            PREFIX_PHONE + " [PHONE]";

    private final CustomFilterPredicate predicate;

    public FilterCommand(CustomFilterPredicate predicate) {
        this.predicate = predicate;
    }

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
