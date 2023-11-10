package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteGroupTimeCommand;
import seedu.address.logic.commands.DeletePersonTimeCommand;
import seedu.address.logic.commands.DeleteTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new DeleteTimeCommandParser object
 */
public class DeleteTimeCommandParser implements Parser<DeleteTimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTimeCommand
     * and returns a DeleteTimeCommand object for execution. *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTimeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTimeCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GROUPTAG, PREFIX_FREETIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_FREETIME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTimeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_GROUPTAG);
        ArrayList<TimeInterval> timeInterval = ParserUtil.parseInterval(argMultimap.getAllValues(PREFIX_FREETIME));
        if ((arePrefixesPresent(argMultimap, PREFIX_NAME) && arePrefixesPresent(argMultimap, PREFIX_GROUPTAG))) {
            throw new ParseException(
                String.format(DeleteCommand.MESSAGE_TWO_PARAMETERS, DeleteTimeCommand.MESSAGE_USAGE));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            return new DeletePersonTimeCommand(name, timeInterval);
        }
        if (arePrefixesPresent(argMultimap, PREFIX_GROUPTAG)) {
            Group group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUPTAG).get());
            return new DeleteGroupTimeCommand(group, timeInterval);
        }

        // Should not reach here
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTimeCommand.MESSAGE_USAGE));

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
