package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListAttendanceCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListStudentsCommand;
import seedu.address.model.predicate.AbsentFromTutorialPredicate;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.week.Week;

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
        Optional<Tag> tag = Optional.of(new Tag("G02"));
        Week week = new Week(1);
        ListAttendanceCommand expectedListAttendanceCommand = new ListAttendanceCommand(tag, week,
                new ContainsTagPredicate(tag), new AbsentFromTutorialPredicate(week, tag));
        assertParseSuccess(parser, " attendance tg/G02 w/1 ", expectedListAttendanceCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "    attendance      tg/     G02     w/    1      ",
                expectedListAttendanceCommand);
    }
}
