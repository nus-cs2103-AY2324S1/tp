package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AddGroupFreeTimeCommand object
 */
public class AddGroupFreeTimeCommandParser implements Parser<AddGroupFreeTimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddGroupFreeTimeCommand
     * and returns a AddGroupFreeTimeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddGroupFreeTimeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUPTAG, PREFIX_FREETIME, PREFIX_ENDINTERVAL);

        //find a way to separate error msg when ";" is missing
        if (!arePrefixesPresent(argMultimap, PREFIX_GROUPTAG, PREFIX_FREETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupFreeTimeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_GROUPTAG);
        Group group = ParserUtil.parseSingleGroup(argMultimap.getValue(PREFIX_GROUPTAG).get());
        TimeInterval firstInterval = ParserUtil.parseEachInterval(argMultimap.getValue(PREFIX_FREETIME).get());
        ArrayList<TimeInterval> timeInterval = ParserUtil.parseInterval(argMultimap.getAllValues(PREFIX_ENDINTERVAL));
        timeInterval.add(0, firstInterval);

        if (!TimeInterval.isTimeIntervalOverlap(timeInterval)) {
            throw new ParseException(TimeInterval.MESSAGE_CONSTRAINTS_OVERLAP);
        }

        return new AddGroupFreeTimeCommand(group, timeInterval);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
