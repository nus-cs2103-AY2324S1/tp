package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.ParserUtil.parseMod;

import seedu.address.logic.commands.TeachCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Mod;

/**
 * Parses input arguments and creates a new TeachCommand object.
 */
public class TeachCommandParser implements Parser<TeachCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TeachCommand
     * and returns a TeachCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TeachCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MOD);

        try {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MOD);

            Mod module = null;

            if (argMultimap.getValue(PREFIX_MOD).isPresent()) {
                module = parseMod(argMultimap.getValue(PREFIX_MOD).get());
            }

            return new TeachCommand(module);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TeachCommand.MESSAGE_USAGE), pe);
        }
    }
}
