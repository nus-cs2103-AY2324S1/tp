package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ListAttendanceCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListStudentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.predicate.AbsentFromTutorialPredicate;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.week.Week;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {
    private static final Pattern LIST_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private final Logger logger = LogsCenter.getLogger(ListCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListAttendanceCommand or ListStudentsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);

        final Matcher matcher = LIST_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_TUTORIAL_GROUP, PREFIX_WEEK);
        Optional<Tag> tag = Optional.empty();

        if (argMultimap.getValue(PREFIX_TUTORIAL_GROUP).isPresent()) {
            tag = Optional.of(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TUTORIAL_GROUP).get()));
        }

        switch (commandWord.trim()) {
        case ListAttendanceCommand.COMMAND_WORD:
            logger.fine("Parsing list attendance");
            if (argMultimap.getValue(PREFIX_WEEK).isEmpty() || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ListAttendanceCommand.MESSAGE_USAGE));
            }
            String weekString = argMultimap.getValue(PREFIX_WEEK).get();
            Week week = ParserUtil.parseWeek(weekString);
            return new ListAttendanceCommand(tag, week, new ContainsTagPredicate(tag),
                    new AbsentFromTutorialPredicate(week, tag));
        case ListStudentsCommand.COMMAND_WORD:
            logger.fine("Parsing list students");
            if (!arguments.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ListStudentsCommand.MESSAGE_USAGE));
            }
            return new ListStudentsCommand();
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}
