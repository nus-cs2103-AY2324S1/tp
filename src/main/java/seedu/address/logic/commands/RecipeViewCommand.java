package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeUuidMatchesPredicate;
import seedu.address.model.recipe.UniqueId;

/**
 * Views a specific recipe in the recipe list.
 */
public class RecipeViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views a specific recipe in the recipe book. "
            + "Parameters: "
            + "uuid (greater or equal to 1)"
            + "Example: " + COMMAND_WORD + " 1";

    private final RecipeUuidMatchesPredicate predicate;
    private final UniqueId uuid;

    /**
     * Creates an RecipeViewCommand to view the specified {@code Recipe}
     */
    public RecipeViewCommand(RecipeUuidMatchesPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.uuid = predicate.getUuid();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasRecipe(this.uuid)) {
            throw new CommandException(Messages.MESSAGE_RECIPE_DOES_NOT_EXIST);
        }

        model.updateFilteredRecipeList(predicate);
        ObservableList<Recipe> recipeLst = model.getFilteredRecipeList();
        assert recipeLst.size() == 1;
        return new CommandResult(String.format(Messages.MESSAGE_RECIPE_LISTED, uuid));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeViewCommand)) {
            return false;
        }

        RecipeViewCommand otherStockCommand = (RecipeViewCommand) other;
        return predicate.equals(otherStockCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
