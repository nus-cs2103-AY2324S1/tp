package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.RemindPredicate;

/**
 * Finds and lists all persons in address book whose policy expiry date is approaching within a certain period.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    private final RemindPredicate remindPredicate;

    /**
     * Constructor for FindCommand.
     *
     * @param remindPredicate the predicate to be used for filtering the persons list based on policy expiry date.
     */
    public RemindCommand(RemindPredicate remindPredicate) {
        this.remindPredicate = remindPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(remindPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
