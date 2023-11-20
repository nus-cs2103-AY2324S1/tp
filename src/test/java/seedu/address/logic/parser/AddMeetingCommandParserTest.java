package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.person.MeetingTime;

public class AddMeetingCommandParserTest {
    private final AddMeetingCommandParser parser = new AddMeetingCommandParser();
    private final Index validIndex = INDEX_FOURTH_PERSON;

    @Test
    public void parse_validArgs_returnsAddMeetingCommand() {
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + validIndex.getOneBased() + MEETING_TIME_DESC_AMY,
                new AddMeetingCommand(validIndex, new MeetingTime(VALID_MEETING_TIME_AMY)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid index
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + "a" + MEETING_TIME_DESC_AMY,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid meeting time
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + validIndex.getOneBased() + INVALID_MEETING_TIME_DESC,
                MeetingTime.MESSAGE_CONSTRAINTS);

        // missing index
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + MEETING_TIME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));

        // missing meeting time
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + validIndex.getOneBased(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));

        // no args
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));

        // duplicate meeting times
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + validIndex.getOneBased() + MEETING_TIME_DESC_AMY + MEETING_TIME_DESC_AMY,
                MESSAGE_DUPLICATE_FIELDS + PREFIX_MEETING_TIME);
    }
}
