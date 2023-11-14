package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.stream.Stream;

import seedu.address.logic.commands.JoinCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;

/**
 * Parses input arguments and creates a new JoinCommand object
 */
public class JoinCommandParser implements Parser<JoinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the JoinCommand
     * and returns a JoinCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public JoinCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_EMAIL, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL, PREFIX_GROUP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, JoinCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EMAIL, PREFIX_GROUP);

        try {
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            int groupNumber = ParserUtil.parseGroupNumber(argMultimap.getValue(PREFIX_GROUP).get());
            return new JoinCommand(email, groupNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, JoinCommand.MESSAGE_USAGE), pe);
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
