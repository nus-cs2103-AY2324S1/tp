package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input and creates a new CreateTagCommand.
 */
public class CreateTagCommandParser implements Parser<CreateTagCommand> {

    /**
     * Parses the given user input and creates a CreateTagCommand.
     *
     * @param args The user input arguments.
     * @return A CreateTagCommand based on the parsed input.
     * @throws ParseException If the input does not conform to the expected format.
     */
    @Override
    public CreateTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagCommand.MESSAGE_USAGE));
        }

        String[] tagParams = ParserUtil.parseTagCategories(argMultimap.getAllValues(PREFIX_TAG));

        return new CreateTagCommand(tagParams);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
