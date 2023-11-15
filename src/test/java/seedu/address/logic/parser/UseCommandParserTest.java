package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_UNIT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_FLOUR;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_FLOUR;
import static seedu.address.logic.commands.CommandTestUtil.UNIT_DESC_FLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.UseCommand;
import seedu.address.model.Name;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;

public class UseCommandParserTest {

    private UseCommandParser parser = new UseCommandParser();

    @Test
    public void parse_validArgs_returnsUseCommand() {
        UseCommand command = new UseCommand(new Name("Flour"), new Quantity(100.0, Unit.GRAM));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_FLOUR + QUANTITY_DESC_FLOUR
                + UNIT_DESC_FLOUR, command);
    }

    @Test
    public void parse_nonValidArgs_throwsIllegalArgumentException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + "y/test", "Invalid command format! \n"
                + "use: Depletes a specified amount of an ingredient from stock. "
                + "Parameters: n/NAME [q/QUANTITY] [u/UNIT] Example: use n/milk q/600 u/g ");
    }

    @Test
    public void parse_noQuantityAndUnit_returnsUseCommand() {
        UseCommand command = new UseCommand(new Name("Flour"), null);
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_FLOUR, command);
    }
    @Test
    public void parse_noQuantity_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FLOUR + UNIT_DESC_FLOUR,
                "Invalid command format! \n"
                        + "use: Depletes a specified amount of an ingredient from stock. "
                        + "Parameters: n/NAME [q/QUANTITY] [u/UNIT] Example: use n/milk q/600 u/g ");
    }

    @Test
    public void parse_noUnit_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FLOUR + QUANTITY_DESC_FLOUR,
                "Invalid command format! \n"
                        + "use: Depletes a specified amount of an ingredient from stock. "
                        + "Parameters: n/NAME [q/QUANTITY] [u/UNIT] Example: use n/milk q/600 u/g ");
    }


    @Test
    public void parse_negativeQuantity_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FLOUR + " q/-100 " + UNIT_DESC_FLOUR,
                "Quantity has to be positive");
    }

    @Test
    public void parse_nonNumericQuantity_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FLOUR + " q/onehundred " + UNIT_DESC_FLOUR,
                "Invalid quantity: onehundred");
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedIngredientString = NAME_DESC_FLOUR + QUANTITY_DESC_FLOUR + UNIT_DESC_FLOUR;

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
                validExpectedIngredientString + NAME_DESC_FLOUR + QUANTITY_DESC_FLOUR + UNIT_DESC_FLOUR
                        + validExpectedIngredientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_QUANTITY, PREFIX_UNIT));

        // invalid value followed by valid value

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
