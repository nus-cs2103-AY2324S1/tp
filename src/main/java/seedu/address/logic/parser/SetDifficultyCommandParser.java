package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIFFICULTY;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetDifficultyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetDifficultyCommand object
 */
public class SetDifficultyCommandParser implements Parser<SetDifficultyCommand> {

    private static final Logger logger = Logger.getLogger(SetDifficultyCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments in the context of the SetDifficultyCommand
     * and returns a SetDifficultyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetDifficultyCommand parse(String args) throws ParseException {
        assert args != null : "Command is empty";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DIFFICULTY);

        Index index;

        // Compulsory field: Difficulty
        if (!arePrefixesPresent(argMultimap, PREFIX_DIFFICULTY)) {
            logger.log(Level.WARNING, "No difficulty set in the input");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDifficultyCommand.MESSAGE_USAGE));
        }

        String difficulty = argMultimap.getValue(PREFIX_DIFFICULTY).get();

        if (isEmptyIndex(args)) {
            logger.log(Level.INFO, "No index in found in input, use default index 0");
            return new SetDifficultyCommand(Index.fromZeroBased(0), difficulty);
        }

        try {
            index = ParserUtil.parseIndexWithR(argMultimap.getPreamble());
            assert index != null : "Index is not present";
            return new SetDifficultyCommand(index, difficulty);
        } catch (ParseException pe) {
            logger.log(Level.INFO, "Invalid index in found in input", pe);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetDifficultyCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isEmptyIndex(String input) {
        // Trim leading and trailing spaces
        input = input.trim();

        // Check if "d/" is at the start of the string or if "set" is followed by "d/" with only spaces in between
        return input.startsWith("d/");
    }

}
