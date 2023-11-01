package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.Status;

/**
 * Parses input arguments and creates a new ListScheduleCommand object
 */
public class ListScheduleCommandParser implements Parser<ListScheduleCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ListScheduleCommand
     * and returns a ListScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListScheduleCommand parse(String args) throws ParseException {
        Index tutorIndex = null;
        Status status = null;

        if (args == null || args.isBlank()) {
            return new ListScheduleCommand(null, null);
        } else {
            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

            // Check if there is a tutor index
            if (!argMultimap.getPreamble().isBlank()) {
                try {
                    tutorIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
                } catch (ParseException pe) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ListScheduleCommand.MESSAGE_USAGE));
                }
            }

            // Check if there is a status
            if (arePrefixesPresent(argMultimap, PREFIX_STATUS)) {
                argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STATUS);
                status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
            }
        }

        return new ListScheduleCommand(tutorIndex, status);
    }

}
