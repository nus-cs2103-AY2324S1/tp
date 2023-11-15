package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.exceptions.UnitConversionException;

/**
 * Use an ingredient from the inventory.
 */
public class UseCommand extends Command {

    public static final String COMMAND_WORD = "use";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Depletes a specified amount of an ingredient from stock. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_QUANTITY + "QUANTITY] "
            + "[" + PREFIX_UNIT + "UNIT] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "milk "
            + PREFIX_QUANTITY + "600 "
            + PREFIX_UNIT + "g ";

    public static final String MESSAGE_SUCCESS = "Ingredient used: %1$s";

    private final Name toUse;
    private final Quantity quantityUsed;
    /**
     * Creates a UseCommand to use the specified {@code Ingredient}
     */
    public UseCommand(Name ingredient, Quantity quantity) {
        requireNonNull(ingredient);
        toUse = ingredient;
        quantityUsed = quantity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (quantityUsed == null) {
            model.useIngredient(toUse, model.getQuantityOf(toUse));
            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    Messages.format(new Ingredient(toUse, model.getQuantityOf(toUse)))));
        }
        try {
            model.useIngredient(toUse, quantityUsed);
        } catch (UnitConversionException uce) {
            throw new CommandException(uce.getMessage());
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                Messages.format(new Ingredient(toUse, model.getQuantityOf(toUse)))));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UseCommand)) {
            return false;
        }

        UseCommand otherUseCommand = (UseCommand) other;
        return toUse.equals(otherUseCommand.toUse);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toUse", toUse)
                .toString();
    }
}
