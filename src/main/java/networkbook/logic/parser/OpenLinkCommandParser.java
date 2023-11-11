package networkbook.logic.parser;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.OpenLinkCommand;
import networkbook.logic.parser.exceptions.ParseException;

/**
 * Represents a parser to parse input into an {@code OpenLinkCommand}.
 */
public class OpenLinkCommandParser implements Parser<OpenLinkCommand> {
    private static final String CMD_STRING_FORMAT = OpenLinkCommand.COMMAND_WORD + " %d "
            + CliSyntax.PREFIX_INDEX + " %d";

    /**
     * Parses the user input into an {@code OpenLinkCommand}.
     * @throws ParseException If the user input is invalid.
     */
    public OpenLinkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_INDEX);

        Index personIndex;

        personIndex = ParserUtil.parseIndex(argMultimap.getPreamble(),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, OpenLinkCommand.MESSAGE_USAGE));

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_INDEX);

        Index linkIndex = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).orElse("1"),
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, OpenLinkCommand.MESSAGE_USAGE));

        return new OpenLinkCommand(personIndex, linkIndex);
    }

    /**
     * Generates open command string from given parameters.
     * @param personIndex Index of person to insert in command.
     * @param linkIndex Index of link to insert in command.
     * @return Command string.
     */
    public static String generateCommandString(int personIndex, int linkIndex) {
        return String.format(CMD_STRING_FORMAT, personIndex, linkIndex);
    }
}
