package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STALLS;

import seedu.address.model.Model;

/**
 * Sorts all stalls by location in alphabetical order.
 */
public class SortStallRatingCommand extends Command {

    public static final String COMMAND_WORD = "sort-stalls-rating";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all stalls by rating in descending order."
            + "and displays them as a list with index number.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sorted all stalls by rating in descending order.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);
        model.sortStallRating();
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.ViewType.DEFAULT);
    }
}
