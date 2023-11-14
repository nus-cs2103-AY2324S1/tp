package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.stream.Stream;

import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.Tutorial;

/**
 * Parses input arguments and creates a new CreateCommand object
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateCommand
     * and returns a CreateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public CreateCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL);

        if (!arePrefixesPresent(argMultimap, PREFIX_TUTORIAL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TUTORIAL);

        try {
            Tutorial tutorial = ParserUtil.parseTutorial(argMultimap.getValue(PREFIX_TUTORIAL).get());
            return new CreateCommand(tutorial);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            Tutorial.MESSAGE_CONSTRAINTS + "\n" + CreateCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
