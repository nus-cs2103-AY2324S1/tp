package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterMeetingCommand;
import seedu.address.model.meeting.AttendeeContainsKeywordsPredicate;
import seedu.address.model.meeting.GeneralMeetingPredicate;
import seedu.address.model.meeting.LocationContainsKeywordsPredicate;
import seedu.address.model.meeting.MeetingTimeContainsPredicate;
import seedu.address.model.meeting.TagContainsKeywordsPredicate;
import seedu.address.model.meeting.TitleContainsKeywordsPredicate;

public class FilterMeetingCommandParserTest {

    private FilterMeetingCommandParser parser = new FilterMeetingCommandParser();
    private LocalDateTime start = LocalDateTime.of(LocalDate.of(0001, 01, 01), LocalTime.of(00, 00));
    private LocalDateTime end = LocalDateTime.of(LocalDate.of(9999, 12, 31), LocalTime.of(23, 59));
    @Test
    public void parse_nonEmptyPreambleArg_throwsParseException() {
        assertParseFailure(parser, " dfvuv m/CS2103T",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsTitle_returnsFilterMeetingCommand() {
        FilterMeetingCommand expectedFilterMeetingCommand =
                new FilterMeetingCommand(preparePredicate(new String[]{"CS2103T", "", "", ""}, start, end));
        assertParseSuccess(parser, " m/CS2103T", expectedFilterMeetingCommand);
    }

    @Test
    public void parse_validArgsLocation_returnsFilterMeetingCommand() {
        FilterMeetingCommand expectedFilterMeetingCommand =
                new FilterMeetingCommand(preparePredicate(new String[]{"", "Zoom", "", ""}, start, end));
        assertParseSuccess(parser, " a/Zoom", expectedFilterMeetingCommand);
    }

    @Test
    public void parse_validArgsTime_returnsFilterMeetingCommand() {
        LocalDateTime start = LocalDateTime.parse("20.09.2023 1000", FORMAT);
        LocalDateTime end = LocalDateTime.parse("20.09.2023 1200", FORMAT);
        FilterMeetingCommand expectedFilterMeetingCommand =
                new FilterMeetingCommand(preparePredicate(new String[]{"", "", "", ""}, start, end));
        assertParseSuccess(parser, " s/20.09.2023 1000 e/20.09.2023 1200", expectedFilterMeetingCommand);
    }

    @Test
    public void parse_validArgsAttendee_returnsFilterMeetingCommand() {
        FilterMeetingCommand expectedFilterMeetingCommand =
                new FilterMeetingCommand(preparePredicate(new String[]{"", "", "Alice Bob", ""}, start, end));
        assertParseSuccess(parser, " n/Alice Bob", expectedFilterMeetingCommand);
    }

    @Test
    public void parse_validArgsTag_returnsFilterMeetingCommand() {
        FilterMeetingCommand expectedFilterMeetingCommand =
                new FilterMeetingCommand(preparePredicate(new String[]{"", "", "", "friend"}, start, end));
        assertParseSuccess(parser, " t/friend", expectedFilterMeetingCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterMeetingCommand() {
        LocalDateTime start = LocalDateTime.parse("20.09.2023 1000", FORMAT);
        LocalDateTime end = LocalDateTime.parse("20.09.2023 1200", FORMAT);
        FilterMeetingCommand expectedFilterMeetingCommand =
                new FilterMeetingCommand(preparePredicate(new String[]{"CS2103T", "Zoom", "Alice Bob", "friend"},
                        start, end));
        assertParseSuccess(parser, " m/CS2103T a/Zoom s/20.09.2023 1000 e/20.09.2023 1200 n/Alice Bob t/friend",
                expectedFilterMeetingCommand);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private GeneralMeetingPredicate preparePredicate(String[] userInput, LocalDateTime start, LocalDateTime end) {
        return new GeneralMeetingPredicate(new TitleContainsKeywordsPredicate(List.of(userInput[0].split("\\s+"))),
                new LocationContainsKeywordsPredicate(List.of(userInput[1].split("\\s+"))),
                new MeetingTimeContainsPredicate(start, end),
                new AttendeeContainsKeywordsPredicate(List.of(userInput[2].split("\\s+"))),
                new TagContainsKeywordsPredicate(List.of(userInput[3].split("\\s+"))));
    }
}
