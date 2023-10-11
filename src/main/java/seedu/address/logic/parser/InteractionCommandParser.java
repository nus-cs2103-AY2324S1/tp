package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OUTCOME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.InteractionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InteractionCommand object.
 */
public class InteractionCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the InteractionCommand
     * and returns an InteractionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_OUTCOME);
        
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InteractionCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_OUTCOME);

        if (argMultimap.getValue(PREFIX_OUTCOME).isPresent()) {
            outcome = ParserUtil.parseOutcome(argMultimap.getValue(PREFIX_OUTCOME).get());
        }

        return new InteractionCommand(index, );
    }
}
