package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowCalendarCommand;
import seedu.address.model.schedule.Date;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ShowCalendarCommandParser code. For example, inputs "1" and "1 abc" take the
 * same path through the ShowCalendarCommandParser, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ShowCalendarCommandParserTest {

    private ShowCalendarCommandParser parser = new ShowCalendarCommandParser();

    @Test
    public void parse_validArgs_returnsShowCalendarScheduleCommand() {
        assertParseSuccess(parser, "2023-09-15",
            new ShowCalendarCommand(new Date(LocalDate.of(2023, 9, 15))));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCalendarCommand.MESSAGE_USAGE));
    }
}
