package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearEventsCommand;
import seedu.address.model.event.EventPeriod;
import seedu.address.testutil.EventPeriodBuilder;

class ClearEventsCommandParserTest {
    private static final ClearEventsCommandParser parser = new ClearEventsCommandParser();
    private static final String sampleString = "ts/2023-01-02 13:00 te/2023-01-02 15:00";
    private static final String sampleStringConfirmed = "ts/2023-01-02 13:00 te/2023-01-02 15:00 c/CONFIRMED";

    @Test
    public void parse_success_unconfirmed() {
        EventPeriod expectedPeriod = new EventPeriodBuilder().build();
        String validUserInput = " " + sampleString;
        assertParseSuccess(parser, validUserInput, new ClearEventsCommand(expectedPeriod, false));
    }

    @Test
    public void parse_success_confirmed() {
        EventPeriod expectedPeriod = new EventPeriodBuilder().build();
        String validUserInput = " " + sampleStringConfirmed;
        assertParseSuccess(parser, validUserInput, new ClearEventsCommand(expectedPeriod, true));
    }

    @Test
    public void parse_invalidValue_failure() {
        String invalidUserInput = " 123";
        assertParseFailure(parser, invalidUserInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearEventsCommand.MESSAGE_USAGE));
        String halfValidInput = " ts/2023-01-02 13:00";
        assertParseFailure(parser, halfValidInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearEventsCommand.MESSAGE_USAGE));
    }
}
