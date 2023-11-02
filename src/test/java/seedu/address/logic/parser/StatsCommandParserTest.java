package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.StatsAvailCommand;
import seedu.address.logic.commands.StatsCommand;
import seedu.address.logic.commands.StatsCurrentCommand;
import seedu.address.logic.commands.StatsHousingCommand;

public class StatsCommandParserTest {
    private StatsCommandParser parser = new StatsCommandParser();

    @Test
    public void parse_validArgs_returnsStatsCommand() {
        assertParseSuccess(parser, StatsAvailCommand.COMMAND_WORD, new StatsAvailCommand());
        assertParseSuccess(parser, StatsCurrentCommand.COMMAND_WORD, new StatsCurrentCommand());
        assertParseSuccess(parser, StatsHousingCommand.COMMAND_WORD, new StatsHousingCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "housinghousing",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraParameters_success() {
        assertParseSuccess(parser, "       " + StatsAvailCommand.COMMAND_WORD , new StatsAvailCommand());
        assertParseSuccess(parser, StatsAvailCommand.COMMAND_WORD + "    ", new StatsAvailCommand());
        assertParseSuccess(parser, StatsAvailCommand.COMMAND_WORD + " d   ", new StatsAvailCommand());
        assertParseSuccess(parser, StatsCurrentCommand.COMMAND_WORD + " current housing", new StatsCurrentCommand());
        assertParseSuccess(parser, StatsHousingCommand.COMMAND_WORD + " housing", new StatsHousingCommand());
    }
}
