package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ITEM_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ITEM_PRICE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STALL_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_DESC_CHICKEN_RICE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_DESC_NASI_LEMAK;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_NASI_LEMAK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_INDEX_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_INDEX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_STALL_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalItems.NASI_LEMAK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddItemCommand;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Price;
import seedu.address.testutil.ItemBuilder;

public class AddItemCommandParserTest {
    private static final String TYPICAL_STALL_INDEX = VALID_STALL_INDEX_DESC;
    private AddItemCommandParser parser = new AddItemCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Item expectedItem = new ItemBuilder(NASI_LEMAK).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TYPICAL_STALL_INDEX + ITEM_DESC_NASI_LEMAK
                + VALID_PRICE_DESC, new AddItemCommand(INDEX_FIRST_STALL, expectedItem));

    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedItemString = TYPICAL_STALL_INDEX + ITEM_DESC_NASI_LEMAK;

        // multiple names
        assertParseFailure(parser, validExpectedItemString + ITEM_DESC_CHICKEN_RICE + VALID_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple indexes
        assertParseFailure(parser, TYPICAL_STALL_INDEX + validExpectedItemString + VALID_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STALL));

        // multiple prices
        assertParseFailure(parser, validExpectedItemString + VALID_PRICE_DESC + VALID_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRICE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedItemString + ITEM_DESC_CHICKEN_RICE + TYPICAL_STALL_INDEX
                        + validExpectedItemString + VALID_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STALL, PREFIX_NAME));

        // invalid value followed by valid value

        // invalid stall
        assertParseFailure(parser, INVALID_STALL_INDEX_DESC + validExpectedItemString + VALID_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STALL));


        // invalid item
        assertParseFailure(parser, INVALID_ITEM_NAME_DESC + validExpectedItemString + VALID_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // valid value followed by invalid value

        // invalid stall
        assertParseFailure(parser, validExpectedItemString + INVALID_STALL_INDEX_DESC + VALID_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STALL));


        // invalid item
        assertParseFailure(parser, validExpectedItemString + INVALID_ITEM_NAME_DESC + VALID_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        //invalid price
        assertParseFailure(parser, validExpectedItemString + VALID_PRICE_DESC + INVALID_ITEM_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRICE));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE);

        // missing stall prefix
        assertParseFailure(parser, VALID_STALL_INDEX_1 + ITEM_DESC_NASI_LEMAK,
                expectedMessage);

        // missing Item prefix
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + VALID_ITEM_NAME_NASI_LEMAK,
                expectedMessage);

        //missing item price prefix
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + ITEM_DESC_NASI_LEMAK
                + VALID_ITEM_PRICE_1, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_STALL_INDEX_1 + VALID_ITEM_NAME_NASI_LEMAK + VALID_ITEM_PRICE_1,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, TYPICAL_STALL_INDEX + INVALID_ITEM_NAME_DESC + VALID_PRICE_DESC,
                ItemName.MESSAGE_CONSTRAINTS);

        // invalid stall index
        assertParseFailure(parser, INVALID_STALL_INDEX_DESC + ITEM_DESC_NASI_LEMAK + VALID_PRICE_DESC,
                MESSAGE_INVALID_STALL_INDEX);

        //invalid item price
        assertParseFailure(parser, TYPICAL_STALL_INDEX + ITEM_DESC_NASI_LEMAK + INVALID_ITEM_PRICE_DESC,
                Price.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_STALL_INDEX_DESC + INVALID_ITEM_NAME_DESC + VALID_PRICE_DESC,
                MESSAGE_INVALID_STALL_INDEX);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TYPICAL_STALL_INDEX + ITEM_DESC_NASI_LEMAK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
    }
}
