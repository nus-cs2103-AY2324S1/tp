package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;

/**
 * Parses input arguments and creates a new AddModuleCommand object
 */
public class AddModuleCommandParser implements Parser<AddModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddModuleCommand
     * and returns an AddModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE);

        // check if "m/" prefix exists and additional erroneous inputs such as add ppm/CS2100
        if (!isPrefixPresent(argMultimap, PREFIX_MODULE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MODULE);

        Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        return new AddModuleCommand(module);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
