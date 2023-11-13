package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RateCommand;
import seedu.address.model.interview.Rating;

//@@author jonyxzx
public class RateCommandParserTest {

    private RateCommandParser parser = new RateCommandParser();

    @Test
    public void parse_validArgs_returnsRateCommand() {
        assertParseSuccess(parser, "1 1.0", new RateCommand((INDEX_FIRST), new Rating("1.0")));
    }

    @Test
    public void parse_missingArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingRating_throwsParseException() {
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        assertParseFailure(parser, "3.0", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidRating_throwsParseException() {
        assertParseFailure(parser, "1 a", String.format(Rating.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a 1.0", String.format(ParserUtil.MESSAGE_INVALID_INDEX));
    }
}
