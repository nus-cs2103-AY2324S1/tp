package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import seedu.address.Main;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.GeneralMeetingPredicate;
import seedu.address.model.meeting.MeetingTime;

/**
 * Parses input arguments and creates a new FindMeetingCommand object
 */
public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {
    private static Logger logger = LogsCenter.getLogger(Main.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindMeetingCommand
     * and returns a FindMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMeetingCommand parse(String args) throws ParseException {
        try {
            logger.info("Begin FindMeetingCommand parse");
            requireNonNull(args);

            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_LOCATION, PREFIX_START,
                    PREFIX_END, PREFIX_NAME, PREFIX_TAG);
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TITLE, PREFIX_LOCATION, PREFIX_START,
                    PREFIX_END, PREFIX_NAME, PREFIX_TAG);

            logger.info("Begin creation of Meeting predicates");
            String[] titleKeyWords = argMultimap.getValue(PREFIX_TITLE).orElse("").split("\\s+");
            String[] locationKeyWords = argMultimap.getValue(PREFIX_LOCATION).orElse("").split("\\s+");

            LocalDateTime start = LocalDateTime.MIN;
            LocalDateTime end = LocalDateTime.MAX;

            if (argMultimap.getValue(PREFIX_START).isPresent() && argMultimap.getValue(PREFIX_END).isEmpty()
                    || argMultimap.getValue(PREFIX_START).isEmpty() && argMultimap.getValue(PREFIX_END).isPresent()) {
                throw new ParseException("Please input both start and end times");
            }

            if (argMultimap.getValue(PREFIX_START).isPresent() && argMultimap.getValue(PREFIX_END).isPresent()) {
                start = ParserUtil.parseMeetingTime(argMultimap.getValue(PREFIX_START).get());
                end = ParserUtil.parseMeetingTime(argMultimap.getValue(PREFIX_END).get());
                if (!MeetingTime.isValidMeetingTime(start, end) && start != null && end != null) {
                    throw new ParseException(MeetingTime.MESSAGE_CONSTRAINTS);
                }
            }

            String[] attendeeKeyWords = argMultimap.getValue(PREFIX_NAME).orElse("").split("\\s+");
            String[] tagKeyWords = argMultimap.getValue(PREFIX_TAG).orElse("").split("\\s+");

            GeneralMeetingPredicate generalMeetingPredicate = new GeneralMeetingPredicate(
                    titleKeyWords,
                    locationKeyWords,
                    start, end,
                    attendeeKeyWords,
                    tagKeyWords);

            logger.info("All Meeting predicates created");

            return new FindMeetingCommand(generalMeetingPredicate);
        } catch (ParseException pe) {
            throw new ParseException(FindMeetingCommand.MESSAGE_USAGE, pe);
        }
    }
}
