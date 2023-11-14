package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sortcommands.SortCommand;
import seedu.address.logic.commands.sortcommands.SortCompanyCommand;
import seedu.address.model.company.internship.InternshipInterviewDateTime;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    @Test
    public void parse_validArgs_returnsSortCommand() {
        // Expected: SortCommand without time range
        assertParseSuccess(parser, "c", new SortCompanyCommand(Optional.empty(), Optional.empty()));

        // Expected: SortCommand with start time only
        assertParseSuccess(parser, "c start/30-01-2004 10:20",
            new SortCompanyCommand(Optional.of(new InternshipInterviewDateTime(
                LocalDateTime.parse("30-01-2004 10:20", formatter))), Optional.empty()));

        // Expected: SortCommand with end time only
        assertParseSuccess(parser, "c end/30-01-2004 10:20",
            new SortCompanyCommand(Optional.empty(), Optional.of(new InternshipInterviewDateTime(
                LocalDateTime.parse("30-01-2004 10:20", formatter)))));

        // Expected: SortCommand with both start and end times
        assertParseSuccess(parser, "c start/30-01-2004 10:20 end/31-01-2004 10:20",
            new SortCompanyCommand(Optional.of(
                new InternshipInterviewDateTime(LocalDateTime.parse("30-01-2004 10:20", formatter))),
                Optional.of(new InternshipInterviewDateTime(LocalDateTime.parse("31-01-2004 10:20", formatter)))));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Missing argument
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // Invalid argument
        assertParseFailure(parser, "z", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // Invalid format for time - missing "start/"
        assertParseFailure(parser, "c 30-01-2004 10:20",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // Invalid date format
        assertParseFailure(parser, "c start/3001-2004 10:20",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // Invalid time format
        assertParseFailure(parser, "c start/30-01-2004 1020",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // Invalid time
        assertParseFailure(parser, "c start/30-01-2004 25:20",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // Empty time
        assertParseFailure(parser, "c start/",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // End time before start time
        assertParseFailure(parser, "c start/30-01-2004 10:20 end/29-01-2004 10:20",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // Duplicate prefixes
        assertParseFailure(parser, "c start/30-01-2004 10:20 start/29-01-2004 10:20",
            MESSAGE_DUPLICATE_FIELDS + "start/");
    }
}
