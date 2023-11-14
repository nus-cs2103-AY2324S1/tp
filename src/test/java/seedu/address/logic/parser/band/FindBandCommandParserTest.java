package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.band.FindBandCommand;
import seedu.address.model.band.Band;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;

public class FindBandCommandParserTest {
    private FindBandCommandParser parser = new FindBandCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBandCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " non-empty preamble",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBandCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsWithNameKeywords_returnsFindCommand() {
        // no leading and trailing whitespaces
        Predicate<Band> predicate =
                new BandNameContainsKeywordsPredicate("Ace");
        FindBandCommand expectedFindBandCommand =
                new FindBandCommand(predicate);
        assertParseSuccess(parser, "Ace", expectedFindBandCommand);

    }
}
