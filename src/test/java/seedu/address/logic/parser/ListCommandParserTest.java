package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListAttendanceCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListStudentsCommand;
import seedu.address.model.predicate.AbsentFromTutorialPredicate;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "  abc   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_listStudentsInvalidArgs_throwsParseException() {
        assertParseFailure(parser, "students 123",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListStudentsCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_listAttendanceInvalidArgs_throwsParseException() {
        assertParseFailure(parser, "  attendance  tg/G02 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAttendanceCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "  attendance  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAttendanceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListStudentsCommand() {
        // no leading and trailing whitespaces
        ListStudentsCommand expectedListStudentsCommand =
                new ListStudentsCommand();
        assertParseSuccess(parser, " students", expectedListStudentsCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "       students      ", expectedListStudentsCommand);
    }

    @Test
    public void parse_validArgs_returnsListAttendanceCommand() {
        // with leading and trailing whitespaces
        Tag tag = new Tag("G02");
        Index index = Index.fromOneBased(1);
        ListAttendanceCommand expectedListAttendanceCommand = new ListAttendanceCommand(tag, index,
                new ContainsTagPredicate(tag), new AbsentFromTutorialPredicate(index, tag));
        assertParseSuccess(parser, " attendance tg/G02 tn/1 ", expectedListAttendanceCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "    attendance      tg/     G02     tn/    1      ",
                expectedListAttendanceCommand);
    }
}
