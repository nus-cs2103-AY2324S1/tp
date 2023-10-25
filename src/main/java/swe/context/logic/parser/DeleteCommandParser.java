package swe.context.logic.parser;

import swe.context.commons.core.index.Index;
import swe.context.logic.Messages;
import swe.context.logic.commands.DeleteCommand;
import swe.context.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    /**
     * Returns a {@link DeleteCommand} from parsing the specified arguments.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException e) {
            throw new ParseException(
                Messages.commandInvalidFormat(DeleteCommand.MESSAGE_USAGE),
                e
            );
        }
    }
}
