package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.RecipeAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Name;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;
import seedu.address.model.recipe.RecipeStep;
import seedu.address.model.recipe.exceptions.RecipeStepFormatException;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    // Todo Add JavaDocs
    /**
     * Stub
     * @param quantStr Stub
     * @return Stub
     * @throws ParseException Stub
     */
    public static Quantity parseQuantity(String quantStr, Unit unit) throws ParseException {
        requireNonNull(quantStr);
        double amount;
        try {
            amount = Double.parseDouble(quantStr);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid quantity: " + quantStr);
        }
        if (amount <= 0) {
            throw new ParseException("Quantity has to be positive");
        }
        return new Quantity(amount, unit);
    }


    /**
     * Parse the lightweight recipe ingredient string into an ingredient
     * @param ingredientString Lightweight ingredient format for only recipe ingredients
     * @return An ingredient instance
     * @throws ParseException When ingredient is incorrectly formatted
     */
    public static Ingredient parseRecipeIngredient(String ingredientString) throws ParseException {
        int whitespaceIndex = ingredientString.lastIndexOf(" ");
        if (whitespaceIndex == -1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RecipeAddCommand.MESSAGE_INGREDIENT_USAGE));
        }
        String ingredientName = ingredientString.substring(0, whitespaceIndex);
        String ingredientQuantity = ingredientString.substring(whitespaceIndex + 1);

        int unitIndex = ingredientQuantity.indexOf(ingredientQuantity.chars().filter(Character::isLetter)
            .findFirst().orElse(-1));
        if (unitIndex == -1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RecipeAddCommand.MESSAGE_INGREDIENT_USAGE));
        }
        String quantityValue = ingredientQuantity.substring(0, unitIndex);
        String quantityUnit = ingredientQuantity.substring(unitIndex);

        try {
            Quantity quantity = new Quantity(Double.parseDouble(quantityValue), Unit.parseUnit(quantityUnit));
            return new Ingredient(new Name(ingredientName), quantity);
        } catch (IllegalArgumentException fe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RecipeAddCommand.MESSAGE_STEP_USAGE));
        }
    }

    /**
     * Stub
     * @param unit Stub
     * @return Stub
     */
    public static Unit parseUnitOfIngredient(String unit) {
        requireNonNull(unit);
        return Unit.parseUnit(unit);
    }

    /**
     * Parse a recipeStep for the RecipeAddCommand.
     * @param recipeStepString The string format for the RecipeAddCommand
     * @return The recipeStep instance
     * @throws ParseException When step is incorrectly formatted
     */
    public static RecipeStep parseRecipeStep(String recipeStepString) throws ParseException {
        try {
            return RecipeStep.parseRecipeStep(recipeStepString);
        } catch (RecipeStepFormatException rsfe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RecipeAddCommand.MESSAGE_INGREDIENT_USAGE));
        }
    }

    //    /**
    //     * Parses a {@code String tag} into a {@code Tag}.
    //     * Leading and trailing whitespaces will be trimmed.
    //     *
    //     * @throws ParseException if the given {@code tag} is invalid.
    //     */
    //    public static Tag parseTag(String tag) throws ParseException {
    //        requireNonNull(tag);
    //        String trimmedTag = tag.trim();
    //        if (!Tag.isValidTagName(trimmedTag)) {
    //            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
    //        }
    //        return new Tag(trimmedTag);
    //    }
    //
    //    /**
    //     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
    //     */
    //    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
    //        requireNonNull(tags);
    //        final Set<Tag> tagSet = new HashSet<>();
    //        for (String tagName : tags) {
    //            tagSet.add(parseTag(tagName));
    //        }
    //        return tagSet;
    //    }
}
