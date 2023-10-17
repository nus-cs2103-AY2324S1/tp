package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSETUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNUMBER;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListAttendanceCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListStudentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AbsentFromTutorialNumPredicate;
import seedu.address.model.person.ContainsCourseTutorialPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    private static final Pattern LIST_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
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

        Tag tag = new Tag("PLACEHOLDER");
        Index tn = Index.fromZeroBased(1);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_COURSETUTORIAL, PREFIX_TUTORIALNUMBER);

        if (argMultimap.getValue(PREFIX_COURSETUTORIAL).isPresent()) {
            String tgValue = argMultimap.getValue(PREFIX_COURSETUTORIAL).get();
            tag = ParserUtil.parseTag(tgValue);
        }

        if (argMultimap.getValue(PREFIX_TUTORIALNUMBER).isPresent()) {
            String tnValue = argMultimap.getValue(PREFIX_TUTORIALNUMBER).get();
            tn = ParserUtil.parseIndex(tnValue);
        }

        switch (commandWord) {

        case ListStudentsCommand.COMMAND_WORD:
            return new ListStudentsCommand();

        case ListAttendanceCommand.COMMAND_WORD:
            if (!argMultimap.getValue(PREFIX_TUTORIALNUMBER).isPresent()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ListAttendanceCommand.MESSAGE_USAGE));
            }
            return new ListAttendanceCommand(tag, tn, new ContainsCourseTutorialPredicate(tag),
                    new AbsentFromTutorialNumPredicate(tn, tag));
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListCommand.MESSAGE_USAGE));
        }
    }
}
