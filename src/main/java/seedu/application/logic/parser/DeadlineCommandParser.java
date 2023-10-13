package seedu.application.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.logic.commands.DeadlineCommand;
import seedu.application.logic.parser.exceptions.ParseException;

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

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeadlineCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_DEADLINE).orElse("");

        return new DeadlineCommand(index, remark);
    }
}
