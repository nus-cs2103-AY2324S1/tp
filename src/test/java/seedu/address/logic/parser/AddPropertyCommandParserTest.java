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
import static seedu.address.logic.commands.CommandPropertyTestUtil.NAME_DESC_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PHONE_DESC_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PHONE_DESC_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PRICE_DESC_AQUAVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.PRICE_DESC_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.TAG_DESC_BIG;
import static seedu.address.logic.commands.CommandPropertyTestUtil.TAG_DESC_SQUARE;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_ADDRESS_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_NAME_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PHONE_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_PRICE_SKYVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_TAG_BIG;
import static seedu.address.logic.commands.CommandPropertyTestUtil.VALID_TAG_SQUARE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProperties.AQUAVIEW;
import static seedu.address.testutil.TypicalProperties.SKYVIEW;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.model.property.Price;
import seedu.address.model.property.PropAddress;
import seedu.address.model.property.PropName;
import seedu.address.model.property.PropPhone;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PropertyBuilder;

public class AddPropertyCommandParserTest {
    private AddPropertyCommandParser parser = new AddPropertyCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Property expectedProperty = new PropertyBuilder(SKYVIEW).withTags(VALID_TAG_SQUARE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_SKYVIEW + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW
                + PRICE_DESC_SKYVIEW + TAG_DESC_SQUARE, new AddPropertyCommand(expectedProperty));


        // multiple tags - all accepted
        Property expectedPropertyMultipleTags = new PropertyBuilder(SKYVIEW).withTags(VALID_TAG_SQUARE, VALID_TAG_BIG)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_SKYVIEW + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW + PRICE_DESC_SKYVIEW
                        + TAG_DESC_BIG + TAG_DESC_SQUARE,
                new AddPropertyCommand(expectedPropertyMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPropertyString = NAME_DESC_SKYVIEW + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW
                + PRICE_DESC_SKYVIEW + TAG_DESC_SQUARE;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AQUAVIEW + validExpectedPropertyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AQUAVIEW + validExpectedPropertyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple ADDRESSs
        assertParseFailure(parser, ADDRESS_DESC_AQUAVIEW + validExpectedPropertyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple PRICEs
        assertParseFailure(parser, PRICE_DESC_AQUAVIEW + validExpectedPropertyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRICE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPropertyString + PHONE_DESC_AQUAVIEW + ADDRESS_DESC_AQUAVIEW + NAME_DESC_AQUAVIEW
                        + PRICE_DESC_AQUAVIEW
                        + validExpectedPropertyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PRICE, PREFIX_ADDRESS, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPropertyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid ADDRESS
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPropertyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPropertyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid PRICE
        assertParseFailure(parser, INVALID_PRICE_DESC + validExpectedPropertyString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRICE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPropertyString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid ADDRESS
        assertParseFailure(parser, validExpectedPropertyString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid phone
        assertParseFailure(parser, validExpectedPropertyString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid PRICE
        assertParseFailure(parser, validExpectedPropertyString + INVALID_PRICE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRICE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Property expectedProperty = new PropertyBuilder(AQUAVIEW).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AQUAVIEW + PHONE_DESC_AQUAVIEW + ADDRESS_DESC_AQUAVIEW
                + PRICE_DESC_AQUAVIEW, new AddPropertyCommand(expectedProperty));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_SKYVIEW + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW
                + PRICE_DESC_SKYVIEW, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_SKYVIEW + VALID_PHONE_SKYVIEW + ADDRESS_DESC_SKYVIEW
                + PRICE_DESC_SKYVIEW, expectedMessage);

        // missing ADDRESS prefix
        assertParseFailure(parser, NAME_DESC_SKYVIEW + PHONE_DESC_SKYVIEW + VALID_ADDRESS_SKYVIEW
                + PRICE_DESC_SKYVIEW, expectedMessage);

        // missing PRICE prefix
        assertParseFailure(parser, NAME_DESC_SKYVIEW + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW
                + VALID_PRICE_SKYVIEW, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_SKYVIEW + VALID_PHONE_SKYVIEW + VALID_ADDRESS_SKYVIEW
                + VALID_PRICE_SKYVIEW, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW + PRICE_DESC_SKYVIEW
                + TAG_DESC_BIG + TAG_DESC_SQUARE, PropName.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_SKYVIEW + INVALID_PHONE_DESC + ADDRESS_DESC_SKYVIEW + PRICE_DESC_SKYVIEW
                + TAG_DESC_BIG + TAG_DESC_SQUARE, PropPhone.MESSAGE_CONSTRAINTS);

        // invalid ADDRESS
        assertParseFailure(parser, NAME_DESC_SKYVIEW + PHONE_DESC_SKYVIEW + INVALID_ADDRESS_DESC + PRICE_DESC_SKYVIEW
                + TAG_DESC_BIG + TAG_DESC_SQUARE, PropAddress.MESSAGE_CONSTRAINTS);

        // invalid PRICE
        assertParseFailure(parser, NAME_DESC_SKYVIEW + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW + INVALID_PRICE_DESC
                + TAG_DESC_BIG + TAG_DESC_SQUARE, Price.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_SKYVIEW + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW + PRICE_DESC_SKYVIEW
                + INVALID_TAG_DESC + VALID_TAG_BIG, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW + INVALID_PRICE_DESC,
                PropName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_SKYVIEW + PHONE_DESC_SKYVIEW + ADDRESS_DESC_SKYVIEW
                + PRICE_DESC_SKYVIEW + TAG_DESC_BIG + TAG_DESC_SQUARE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
    }
}
