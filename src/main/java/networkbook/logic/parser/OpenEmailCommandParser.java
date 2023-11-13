package networkbook.logic.parser;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.OpenEmailCommand;
import networkbook.logic.parser.exceptions.ParseException;

/**
 * Represents a parser to parse input into an {@code OpenEmailCommand}.
 */
public class OpenEmailCommandParser implements Parser<OpenEmailCommand> {
    private static final String CMD_STRING_FORMAT = OpenEmailCommand.COMMAND_WORD + " %d "
            + CliSyntax.PREFIX_INDEX + " %d";

    /**
     * Parses the user input into an {@code OpenEmailCommand}.
     * @throws ParseException If the user input is invalid.
     */
    public OpenEmailCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_INDEX);

        Index personIndex;

        personIndex = ParserUtil.parseIndex(argMultimap.getPreamble(),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, OpenEmailCommand.MESSAGE_USAGE));

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_INDEX);

        Index emailIndex = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).orElse("1"),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, OpenEmailCommand.MESSAGE_USAGE));

        return new OpenEmailCommand(personIndex, emailIndex);
    }

    /**
     * Generates open email command string from given parameters.
     * @param personIndex Index of person to insert in command.
     * @param emailIndex Index of email to insert in command.
     * @return Command string.
     */
    public static String generateCommandString(int personIndex, int emailIndex) {
        return String.format(CMD_STRING_FORMAT, personIndex, emailIndex);
    }
}
