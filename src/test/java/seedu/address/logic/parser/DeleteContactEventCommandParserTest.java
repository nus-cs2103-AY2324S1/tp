package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INTEGER_OVERFLOW;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_EARLIER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteContactEventCommand;

public class DeleteContactEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactEventCommand.MESSAGE_USAGE);
    private static final String VALID_DATE_TIME = START_DATE_DESC_EARLIER;
    private static final String VALID_DATE_TIME_WITHOUT_PREFIX = VALID_START_DATE_EARLIER;



    private DeleteContactEventCommandParser parser = new DeleteContactEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DATE_TIME, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_DATE_TIME, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_DATE_TIME, MESSAGE_INVALID_FORMAT);

        // int overflow
        assertParseFailure(parser, "2147483648" + VALID_DATE_TIME, MESSAGE_INTEGER_OVERFLOW);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DATE, MESSAGE_INVALID_FORMAT); // invalid date
    }

    @Test
    public void parse_validEvent_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + VALID_DATE_TIME;

        LocalDateTime expectedDateTime = LocalDateTime.parse(
                VALID_DATE_TIME_WITHOUT_PREFIX,
                DATE_TIME_STRING_FORMATTER);
        DeleteContactEventCommand expectedCommand = new DeleteContactEventCommand(targetIndex, expectedDateTime);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
