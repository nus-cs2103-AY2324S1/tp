package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INCORRECT_DATE_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EventCommand;
import seedu.address.model.event.Event;

public class EventCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventCommand.MESSAGE_USAGE);
    private final EventCommandParser parser = new EventCommandParser();
    private final String nonEmptyDescription = "Interview";
    private final String nonEmptyValidStartTime = "2023-11-01 12:00";
    private final String nonEmptyValidEndTime = "2023-11-01 13:00";
    private final String nonEmptyInvalidStartTime = "01-11-2023 12:00";
    private final String nonEmptyInvalidEndTime = "1 January 2023 12PM";
    private final String invalidDate = "2023-02-30 12:00";

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_DESCRIPTION + nonEmptyDescription + " "
                + PREFIX_STARTTIME + nonEmptyValidStartTime + " " + PREFIX_ENDTIME + nonEmptyValidEndTime;
        EventCommand expectedCommand = new EventCommand(new Event(Index.fromOneBased(1),
                "Interview", LocalDateTime.of(2023, 11, 1, 12, 0),
                LocalDateTime.of(2023, 11, 1, 13, 0)));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecified_failure() {
        // Negative numbers
        String userInput = " -5" + " " + PREFIX_DESCRIPTION + nonEmptyDescription + " " + PREFIX_STARTTIME
                + nonEmptyValidStartTime + " " + PREFIX_ENDTIME + nonEmptyValidEndTime;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // Out of bounds check
        String userInput2 = " 100000000000" + " " + PREFIX_DESCRIPTION + nonEmptyDescription + " " + PREFIX_STARTTIME
                + nonEmptyValidStartTime + " " + PREFIX_ENDTIME + nonEmptyValidEndTime;
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_FORMAT);

        // Blank field
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index
        userInput = PREFIX_DESCRIPTION + nonEmptyDescription + " " + PREFIX_STARTTIME + nonEmptyValidStartTime
                + " " + PREFIX_ENDTIME + nonEmptyValidEndTime;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, EventCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, EventCommand.COMMAND_WORD + " " + nonEmptyDescription, expectedMessage);

        // no description
        assertParseFailure(parser, EventCommand.COMMAND_WORD + " 0 " + PREFIX_STARTTIME
                + nonEmptyValidStartTime + " " + PREFIX_ENDTIME + nonEmptyValidEndTime, expectedMessage);

        // no start time
        assertParseFailure(parser, EventCommand.COMMAND_WORD + " 0 " + PREFIX_ENDTIME
                + nonEmptyValidEndTime, expectedMessage);

        // no end time
        assertParseFailure(parser, EventCommand.COMMAND_WORD + " 0 " + PREFIX_STARTTIME
                + nonEmptyValidStartTime, expectedMessage);
    }

    @Test
    public void parse_invalidDateFormat_failure() {
        String expectedMessage = MESSAGE_INCORRECT_DATE_FORMAT;

        // invalid date format for start time
        assertParseFailure(parser, EventCommand.COMMAND_WORD + " 0 " + PREFIX_DESCRIPTION
                + nonEmptyDescription + " " + PREFIX_STARTTIME + nonEmptyInvalidStartTime + " " + PREFIX_ENDTIME
                + nonEmptyValidEndTime, expectedMessage);

        // invalid date format for end time
        assertParseFailure(parser, EventCommand.COMMAND_WORD + " 0 " + PREFIX_DESCRIPTION
                + nonEmptyDescription + " " + PREFIX_STARTTIME + nonEmptyValidStartTime + " " + PREFIX_ENDTIME
                + nonEmptyInvalidEndTime, expectedMessage);
    }

    @Test
    public void parse_invalidStart_failure() {
        String expectedMessage = MESSAGE_INVALID_START;

        // end time = start time
        assertParseFailure(parser, EventCommand.COMMAND_WORD + " 0 " + PREFIX_DESCRIPTION
                + nonEmptyDescription + " " + PREFIX_STARTTIME + nonEmptyValidStartTime + " " + PREFIX_ENDTIME
                + nonEmptyValidStartTime, expectedMessage);

        // end time < start time
        assertParseFailure(parser, EventCommand.COMMAND_WORD + " 0 " + PREFIX_DESCRIPTION
                + nonEmptyDescription + " " + PREFIX_STARTTIME + nonEmptyValidEndTime + " " + PREFIX_ENDTIME
                + nonEmptyValidStartTime, expectedMessage);
    }

    @Test
    public void parse_invalidDate_failure() {
        String expectedMessage = MESSAGE_INVALID_DATE;

        assertParseFailure(parser, EventCommand.COMMAND_WORD + " 0 " + PREFIX_DESCRIPTION
                + nonEmptyDescription + " " + PREFIX_STARTTIME + invalidDate + " " + PREFIX_ENDTIME
                + nonEmptyValidEndTime, expectedMessage);
    }
}
