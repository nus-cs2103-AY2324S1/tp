package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ITEM_PRICE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_DESC_CHICKEN_RICE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_DESC_NASI_LEMAK;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_NASI_LEMAK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_INDEX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STALL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditItemCommand;
import seedu.address.logic.commands.EditStallCommand;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Price;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;
import seedu.address.testutil.EditItemDescriptorBuilder;

public class EditItemCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditItemCommand.MESSAGE_USAGE);

    private EditItemCommandParser parser = new EditItemCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no item index specified
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + VALID_ITEM_NAME_NASI_LEMAK, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + VALID_ITEM_INDEX_DESC,
                EditStallCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidStallIndex_failure() {
        // EP: larger than max int
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "2147483648" // boundary value
                + VALID_ITEM_INDEX_DESC
                + ITEM_DESC_NASI_LEMAK, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);

        // EP: negative integer
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "-1" // boundary value
                + VALID_ITEM_INDEX_DESC
                + ITEM_DESC_NASI_LEMAK, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "-2147483647" // boundary value
                + VALID_ITEM_INDEX_DESC
                + ITEM_DESC_NASI_LEMAK, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);

        // EP: zero
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "0" // boundary value
                + VALID_ITEM_INDEX_DESC
                + ITEM_DESC_NASI_LEMAK, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);

        // EP: non-integer
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "some random string"
                + VALID_ITEM_INDEX_DESC, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1.5"
                + VALID_ITEM_INDEX_DESC, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);

        // EP: empty strings
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + ""
                + VALID_ITEM_INDEX_DESC, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "    "
                + VALID_ITEM_INDEX_DESC, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1 l/ string"
                + VALID_ITEM_INDEX_DESC, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidItemIndex_failure() {
        // EP: larger than max int
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + PREAMBLE_WHITESPACE + " "
                + PREFIX_ITEM + "2147483648" // boundary value
                + ITEM_DESC_NASI_LEMAK, MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);

        // EP: negative integer
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + PREAMBLE_WHITESPACE + " "
                + PREFIX_ITEM + "-1" + ITEM_DESC_NASI_LEMAK, MESSAGE_INVALID_ITEM_DISPLAYED_INDEX); //boundary value
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + PREAMBLE_WHITESPACE + " "
                + PREFIX_ITEM + "-2147483647" // boundary value
                + ITEM_DESC_NASI_LEMAK, MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);

        // EP: zero
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + PREAMBLE_WHITESPACE + " "
                + PREFIX_ITEM + "0" + ITEM_DESC_NASI_LEMAK, MESSAGE_INVALID_ITEM_DISPLAYED_INDEX); // boundary value

        // EP: non-integer
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + PREAMBLE_WHITESPACE + " "
                + PREFIX_ITEM + "some random string", MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + PREAMBLE_WHITESPACE + " "
                + PREFIX_ITEM + "1.5", MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);

        // EP: empty strings
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + PREAMBLE_WHITESPACE + " "
                + PREFIX_ITEM + "", MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + PREAMBLE_WHITESPACE + " "
                + PREFIX_ITEM + "    ", MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + PREAMBLE_WHITESPACE + " "
                + PREFIX_ITEM + "1 l/ string", MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // EP: empty string
        assertParseFailure(parser, VALID_STALL_INDEX_DESC
                + VALID_ITEM_INDEX_DESC
                + INVALID_NAME_DESC, ItemName.MESSAGE_CONSTRAINTS); // invalid name

        // EP: empty string
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC
                + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS); // invalid description

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, VALID_STALL_INDEX_DESC
                + VALID_ITEM_INDEX_DESC
                + INVALID_NAME_DESC + INVALID_ITEM_PRICE_DESC
                + INVALID_RATING_DESC + INVALID_DESCRIPTION_DESC,
                ItemName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPrice_failure() {
        // EP: empty string
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC + " "
                + PREFIX_PRICE + "", Price.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC + " "
                + PREFIX_PRICE + "   ", Price.MESSAGE_CONSTRAINTS);

        // EP: non-numeric
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC + " "
                + PREFIX_PRICE + "a", Price.MESSAGE_CONSTRAINTS);

        // EP: more than 2 decimal places
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC + " "
                + PREFIX_PRICE + "1.000", Price.MESSAGE_CONSTRAINTS);

        // EP: less than 2 decimal places
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC + " "
                + PREFIX_PRICE + "1.1", Price.MESSAGE_CONSTRAINTS);

        // EP: negative double
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC + " "
                + PREFIX_PRICE + "-10.00", Price.MESSAGE_CONSTRAINTS);

        // EP: leading zeros not removed for numbers >= 1.00
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC + " "
                + PREFIX_PRICE + "010.00", Price.MESSAGE_CONSTRAINTS);

        // EP: more than 1 leading zeros for numbers < 1.00
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC + " "
                + PREFIX_PRICE + "00.01", Price.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidRating_failure() {
        // EP: non-integer
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC
                + INVALID_RATING_DESC, Rating.MESSAGE_CONSTRAINTS); // invalid rating
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC
                + " " + PREFIX_RATING + "1.5", Rating.MESSAGE_CONSTRAINTS);

        // EP: Integer larger than 5
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC
                + " " + PREFIX_RATING + "6", Rating.MESSAGE_CONSTRAINTS); // boundary value

        // EP: empty string
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC
                + " " + PREFIX_RATING + "", Rating.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + VALID_ITEM_INDEX_DESC
                + " " + PREFIX_RATING + "    ", Rating.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index stallIndex = INDEX_FIRST_STALL;
        Index itemIndex = INDEX_FIRST_ITEM;

        // name and price specified
        String userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + stallIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + ITEM_DESC_NASI_LEMAK + VALID_PRICE_DESC;
        EditItemCommand.EditItemDescriptor itemDescriptor = new EditItemDescriptorBuilder()
                .withName(VALID_ITEM_NAME_NASI_LEMAK)
                .withPrice(VALID_ITEM_PRICE_1).build();
        EditItemCommand expectedCommand = new EditItemCommand(stallIndex, itemIndex, itemDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating and description specified
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + stallIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + RATING_DESC + DESCRIPTION_DESC;
        itemDescriptor = new EditItemDescriptorBuilder().withRating(VALID_RATING)
                .withDescription(VALID_DESCRIPTION).build();
        expectedCommand = new EditItemCommand(stallIndex, itemIndex, itemDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STALL;
        String userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL
                + targetIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + ITEM_DESC_NASI_LEMAK + VALID_PRICE_DESC
                + RATING_DESC + DESCRIPTION_DESC;

        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withName(VALID_ITEM_NAME_NASI_LEMAK).withPrice(VALID_ITEM_PRICE_1)
                .withRating(VALID_RATING).withDescription(VALID_DESCRIPTION).build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, INDEX_FIRST_ITEM, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STALL;
        String userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + ITEM_DESC_NASI_LEMAK;
        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withName(VALID_ITEM_NAME_NASI_LEMAK).build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, INDEX_FIRST_ITEM, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + VALID_PRICE_DESC;
        descriptor = new EditItemDescriptorBuilder().withPrice(VALID_ITEM_PRICE_1).build();
        expectedCommand = new EditItemCommand(targetIndex, INDEX_FIRST_ITEM, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + RATING_DESC;
        descriptor = new EditItemDescriptorBuilder().withRating(VALID_RATING).build();
        expectedCommand = new EditItemCommand(targetIndex, INDEX_FIRST_ITEM, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + DESCRIPTION_DESC;
        descriptor = new EditItemDescriptorBuilder().withDescription(VALID_DESCRIPTION).build();
        expectedCommand = new EditItemCommand(targetIndex, INDEX_FIRST_ITEM, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // invalid followed by valid
        Index targetIndex = INDEX_FIRST_STALL;
        String userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL
                + targetIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + INVALID_NAME_DESC + ITEM_DESC_NASI_LEMAK;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // valid followed by invalid
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL
                + targetIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + VALID_PRICE_DESC + INVALID_ITEM_PRICE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRICE));

        // mulltiple valid fields repeated
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + ITEM_DESC_NASI_LEMAK + VALID_PRICE_DESC
                + ITEM_DESC_NASI_LEMAK + VALID_PRICE_DESC + ITEM_DESC_CHICKEN_RICE + VALID_PRICE_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PRICE));

        // multiple invalid values
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL
                + targetIndex.getOneBased()
                + VALID_ITEM_INDEX_DESC
                + INVALID_NAME_DESC + INVALID_ITEM_PRICE_DESC
                + INVALID_NAME_DESC + INVALID_ITEM_PRICE_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PRICE));
    }
}
