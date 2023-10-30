package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.commands.ListScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.Status;

/**
 * Parses input arguments and creates a new DeleteTutorCommand object
 */
public class ListScheduleCommandParser implements Parser<ListScheduleCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTutorCommand
     * and returns a DeleteTutorCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        Index tutorIndex = null;
        Status status = null;

        // Check if there is a tutor index
        if (!argMultimap.getPreamble().isBlank()) {
            try {
                tutorIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddScheduleCommand.MESSAGE_USAGE));
            }
        }

        // Check if there is a status
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            try {
                status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddScheduleCommand.MESSAGE_USAGE));
            }
        }

        return new ListScheduleCommand(tutorIndex, status);
    }

}
