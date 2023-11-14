package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteGroupCommand object.
 */
public class DeleteGroupCommandParser implements Parser<DeleteGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGroupCommand
     * and returns a DeleteGroupCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        // Parse the group number from the args using the group prefix
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        if (!argMultimap.getValue(PREFIX_GROUP).isPresent() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));
        }

        // Extract the group number from the argument map
        try {
            int groupNumber = ParserUtil.parseGroupNumber(argMultimap.getValue(PREFIX_GROUP).get());
            return new DeleteGroupCommand(groupNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE), pe);
        }
    }
}
