package seedu.address.logic.parser;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;
import seedu.address.model.person.Name;

import java.util.ArrayList;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class DeleteTimeCommandParser implements Parser<DeleteTimeCommand>{

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTimeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GROUPTAG, PREFIX_FREETIME, PREFIX_ENDINTERVAL);

        //find a way to separate error msg when ";" is missing
        if ((!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_FREETIME)
                && !arePrefixesPresent(argMultimap, PREFIX_GROUPTAG, PREFIX_FREETIME))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTimeCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_FREETIME)) {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            TimeInterval firstInterval = ParserUtil.parseEachInterval(argMultimap.getValue(PREFIX_FREETIME).get());
            ArrayList<TimeInterval> timeInterval = ParserUtil.parseInterval(argMultimap.getAllValues(PREFIX_ENDINTERVAL));
            timeInterval.add(0, firstInterval);

            return new DeleteTimeCommand(name, timeInterval);
        } else {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_GROUPTAG);
            Group group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUPTAG).get());
            TimeInterval firstInterval = ParserUtil.parseEachInterval(argMultimap.getValue(PREFIX_FREETIME).get());
            ArrayList<TimeInterval> timeInterval = ParserUtil.parseInterval(argMultimap.getAllValues(PREFIX_ENDINTERVAL));
            timeInterval.add(0, firstInterval);

            return new DeleteTimeCommand(group, timeInterval);
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
