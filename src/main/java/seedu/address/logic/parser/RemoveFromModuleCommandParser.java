package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveFromModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;

/**
 * Parses input arguments and creates a new RemoveFromModuleCommand object
 */
public class RemoveFromModuleCommandParser implements Parser<RemoveFromModuleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveFromModuleCommand
     * and returns an RemoveFromModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveFromModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MODULE);

        if (!isPrefixPresent(argMultimap, PREFIX_MODULE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveFromModuleCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(ParserUtil.MESSAGE_INVALID_INDEX, pe);
        }

        Module moduleToAddTo = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        return new RemoveFromModuleCommand(index, moduleToAddTo);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
