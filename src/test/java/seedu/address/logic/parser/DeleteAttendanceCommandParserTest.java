package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAttendanceCommand;
import seedu.address.model.tag.Tag;

public class DeleteAttendanceCommandParserTest {
    private final DeleteAttendanceCommandParser parser = new DeleteAttendanceCommandParser();
    @Test
    public void parse_validArgs_returnsAttendanceCommand() {
        Tag tag = new Tag("S1");

        assertParseSuccess(parser, "1 ln/S1",
                new DeleteAttendanceCommand(INDEX_FIRST_PERSON, tag));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-9 ln/S1",
                String.format(ParserUtil.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "ln/S1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAttendanceCommand.MESSAGE_USAGE));
    }
}
