package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STALLS;

import seedu.address.model.Model;

/**
 * Sorts all stalls by average price in ascending order.
 */
public class SortStallPriceCommand extends Command {
    public static final String COMMAND_WORD = "sort-stalls-price";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all stalls by average price in ascending order."
            + "and displays them as a list with index number.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sorted all stalls by average price in ascending order.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);
        model.sortStallPrice();
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.ViewType.DEFAULT);
    }

}
