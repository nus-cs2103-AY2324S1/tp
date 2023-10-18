package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.FilterMeetingCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.AttendeeContainsKeywordsPredicate;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.LocationContainsKeywordsPredicate;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.MeetingTimeContainsPredicate;
import seedu.address.model.meeting.Title;
import seedu.address.model.meeting.TitleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterMeetingCommandParser implements Parser<FilterMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
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

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String[] titleKeyWords = argMultimap.getValue(PREFIX_TITLE).get().split("\\s+");
        String[] locationKeyWords = argMultimap.getValue(PREFIX_LOCATION).get().split("\\s+");
        LocalDateTime start = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START).get());
        LocalDateTime end = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END).get());
        if (!MeetingTime.isValidMeetingTime(start, end)) {
            throw new ParseException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
        String[] attendeeKeyWords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
        String[] tagKeyWords = argMultimap.getValue(PREFIX_TAG).get().split("\\s+");
        // CREATE PREDICATE BASED ON ALL FIELDS

        return new FilterMeetingCommand(new TitleContainsKeywordsPredicate(Arrays.asList(titleKeyWords)),
                new LocationContainsKeywordsPredicate(Arrays.asList(locationKeyWords)),
                new MeetingTimeContainsPredicate(start, end),
                new AttendeeContainsKeywordsPredicate(Arrays.asList(titleKeyWords)));
    }

}
