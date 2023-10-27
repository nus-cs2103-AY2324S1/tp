package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
//import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.AttendanceType;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ABSENT, PREFIX_PRESENT, PREFIX_LATE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PRESENT, PREFIX_PRESENT, PREFIX_LATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(args);
            if (argMultimap.getValue(PREFIX_ABSENT).isPresent() &&
                    !(argMultimap.getValue(PREFIX_PRESENT).isPresent() || argMultimap.getValue(PREFIX_LATE).isPresent())) {

                return new MarkCommand(index, AttendanceType.ABSENT);

            } else if (argMultimap.getValue(PREFIX_LATE).isPresent() &&
                    !(argMultimap.getValue(PREFIX_PRESENT).isPresent() || argMultimap.getValue(PREFIX_PRESENT).isPresent())) {

                return new MarkCommand(index, AttendanceType.LATE);

            } else if (argMultimap.getValue(PREFIX_PRESENT).isPresent() &&
                    !(argMultimap.getValue(PREFIX_LATE).isPresent() || argMultimap.getValue(PREFIX_ABSENT).isPresent())) {

                return new MarkCommand(index, AttendanceType.PRESENT);
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE)
                );
            }

        } catch (ParseException pe) {

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                String name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString();
                String[] nameKeywords = name.split("\\s+");

                if (argMultimap.getValue(PREFIX_ABSENT).isPresent() &&
                        !(argMultimap.getValue(PREFIX_PRESENT).isPresent() || argMultimap.getValue(PREFIX_LATE).isPresent())) {
                    return new MarkCommand(
                            new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), AttendanceType.ABSENT
                    );
                } else if (argMultimap.getValue(PREFIX_LATE).isPresent() &&
                        !(argMultimap.getValue(PREFIX_PRESENT).isPresent() || argMultimap.getValue(PREFIX_PRESENT).isPresent())) {
                    return new MarkCommand(
                            new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), AttendanceType.LATE
                    );
                } else if (argMultimap.getValue(PREFIX_PRESENT).isPresent() &&
                        !(argMultimap.getValue(PREFIX_LATE).isPresent() || argMultimap.getValue(PREFIX_ABSENT).isPresent())) {
                    return new MarkCommand(
                            new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), AttendanceType.PRESENT
                    );
                } else {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE_FOR_NAME));
                }


            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE_FOR_NAME));
            }
        }

    }
}
