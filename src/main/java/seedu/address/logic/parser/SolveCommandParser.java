package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SolveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SolveCommand object
 */
public class SolveCommandParser implements Parser<SolveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SolveCommand
     * and returns a SolveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SolveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DIFFICULTY);

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new SolveCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SolveCommand.MESSAGE_USAGE), pe);
        }
    }
}
