package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_TYPE_DESC_ABSENT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_TYPE_DESC_LATE;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_TYPE_DESC_PRESENT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.attendance.AttendanceType;

import java.util.Arrays;

public class MarkCommandParserTest {
    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_namePresent_validType_success() {
        // valid name, mark as absent
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ATTENDANCE_TYPE_DESC_ABSENT,
                new MarkCommand(
                        new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_BOB.split("\\s+"))),
                        AttendanceType.ABSENT));

        // valid name, mark as late
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ATTENDANCE_TYPE_DESC_LATE,
                new MarkCommand(
                        new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_BOB.split("\\s+"))),
                        AttendanceType.LATE));

        // valid name, mark as present
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ATTENDANCE_TYPE_DESC_PRESENT,
                new MarkCommand(
                        new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_BOB.split("\\s+"))),
                        AttendanceType.PRESENT));
    }

    @Test
    public void parse_indexPresent_success() {
        // valid index, mark as absent
        assertParseSuccess(parser, "1" + ATTENDANCE_TYPE_DESC_ABSENT,
                new MarkCommand(
                        INDEX_FIRST_PERSON,
                        AttendanceType.ABSENT));

        // valid index, mark as late
        assertParseSuccess(parser, "1"  + ATTENDANCE_TYPE_DESC_LATE,
                new MarkCommand(
                        INDEX_FIRST_PERSON,
                        AttendanceType.LATE));

        // valid index, mark as present
        assertParseSuccess(parser, "1" + ATTENDANCE_TYPE_DESC_PRESENT,
                new MarkCommand(
                        INDEX_FIRST_PERSON,
                        AttendanceType.PRESENT));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString + ATTENDANCE_TYPE_DESC_ABSENT,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple indexes
        assertParseFailure(parser, " 1 2 " + NAME_DESC_BOB + validExpectedPersonString + ATTENDANCE_TYPE_DESC_ABSENT,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString + ATTENDANCE_TYPE_DESC_ABSENT,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));


        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC + ATTENDANCE_TYPE_DESC_ABSENT,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

    }

    @Test
    public void parse_attendanceFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);

        // missing attendance prefix
        assertParseFailure(parser, VALID_NAME_BOB,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, ATTENDANCE_TYPE_DESC_LATE,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, " ",
                expectedMessage);
    }

    @Test
    public void parse_invalidName_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ATTENDANCE_TYPE_DESC_ABSENT,
                Name.MESSAGE_CONSTRAINTS);
    }
}
