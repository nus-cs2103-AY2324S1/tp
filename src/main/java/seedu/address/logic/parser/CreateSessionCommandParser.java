package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.CreateSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.session.SessionNumber;

/**
 * Parses input arguments and creates a new CreateSessionCommand object.
 */
public class CreateSessionCommandParser implements Parser<CreateSessionCommand> {

    /**
     * Parses the given `args` string and returns a CreateSessionCommand object if the input is valid.
     *
     * @param args The input arguments to be parsed.
     * @return A CreateSessionCommand object.
     * @throws ParseException If the input arguments are not in the correct format or if parsing fails.
     */
    @Override
    public CreateSessionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SESSION, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_SESSION, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateSessionCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SESSION);
        SessionNumber sessionNumber = ParserUtil.parseSessionNumber(argMultimap.getValue(PREFIX_SESSION).get());
        Set<Name> names = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));

        return new CreateSessionCommand(sessionNumber, names);
    }

    /**
     * Checks if the specified prefixes are present in the given ArgumentMultimap.
     *
     * @param argumentMultimap The ArgumentMultimap to check for prefix presence.
     * @param prefixes The prefixes to be checked.
     * @return `true` if all specified prefixes are present, `false` otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
