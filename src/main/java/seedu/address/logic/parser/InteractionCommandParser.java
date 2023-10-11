package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OUTCOME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.InteractionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Interaction;
import seedu.address.model.person.Interaction.Outcome;

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
        String trimmedArgs = args.trim();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_OUTCOME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            // CHECKSTYLE.OFF: LineLength
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InteractionCommand.MESSAGE_USAGE), pe);
            // CHECKSTYLE.ON: LineLength
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_OUTCOME);

        Outcome outcome = Outcome.UNKNOWN;
        String note = "";

        if (argMultimap.getValue(PREFIX_OUTCOME).isPresent()) {
            outcome = ParserUtil.parseOutcome(argMultimap.getValue(PREFIX_OUTCOME).get());
            String[] trimmedArgsParts = trimmedArgs.split("\\s+", 3);
            if (trimmedArgsParts.length < 3) {
                note = "";
            }
        } else {
            String[] trimmedArgsParts = trimmedArgs.split("\\s+", 2);
            if (trimmedArgsParts.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        InteractionCommand.MESSAGE_USAGE));
            }
            note = trimmedArgsParts[1];
        }

        return new InteractionCommand(index, new Interaction(note, outcome));
    }
}
