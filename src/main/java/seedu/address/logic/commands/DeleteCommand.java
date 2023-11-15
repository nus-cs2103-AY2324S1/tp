package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.UniqueId;


/**
 * Deletes a recipe identified using it's displayed index from the recipe book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the recipe identified by the Unique Id (uuid) in the displayed recipe list.\n"
            + "Parameters: UUID (must be a positive integer greater than 1)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_RECIPE_SUCCESS = "Deleted Recipe: %1$s";

    private final UniqueId uuid;

    public DeleteCommand(UniqueId uuid) {
        this.uuid = uuid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        assert uuid.getId() > 0;

        if (!model.hasRecipe(this.uuid)) {
            throw new CommandException(Messages.MESSAGE_RECIPE_DOES_NOT_EXIST);
        }

        Recipe deletedRecipe = model.getRecipe(this.uuid);
        model.deleteRecipe(deletedRecipe);

        return new CommandResult(String.format(MESSAGE_DELETE_RECIPE_SUCCESS, this.uuid));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return (this.uuid.equals(otherDeleteCommand.uuid));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("uuid", uuid)
                .toString();
    }
}
