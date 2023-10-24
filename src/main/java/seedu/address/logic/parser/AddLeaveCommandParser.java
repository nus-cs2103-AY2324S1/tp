package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANNUAL_LEAVE;

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
        ArgumentMultimap argMultimapForOn =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD_ANNUAL_LEAVE_ON);
        ArgumentMultimap argMultimapForFromAndTo =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD_ANNUAL_LEAVE_FROM, PREFIX_ADD_ANNUAL_LEAVE_TO);
        if (argMultimapForOn.getValue(PREFIX_ADD_ANNUAL_LEAVE_ON).isPresent()) {

        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }
        if (argMultimapForOn.getValue(PREFIX_ADD_ANNUAL_LEAVE_FROM).isPresent() &&
                argMultimapForOn.getValue(PREFIX_ADD_ANNUAL_LEAVE_TO).isPresent()) {

        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new AddLeaveCommand();
        } catch (ParseException pe) {
            if (StringUtil.isInteger(trimmedArgs)) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
            }
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME);
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

            if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getPreamble().isEmpty()) {
                String name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString();
                String[] nameKeywords = name.split("\\s+");
                return new AddLeaveCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE_FOR_NAME));
            }
        }
    }

}
