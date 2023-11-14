package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;

import seedu.lovebook.model.Model;
import seedu.lovebook.model.date.Date;

/**
 * Lists all Dates in the LoveBook to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all Dates";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateSortedDateList(Comparator.<Date>naturalOrder());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
