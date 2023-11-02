package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GENRE_DESC_ROCK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENRE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ACE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOOM;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.band.AddBandCommand;
import seedu.address.model.band.BandName;
import seedu.address.model.tag.Genre;

public class AddBandCommandParserTest {
    private AddBandCommandParser parser = new AddBandCommandParser();

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedBandString = NAME_DESC_BOOM + GENRE_DESC_ROCK;

        // multiple names
        assertParseFailure(parser, NAME_DESC_ACE + validExpectedBandString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedBandString + NAME_DESC_ACE
                        + validExpectedBandString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedBandString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));


        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedBandString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBandCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOOM,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + GENRE_DESC_ROCK, BandName.MESSAGE_CONSTRAINTS);


        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOOM + INVALID_GENRE_DESC, Genre.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_GENRE_DESC,
                BandName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOOM,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBandCommand.MESSAGE_USAGE));
    }
}
