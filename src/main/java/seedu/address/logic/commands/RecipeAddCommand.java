package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;

/**
 * Views a specific recipe in the recipe list.
 */
public class RecipeAddCommand extends Command {
    public static final String COMMAND_WORD = "addrecipe";

    public static final String MESSAGE_INGREDIENT_USAGE = "Ingredient format: NAME NUMBER-UNIT (e.g. flour 100g)";

    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";

    public static final String MESSAGE_STEP_USAGE = "Step format: NUMBER. STEP (e.g. 1. Preheat Oven)";

    private final Recipe toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Ingredient}
     */
    public RecipeAddCommand(Recipe recipe) {
        requireNonNull(recipe);
        toAdd = recipe;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addRecipe(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeAddCommand)) {
            return false;
        }

        RecipeAddCommand otherAddCommand = (RecipeAddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
