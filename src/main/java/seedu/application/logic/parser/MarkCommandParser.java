package seedu.application.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.logic.commands.DeadlineCommand;
import seedu.application.logic.commands.MarkCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.Status;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_STATUS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_JOB_DISPLAYED_INDEX,
                    DeadlineCommand.MESSAGE_USAGE), ive);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STATUS);
        String s = argMultimap.getValue(PREFIX_STATUS).get();
        Status status = ParserUtil.parseStatus(s);
        return new MarkCommand(index, status);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
