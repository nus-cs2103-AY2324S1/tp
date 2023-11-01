package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_ON;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ViewLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewLeaveCommand object
 */
public class ViewLeaveCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewLeaveCommand
     * and returns a ViewLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DateTimeParseException if the user input does not conform the expected DateTime format
     */
    public ViewLeaveCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewLeaveCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ADD_ANNUAL_LEAVE_ON);

        try {
            if (argMultimap.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewLeaveCommand.MESSAGE_USAGE));
            }
            LocalDate dateToView = ParserUtil.stringToDate(argMultimap.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).get());
            return new ViewLeaveCommand(dateToView);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewLeaveCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException de) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_DATE));
        }
    }
}
