package networkbook.logic.parser;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.AddCommand;
import networkbook.logic.commands.EditCommand.EditPersonDescriptor;
import networkbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_LINK,
                        CliSyntax.PREFIX_GRADUATING_YEAR,
                        CliSyntax.PREFIX_COURSE,
                        CliSyntax.PREFIX_SPECIALISATION,
                        CliSyntax.PREFIX_TAG,
                        CliSyntax.PREFIX_PRIORITY
                );

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(
                            Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            AddCommand.MESSAGE_USAGE
                    ),
                    pe
            );
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_GRADUATING_YEAR,
                CliSyntax.PREFIX_PRIORITY
        );

        EditPersonDescriptor editPersonDescriptor = EditCommandParser.generateEditPersonDescriptor(argMultimap);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AddCommand.MESSAGE_NO_INFO);
        }

        return new AddCommand(index, editPersonDescriptor);
    }

}
