package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.time.LocalDateTime;
import java.util.Arrays;

import seedu.address.logic.commands.FilterMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.AttendeeContainsKeywordsPredicate;
import seedu.address.model.meeting.GeneralMeetingPredicate;
import seedu.address.model.meeting.LocationContainsKeywordsPredicate;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.MeetingTimeContainsPredicate;
import seedu.address.model.meeting.TagContainsKeywordsPredicate;
import seedu.address.model.meeting.TitleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterMeetingCommand object
 */
public class FilterMeetingCommandParser implements Parser<FilterMeetingCommand> {
    private String defaultMinimumStartTime = "01.01.0001 0000";
    private String defaultMaximumEndTime = "31.12.9999 2359";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterMeetingCommand
     * and returns a FilterMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_LOCATION, PREFIX_START,
                PREFIX_END, PREFIX_NAME, PREFIX_TAG);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterMeetingCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TITLE, PREFIX_LOCATION, PREFIX_START,
                PREFIX_END, PREFIX_NAME, PREFIX_TAG);

        String[] titleKeyWords = argMultimap.getValue(PREFIX_TITLE).orElse("").split("\\s+");
        String[] locationKeyWords = argMultimap.getValue(PREFIX_LOCATION).orElse("").split("\\s+");
        LocalDateTime start = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START)
                .orElse(defaultMinimumStartTime));
        LocalDateTime end = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END)
                .orElse(defaultMaximumEndTime));
        String[] attendeeKeyWords = argMultimap.getValue(PREFIX_NAME).orElse("").split("\\s+");
        String[] tagKeyWords = argMultimap.getValue(PREFIX_TAG).orElse("").split("\\s+");

        if (!MeetingTime.isValidMeetingTime(start, end)) {
            throw new ParseException(MeetingTime.MESSAGE_CONSTRAINTS);
        }

        GeneralMeetingPredicate generalMeetingPredicate = new GeneralMeetingPredicate(
                new TitleContainsKeywordsPredicate(Arrays.asList(titleKeyWords)),
                new LocationContainsKeywordsPredicate(Arrays.asList(locationKeyWords)),
                new MeetingTimeContainsPredicate(start, end),
                new AttendeeContainsKeywordsPredicate(Arrays.asList(attendeeKeyWords)),
                new TagContainsKeywordsPredicate(Arrays.asList(tagKeyWords)));

        return new FilterMeetingCommand(generalMeetingPredicate);
    }

}
