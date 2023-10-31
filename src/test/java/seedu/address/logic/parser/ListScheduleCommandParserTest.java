package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTOR_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TUTOR_INDEX_DESC_TWO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ListScheduleCommand;
import seedu.address.model.schedule.Status;

public class ListScheduleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListScheduleCommand.MESSAGE_USAGE);
    private ListScheduleCommandParser parser = new ListScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + STATUS_DESC_ONE;

        assertParseSuccess(parser,
            userInput,
            new ListScheduleCommand(INDEX_FIRST_PERSON, Status.MISSED));
    }

    @Test
    public void parse_missingParts_failure() {
        // no command specified
        assertParseFailure(parser, "m/", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "list-s m/", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + STATUS_DESC_ONE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + STATUS_DESC_ONE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 m/ string", Status.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_repeatedValue_failure() {
        // multiple statuses
        assertParseFailure(parser,
            INDEX_FIRST_PERSON.getOneBased() + STATUS_DESC_ONE + STATUS_DESC_TWO,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tutor index
        assertParseFailure(parser, INVALID_TUTOR_INDEX + STATUS_DESC_ONE,
            MESSAGE_INVALID_FORMAT);

        // invalid status
        assertParseFailure(parser, TUTOR_INDEX_DESC_TWO + INVALID_STATUS,
            Status.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TUTOR_INDEX + INVALID_STATUS,
            MESSAGE_INVALID_FORMAT);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TUTOR_INDEX_DESC_TWO + INVALID_STATUS
            + END_TIME_DESC_ONE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validArgs_returnsListScheduleCommand() {
        ListScheduleCommand expectedListCommand = new ListScheduleCommand(INDEX_FIRST_PERSON, Status.MISSED);
        assertParseSuccess(parser, "1 m/0", expectedListCommand);

    }

    @Test
    public void parse_validArgsIndexOnly_returnsListScheduleCommand() {
        ListScheduleCommand expectedListCommand = new ListScheduleCommand(INDEX_FIRST_PERSON, null);
        assertParseSuccess(parser, "1", expectedListCommand);

    }

    @Test
    public void parse_validArgsStatusOnly_returnsListScheduleCommand() {
        ListScheduleCommand expectedListCommand = new ListScheduleCommand(null, Status.MISSED);
        assertParseSuccess(parser, " m/0", expectedListCommand);

    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nullArgs_returnsListScheduleCommand() {
        ListScheduleCommand expectedListCommand = new ListScheduleCommand(null, null);
        assertParseSuccess(parser, null, expectedListCommand);
    }

    @Test
    public void parse_emptyArgs_returnsListScheduleCommand() {
        ListScheduleCommand expectedListCommand = new ListScheduleCommand(null, null);
        assertParseSuccess(parser, "", expectedListCommand);
        assertParseSuccess(parser, "  ", expectedListCommand);
    }

}
