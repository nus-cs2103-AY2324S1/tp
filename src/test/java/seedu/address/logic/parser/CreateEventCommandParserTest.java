package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AURORA;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_AURORA;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AURORA;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOXING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.AURORA_BOREALIS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CreateEventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.Location;
import seedu.address.model.shared.Name;
import seedu.address.testutil.EventBuilder;

public class CreateEventCommandParserTest {
    private CreateEventCommandParser parser = new CreateEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(AURORA_BOREALIS).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NAME_DESC_AURORA + LOCATION_DESC_AURORA + DATE_DESC_AURORA,
                new CreateEventCommand(expectedEvent));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedEventString = NAME_DESC_AURORA + LOCATION_DESC_AURORA + DATE_DESC_AURORA;

        // multiple names
        assertParseFailure(parser, NAME_DESC_BOXING + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple locations
        assertParseFailure(parser, LOCATION_DESC_BOXING + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // multiple dates
        assertParseFailure(parser, DATE_DESC_BOXING + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedEventString + NAME_DESC_BOXING + LOCATION_DESC_BOXING + DATE_DESC_BOXING
                        + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_LOCATION, PREFIX_DATE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid location
        assertParseFailure(parser, INVALID_LOCATION_DESC + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // invalid date
        assertParseFailure(parser, INVALID_DATE_DESC + validExpectedEventString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedEventString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid location
        assertParseFailure(parser, validExpectedEventString + INVALID_LOCATION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // invalid date
        assertParseFailure(parser, validExpectedEventString + INVALID_DATE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateEventCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOXING + LOCATION_DESC_BOXING + DATE_DESC_BOXING,
                expectedMessage);

        // missing location prefix
        assertParseFailure(parser, NAME_DESC_BOXING + VALID_LOCATION_BOXING + DATE_DESC_BOXING,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_BOXING + LOCATION_DESC_BOXING + VALID_DATE_BOXING,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOXING + VALID_LOCATION_BOXING + VALID_DATE_BOXING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + LOCATION_DESC_BOXING + DATE_DESC_BOXING,
                Name.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, NAME_DESC_BOXING + INVALID_LOCATION_DESC + DATE_DESC_BOXING,
                Location.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOXING + LOCATION_DESC_BOXING + INVALID_DATE_DESC,
                EventDate.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + LOCATION_DESC_BOXING + INVALID_DATE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOXING + LOCATION_DESC_BOXING
                        + DATE_DESC_BOXING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateEventCommand.MESSAGE_USAGE));
    }
}
