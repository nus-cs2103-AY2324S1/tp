package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

// TODO Add JavaDocs
/**
 * Stub JavaDocs
 */
public class StockCommand extends Command {

    public static final String COMMAND_WORD = "stock";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all specified ingredients and their respective"
            + "quantities. "
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " flour milk";

    private final Predicate<Ingredient> predicate;

    /**
     * Constructor with for stock command with an ingredient predicate
     * @param predicate
     */
    public StockCommand(Predicate<Ingredient> predicate) {
        //todo does this need a require non null?
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INGREDIENTS_LISTED_OVERVIEW, model.getFilteredIngredientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StockCommand)) {
            return false;
        }

        StockCommand otherStockCommand = (StockCommand) other;
        return predicate.equals(otherStockCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

}
