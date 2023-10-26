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
import seedu.address.logic.commands.AddLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddLeaveCommand object
 */
public class AddLeaveCommandParser implements Parser<AddLeaveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddLeaveCommand
     * and returns an AddLeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLeaveCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimapForAll = ArgumentTokenizer.tokenize(args, PREFIX_ADD_ANNUAL_LEAVE_ON,
                PREFIX_ADD_ANNUAL_LEAVE_FROM, PREFIX_ADD_ANNUAL_LEAVE_TO);
        if (argMultimapForAll.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).isPresent()
                && (argMultimapForAll.getValue(PREFIX_ADD_ANNUAL_LEAVE_FROM).isPresent()
                || argMultimapForAll.getValue(PREFIX_ADD_ANNUAL_LEAVE_TO).isPresent())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimapForOn =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD_ANNUAL_LEAVE_ON);
        ArgumentMultimap argMultimapForFromAndTo =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD_ANNUAL_LEAVE_FROM, PREFIX_ADD_ANNUAL_LEAVE_TO);
        if (argMultimapForOn.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).isPresent()) {
            return singleDayLeaveHandler(argMultimapForOn);
        } else if (argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_FROM).isPresent()
                && argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_TO).isPresent()) {
            return multipleDaysLeaveHandler(argMultimapForFromAndTo);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Handles the command based on user input if user wish to add in a single day of leave for employee.
     * @param argMultimapForOn The ArgumentMultimap consisting of the user input
     * @return AddLeaveCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLeaveCommand singleDayLeaveHandler(ArgumentMultimap argMultimapForOn) throws ParseException {
        if (argMultimapForOn.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(argMultimapForOn.getPreamble());
            LocalDate date = ParserUtil.stringToDate(argMultimapForOn.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).get());
            return new AddLeaveCommand(index, date);
        } catch (ParseException pe) {
            if (StringUtil.isInteger(argMultimapForOn.getPreamble())) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException de) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_DATE));
        }
    }

    /**
     * Handles the command based on user input if user wish to add in multiple days of leave for employee.
     * @param argMultimapForFromAndTo The ArgumentMultimap consisting of the user input
     * @return AddLeaveCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLeaveCommand multipleDaysLeaveHandler(ArgumentMultimap argMultimapForFromAndTo) throws ParseException {
        if (argMultimapForFromAndTo.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(argMultimapForFromAndTo.getPreamble());
            LocalDate startDate =
                    ParserUtil.stringToDate(argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_FROM).get());
            LocalDate endDate =
                    ParserUtil.stringToDate(argMultimapForFromAndTo.getValue(PREFIX_ADD_ANNUAL_LEAVE_TO).get());
            return new AddLeaveCommand(index, startDate, endDate);

        } catch (ParseException pe) {
            if (StringUtil.isInteger(argMultimapForFromAndTo.getPreamble())) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException de) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_DATE));
        }
    }

}
