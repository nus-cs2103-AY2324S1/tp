package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FilterPolicyDescriptionPredicate;

/**
 * Filters the list of persons whose policy description contains any of the
 * argument keywords.
 */
public class FilterPolicyDescriptionCommand extends Command {

    public static final String COMMAND_WORD = "filterpolicydescription";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort by clients based on policy description. \n"
            + "Parameters: DESCRIPTION (must be a valid description) \n"
            + "Example: " + COMMAND_WORD + " Cancer Plan";

    public static final String MESSAGE_SUCCESS = "Filtered for policy description %s successfully";

    public static final String MESSAGE_MORETHANONE_DESCRIPTION_ERROR = "Please only enter one description";

    private final FilterPolicyDescriptionPredicate predicate;

    public FilterPolicyDescriptionCommand(FilterPolicyDescriptionPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(
                        Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterPolicyDescriptionCommand)) {
            return false;
        }

        FilterPolicyDescriptionCommand otherFilterPolicyCommand = (FilterPolicyDescriptionCommand) other;
        return predicate.equals(otherFilterPolicyCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
