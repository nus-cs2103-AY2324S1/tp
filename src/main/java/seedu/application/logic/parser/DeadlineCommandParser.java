package seedu.application.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;

import java.util.stream.Stream;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.logic.commands.DeadlineCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.Deadline;

/**
 * Parses input arguments and creates a new {@code DeadlineCommand} object
 */
public class DeadlineCommandParser implements Parser<DeadlineCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeadlineCommand}
     * and returns a {@code DeadlineCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeadlineCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeadlineCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_JOB_DISPLAYED_INDEX,
                DeadlineCommand.MESSAGE_USAGE), ive);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DEADLINE);
        String dateTime = argMultimap.getValue(PREFIX_DEADLINE).get();
        Deadline deadline = ParserUtil.parseDeadline(dateTime);
        return new DeadlineCommand(index, deadline);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

