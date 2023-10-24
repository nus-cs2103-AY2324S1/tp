package seedu.ccacommander.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.logic.commands.AddMemberCommand;
import seedu.ccacommander.model.attendance.Hours;
import seedu.ccacommander.model.attendance.Remark;

import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ccacommander.logic.commands.CommandTestUtil.*;
import static seedu.ccacommander.logic.parser.CliSyntax.*;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ccacommander.testutil.TypicalAttendances.ALICE_AURORA;

public class AddMemberCommandParserTest {
    final Index VALID_INDEX_ONE = Index.fromOneBased(1);
    final Index VALID_INDEX_TWO = Index.fromOneBased(2);
    final Hours VALID_HOURS_A = ALICE_AURORA.getHours();
    final Remark VALID_REMARK_A = ALICE_AURORA.getRemark();
    private AddMemberCommandParser parser = new AddMemberCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AddMemberCommand expectedAddMemberEventCommand =
                new AddMemberCommand(VALID_INDEX_ONE, VALID_INDEX_TWO, VALID_HOURS_A, VALID_REMARK_A);

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + MEMBER_INDEX_DESC_ONE
                        + EVENT_INDEX_DESC_TWO + HOURS_DESC_AURORA + REMARK_DESC_AURORA,
                expectedAddMemberEventCommand);
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedAttendanceString = MEMBER_INDEX_DESC_ONE + EVENT_INDEX_DESC_TWO + HOURS_DESC_AURORA
                + REMARK_DESC_AURORA;

        // multiple member indexes
        assertParseFailure(parser, MEMBER_INDEX_DESC_ONE + validExpectedAttendanceString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MEMBER));

        // multiple event indexes
        assertParseFailure(parser, EVENT_INDEX_DESC_TWO + validExpectedAttendanceString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENT));

        // multiple hours
        assertParseFailure(parser, HOURS_DESC_AURORA + validExpectedAttendanceString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_HOURS));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedAttendanceString + MEMBER_INDEX_DESC_ONE + EVENT_INDEX_DESC_TWO
                        + HOURS_DESC_AURORA + REMARK_DESC_AURORA + validExpectedAttendanceString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MEMBER, PREFIX_EVENT, PREFIX_HOURS, PREFIX_REMARK));

        // invalid value followed by valid value

        // invalid member index
        assertParseFailure(parser, INVALID_MEMBER_INDEX_DESC + validExpectedAttendanceString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MEMBER));

        // invalid event index
        assertParseFailure(parser, INVALID_EVENT_INDEX_DESC + validExpectedAttendanceString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENT));

        // invalid hours
        assertParseFailure(parser, INVALID_HOURS_DESC + validExpectedAttendanceString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_HOURS));

        // invalid remark
        assertParseFailure(parser, INVALID_REMARK_DESC + validExpectedAttendanceString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_REMARK));

        // valid value followed by invalid value

        // invalid member index
        assertParseFailure(parser, validExpectedAttendanceString + INVALID_MEMBER_INDEX_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MEMBER));

        // invalid event index
        assertParseFailure(parser, validExpectedAttendanceString + INVALID_EVENT_INDEX_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENT));

        // invalid hours
        assertParseFailure(parser, validExpectedAttendanceString + INVALID_HOURS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_HOURS));

        // invalid remark
        assertParseFailure(parser, validExpectedAttendanceString + INVALID_REMARK_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_REMARK));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberCommand.MESSAGE_USAGE);

        // missing name index prefix
        assertParseFailure(parser, EVENT_INDEX_DESC_TWO + HOURS_DESC_AURORA + REMARK_DESC_AURORA,
                expectedMessage);

        // missing event index prefix
        assertParseFailure(parser, MEMBER_INDEX_DESC_ONE + HOURS_DESC_AURORA + REMARK_DESC_AURORA,
                expectedMessage);

        // missing hours prefix
        assertParseFailure(parser, MEMBER_INDEX_DESC_ONE + EVENT_INDEX_DESC_TWO + REMARK_DESC_AURORA,
                expectedMessage);

        // missing remark prefix
        assertParseFailure(parser, MEMBER_INDEX_DESC_ONE + EVENT_INDEX_DESC_TWO + HOURS_DESC_AURORA,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_MEMBER_INDEX_ONE + VALID_EVENT_INDEX_TWO + VALID_HOURS_AURORA
                        + VALID_REMARK_AURORA,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid member index
        assertParseFailure(parser, INVALID_MEMBER_INDEX_DESC + EVENT_INDEX_DESC_TWO + HOURS_DESC_AURORA
                + REMARK_DESC_AURORA, ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid event index
        assertParseFailure(parser, MEMBER_INDEX_DESC_ONE + INVALID_EVENT_INDEX_DESC + HOURS_DESC_AURORA
                + REMARK_DESC_AURORA, ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid hours
        assertParseFailure(parser, MEMBER_INDEX_DESC_ONE + EVENT_INDEX_DESC_TWO + INVALID_HOURS_DESC
                        + REMARK_DESC_AURORA, Hours.MESSAGE_CONSTRAINTS);

        // invalid remark
        assertParseFailure(parser, MEMBER_INDEX_DESC_ONE + EVENT_INDEX_DESC_TWO + HOURS_DESC_AURORA
                        + INVALID_REMARK_DESC, Remark.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,INVALID_MEMBER_INDEX_DESC + EVENT_INDEX_DESC_TWO + HOURS_DESC_AURORA
                        + INVALID_REMARK_DESC, ParserUtil.MESSAGE_INVALID_INDEX);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MEMBER_INDEX_DESC_ONE
                        + EVENT_INDEX_DESC_TWO + HOURS_DESC_AURORA + REMARK_DESC_AURORA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberCommand.MESSAGE_USAGE));
    }
}
