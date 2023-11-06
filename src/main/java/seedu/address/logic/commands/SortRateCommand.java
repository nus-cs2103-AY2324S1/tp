package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

/**
 * Rate all interviews in descending order in the address book to the user.
 */
public class SortRateCommand extends Command {

    public static final String COMMAND_WORD = "sort-rate";

    public static final String MESSAGE_SUCCESS = "Interviews are sorted by ratings successfully";

    public final Comparator<Interview> comparator = Comparator.comparing(Interview::getRating);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortInterviewList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
