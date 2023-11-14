package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandPropertyTestUtil.ADDRESS_DESC_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.ADDRESS_DESC_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandPropertyTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandPropertyTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandPropertyTestUtil.INVALID_PRICE_DESC;
import static seedu.address.logic.commands.CommandPropertyTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandPropertyTestUtil.NAME_DESC_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PHONE_DESC_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PHONE_DESC_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PRICE_DESC_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PRICE_DESC_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.TAG_DESC_BIG;
import static seedu.address.logic.commands.CommandPropertyTestUtil.TAG_DESC_SQUARE;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_ADDRESS_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_NAME_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PHONE_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PHONE_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PRICE_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_TAG_BIG;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_TAG_SQUARE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PROPERTY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.property.Price;
import seedu.address.model.property.PropAddress;
import seedu.address.model.property.PropName;
import seedu.address.model.property.PropPhone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPropertyDescriptorBuilder;

public class EditPropertyCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPropertyCommand.MESSAGE_USAGE);

    private EditPropertyCommandParser parser = new EditPropertyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AQUAVIEW, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPropertyCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AQUAVIEW, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AQUAVIEW, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, PropName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, PropPhone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, PropAddress.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_PRICE_DESC, Price.MESSAGE_CONSTRAINTS); // invalid price
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid address
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + ADDRESS_DESC_AQUAVIEW, PropPhone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Property} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_SQUARE + TAG_DESC_BIG + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_SQUARE + TAG_EMPTY + TAG_DESC_BIG, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_SQUARE + TAG_DESC_BIG, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_ADDRESS_DESC
                        + VALID_PRICE_AQUAVIEW + VALID_PHONE_AQUAVIEW, PropName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PROPERTY;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_SKYVIEW + TAG_DESC_BIG
                + ADDRESS_DESC_AQUAVIEW + PRICE_DESC_AQUAVIEW + NAME_DESC_AQUAVIEW + TAG_DESC_SQUARE;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_AQUAVIEW)
                .withPhone(VALID_PHONE_SKYVIEW).withAddress(VALID_ADDRESS_AQUAVIEW).withPrice(VALID_PRICE_AQUAVIEW)
                .withTags(VALID_TAG_BIG, VALID_TAG_SQUARE).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PROPERTY;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_SKYVIEW + ADDRESS_DESC_AQUAVIEW;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withPhone(VALID_PHONE_SKYVIEW)
                .withAddress(VALID_ADDRESS_AQUAVIEW).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PROPERTY;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AQUAVIEW;
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_AQUAVIEW).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AQUAVIEW;
        descriptor = new EditPropertyDescriptorBuilder().withPhone(VALID_PHONE_AQUAVIEW).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // ADDRESS
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AQUAVIEW;
        descriptor = new EditPropertyDescriptorBuilder().withAddress(VALID_ADDRESS_AQUAVIEW).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // PRICE
        userInput = targetIndex.getOneBased() + PRICE_DESC_AQUAVIEW;
        descriptor = new EditPropertyDescriptorBuilder().withPrice(VALID_PRICE_AQUAVIEW).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_SQUARE;
        descriptor = new EditPropertyDescriptorBuilder().withTags(VALID_TAG_SQUARE).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddPropertyCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PROPERTY;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_SKYVIEW;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + PHONE_DESC_SKYVIEW + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + PHONE_DESC_AQUAVIEW + PRICE_DESC_AQUAVIEW + ADDRESS_DESC_AQUAVIEW
                + TAG_DESC_SQUARE + PHONE_DESC_AQUAVIEW + PRICE_DESC_AQUAVIEW + ADDRESS_DESC_AQUAVIEW + TAG_DESC_SQUARE
                + PHONE_DESC_SKYVIEW + PRICE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW + TAG_DESC_BIG;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_PRICE));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + INVALID_PRICE_DESC + INVALID_ADDRESS_DESC
                + INVALID_PHONE_DESC + INVALID_PRICE_DESC + INVALID_ADDRESS_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_PRICE));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PROPERTY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withTags().build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
