package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.predicates.RemindPredicate;

/**
 * Finds and lists all persons in address book whose policy expiry date is approaching within a certain period.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose policy expiry dates is within "
            + "the specified number of days.\n"
            + "Parameters: Number of days\n"
            + "Example: " + COMMAND_WORD + " 30";

    private final RemindPredicate remindPredicate;


    /**
     * Constructor for RemindCommand.
     *
     * @param remindPredicate the predicate to be used for filtering the persons list based on policy expiry date.
     */
    public RemindCommand(RemindPredicate remindPredicate) {
        this.remindPredicate = remindPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert remindPredicate != null : "Predicate should never be null";
        model.updateFilteredPersonList(remindPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemindCommand)) {
            return false;
        }

        RemindCommand otherFindCommand = (RemindCommand) other;
        return remindPredicate.equals(otherFindCommand.remindPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("remind predicate", remindPredicate)
                .toString();
    }
}
