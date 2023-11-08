package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.model.meeting.AttendeeContainsKeywordsPredicate;
import seedu.address.model.meeting.GeneralMeetingPredicate;
import seedu.address.model.meeting.LocationContainsKeywordsPredicate;
import seedu.address.model.meeting.MeetingTagContainsKeywordsPredicate;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.MeetingTimeContainsPredicate;
import seedu.address.model.meeting.TitleContainsKeywordsPredicate;

public class FindMeetingCommandParserTest {

    private FindMeetingCommandParser parser = new FindMeetingCommandParser();
    private LocalDateTime start = LocalDateTime.MIN;
    private LocalDateTime end = LocalDateTime.MAX;

    @Test
    public void parse_nonEmptyPreambleArg_throwsParseException() {
        assertParseFailure(parser, " dfvuv m/CS2103T",
                MESSAGE_INVALID_COMMAND_FORMAT + "\n" + FindMeetingCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validArgsTitle_returnsFilterMeetingCommand() {
        FindMeetingCommand expectedFindMeetingCommand = new FindMeetingCommand(
                preparePredicate(new String[] { "CS2103T", "", "", "" }, start, end));
        assertParseSuccess(parser, " m/CS2103T", expectedFindMeetingCommand);
    }

    @Test
    public void parse_validArgsLocation_returnsFilterMeetingCommand() {
        FindMeetingCommand expectedFindMeetingCommand = new FindMeetingCommand(
                preparePredicate(new String[] { "", "Zoom", "", "" }, start, end));
        assertParseSuccess(parser, " a/Zoom", expectedFindMeetingCommand);
    }

    @Test
    public void parse_validArgsTime_returnsFilterMeetingCommand() {
        LocalDateTime start = DateTimeUtil.parse("20.09.2023 1000");
        LocalDateTime end = DateTimeUtil.parse("20.09.2023 1200");
        FindMeetingCommand expectedFindMeetingCommand = new FindMeetingCommand(
                preparePredicate(new String[] { "", "", "", "" }, start, end));
        assertParseSuccess(parser, " s/20.09.2023 1000 e/20.09.2023 1200", expectedFindMeetingCommand);
    }

    @Test
    public void parse_validArgsAttendee_returnsFilterMeetingCommand() {
        FindMeetingCommand expectedFindMeetingCommand = new FindMeetingCommand(
                preparePredicate(new String[] { "", "", "Alice Bob", "" }, start, end));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindMeetingCommand);
    }

    @Test
    public void parse_validArgsTag_returnsFilterMeetingCommand() {
        FindMeetingCommand expectedFindMeetingCommand = new FindMeetingCommand(
                preparePredicate(new String[] { "", "", "", "friend" }, start, end));
        assertParseSuccess(parser, " t/friend", expectedFindMeetingCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterMeetingCommand() {
        LocalDateTime start = DateTimeUtil.parse("20.09.2023 1000");
        LocalDateTime end = DateTimeUtil.parse("20.09.2023 1200");
        FindMeetingCommand expectedFindMeetingCommand = new FindMeetingCommand(
                preparePredicate(new String[] { "CS2103T", "Zoom", "Alice Bob", "friend" }, start, end));
        assertParseSuccess(parser, " m/CS2103T a/Zoom s/20.09.2023 1000 e/20.09.2023 1200 n/Alice Bob t/friend",
                expectedFindMeetingCommand);
    }

    @Test
    public void parse_invalidArgsTime_throwsParseException() {
        // start before end
        assertParseFailure(parser, " e/20.09.2023 1000 s/20.09.2023 1200",
                MeetingTime.MESSAGE_CONSTRAINTS + "\n" + FindMeetingCommand.MESSAGE_USAGE);

        // wrong start format
        assertParseFailure(parser, " s/2 e/20.09.2023 1000",
                MeetingTime.MESSAGE_CONSTRAINTS + "\n" + FindMeetingCommand.MESSAGE_USAGE);

        // wrong end format
        assertParseFailure(parser, " s/20.09.2023 1200 e/2",
                MeetingTime.MESSAGE_CONSTRAINTS + "\n" + FindMeetingCommand.MESSAGE_USAGE);

        // no end
        assertParseFailure(parser, " s/20.09.2023 1200",
                "Please input both start and end times" + "\n" + FindMeetingCommand.MESSAGE_USAGE);

        // no start
        assertParseFailure(parser, " e/20.09.2023 1000",
                "Please input both start and end times" + "\n" + FindMeetingCommand.MESSAGE_USAGE);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private GeneralMeetingPredicate preparePredicate(String[] userInput, LocalDateTime start, LocalDateTime end) {
        return new GeneralMeetingPredicate(new TitleContainsKeywordsPredicate(List.of(userInput[0].split("\\s+"))),
                new LocationContainsKeywordsPredicate(List.of(userInput[1].split("\\s+"))),
                new MeetingTimeContainsPredicate(start, end),
                new AttendeeContainsKeywordsPredicate(List.of(userInput[2].split("\\s+"))),
                new MeetingTagContainsKeywordsPredicate(List.of(userInput[3].split("\\s+"))));
    }
}
