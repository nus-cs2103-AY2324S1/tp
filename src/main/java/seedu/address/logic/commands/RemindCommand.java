package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Finds and lists all persons in address book whose policy expiry date is approaching within a certain period.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
