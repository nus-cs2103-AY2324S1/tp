package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_ON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_ANNUAL_LEAVE_TO;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteLeaveCommand object
 */
public class DeleteLeaveCommandParser implements Parser<DeleteLeaveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteLeaveCommand
     * and returns a DeleteLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLeaveCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimapForAll = ArgumentTokenizer.tokenize(args, PREFIX_ADD_ANNUAL_LEAVE_ON,
                PREFIX_ADD_ANNUAL_LEAVE_FROM, PREFIX_ADD_ANNUAL_LEAVE_TO);
        if (argMultimapForAll.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).isPresent()
                && (argMultimapForAll.getValue(PREFIX_ADD_ANNUAL_LEAVE_FROM).isPresent()
                || argMultimapForAll.getValue(PREFIX_ADD_ANNUAL_LEAVE_TO).isPresent())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimapForOn =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD_ANNUAL_LEAVE_ON);
        ArgumentMultimap argMultimapForFromAndTo =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD_ANNUAL_LEAVE_FROM, PREFIX_ADD_ANNUAL_LEAVE_TO);
        if (argMultimapForOn.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).isPresent()) {
            return deleteSingleDayLeaveHandler(argMultimapForOn);
        } else if (argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_FROM).isPresent()
                && argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_TO).isPresent()) {
            return deleteMultipleDaysLeaveHandler(argMultimapForFromAndTo);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Handles the command based on user input if user wish to delete a single day of leave from employee.
     * @param argMultimapForOn The ArgumentMultimap consisting of the user input
     * @return DeleteLeaveCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLeaveCommand deleteSingleDayLeaveHandler(ArgumentMultimap argMultimapForOn) throws ParseException {
        if (argMultimapForOn.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(argMultimapForOn.getPreamble());
            if (argMultimapForOn.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
            }
            LocalDate date = ParserUtil.stringToDate(argMultimapForOn.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).get());
            return new DeleteLeaveCommand(index, date);
        } catch (ParseException pe) {
            if (StringUtil.isInteger(argMultimapForOn.getPreamble())) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException de) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_DATE));
        }
    }

    /**
     * Handles the command based on user input if user wish to delete multiple days of leave from employee.
     * @param argMultimapForFromAndTo The ArgumentMultimap consisting of the user input
     * @return DeleteLeaveCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLeaveCommand deleteMultipleDaysLeaveHandler(ArgumentMultimap argMultimapForFromAndTo)
            throws ParseException {
        if (argMultimapForFromAndTo.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(argMultimapForFromAndTo.getPreamble());
            if (argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_FROM).isEmpty()
                    || argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_TO).isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
            }
            LocalDate startDate =
                    ParserUtil.stringToDate(argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_FROM).get());
            LocalDate endDate =
                    ParserUtil.stringToDate(argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_TO).get());
            return new DeleteLeaveCommand(index, startDate, endDate);

        } catch (ParseException pe) {
            if (StringUtil.isInteger(argMultimapForFromAndTo.getPreamble())) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException de) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_DATE));
        }
    }
}
