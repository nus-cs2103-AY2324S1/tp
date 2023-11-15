package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NEG_UUID_DESC_RECIPE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_UNIT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ZERO_UUID_DESC_RECIPE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_FLOUR;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_FLOUR;
import static seedu.address.logic.commands.CommandTestUtil.UNIT_DESC_FLOUR;
import static seedu.address.logic.commands.CommandTestUtil.UUID_DESC_RECIPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UUID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ModifyCommand;
import seedu.address.model.Name;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;
import seedu.address.model.recipe.UniqueId;


public class ModifyCommandParserTest {
    private ModifyCommandParser parser = new ModifyCommandParser();

    @Test
    public void parse_validArgs_returnsModifyCommand() {
        Ingredient editedIngredient = new Ingredient(new Name("Flour"), new Quantity(100.0, Unit.GRAM));
        ModifyCommand command = new ModifyCommand(new UniqueId(1), editedIngredient);
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + UUID_DESC_RECIPE + NAME_DESC_FLOUR + QUANTITY_DESC_FLOUR
                + UNIT_DESC_FLOUR, command);
    }
    @Test
    public void parse_nonValidArgs_throwsIllegalArgumentException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + "y/test", "Invalid command format! "
                + "\n" + "modify: Modifies the ingredients used in the recipe by the uuid used in the displayed recipe"
                + " list. Existing values will be overwritten by the input values."
                + "\n" + "Parameters: i/UUID n/NAME q/QUANTITY u/UNIT Example: modify i/1 n/milk q/600 u/g ");
    }

    @Test
    public void parse_negativeQuantity_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + UUID_DESC_RECIPE + NAME_DESC_FLOUR + " q/-100 "
                        + UNIT_DESC_FLOUR, "Quantity has to be positive");
    }

    @Test
    public void parse_nonNumericQuantity_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + UUID_DESC_RECIPE + NAME_DESC_FLOUR + " q/e "
                        + UNIT_DESC_FLOUR, "Invalid quantity: e");
    }

    @Test
    public void parse_uuidEquals_zero() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_ZERO_UUID_DESC_RECIPE + NAME_DESC_FLOUR
                + QUANTITY_DESC_FLOUR + UNIT_DESC_FLOUR, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void parse_uuid_negative() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_NEG_UUID_DESC_RECIPE + NAME_DESC_FLOUR
                + QUANTITY_DESC_FLOUR + UNIT_DESC_FLOUR, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalid_units() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + UUID_DESC_RECIPE + NAME_DESC_FLOUR
                + QUANTITY_DESC_FLOUR + " u/ml ", "This is not a valid unit!");
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedIngredientString = UUID_DESC_RECIPE + NAME_DESC_FLOUR + QUANTITY_DESC_FLOUR
                + UNIT_DESC_FLOUR;

        //multiple uuids
        assertParseFailure(parser, UUID_DESC_RECIPE + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_UUID));

        // multiple names
        assertParseFailure(parser, NAME_DESC_FLOUR + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple quantities
        assertParseFailure(parser, QUANTITY_DESC_FLOUR + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_QUANTITY));

        // multiple units
        assertParseFailure(parser, UNIT_DESC_FLOUR + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_UNIT));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedIngredientString + UUID_DESC_RECIPE + NAME_DESC_FLOUR + QUANTITY_DESC_FLOUR
                        + UNIT_DESC_FLOUR + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_UUID, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_UNIT));

        // invalid value followed by valid value

        // invalid uuid
        assertParseFailure(parser, INVALID_NEG_UUID_DESC_RECIPE + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_UUID));

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid quantity
        assertParseFailure(parser, INVALID_QUANTITY_DESC + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_QUANTITY));

        // invalid unit
        assertParseFailure(parser, INVALID_UNIT_DESC + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_UNIT));


        // valid value followed by invalid value

        // invalid uuid
        assertParseFailure(parser, validExpectedIngredientString + INVALID_NEG_UUID_DESC_RECIPE,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_UUID));

        // invalid name
        assertParseFailure(parser, validExpectedIngredientString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid quantity
        assertParseFailure(parser, validExpectedIngredientString + INVALID_QUANTITY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_QUANTITY));

        // invalid quantity
        assertParseFailure(parser, validExpectedIngredientString + INVALID_UNIT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_UNIT));

    }


}
