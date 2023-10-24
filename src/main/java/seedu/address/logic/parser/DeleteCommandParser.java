package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Returns a {@link DeleteCommand} from parsing the specified arguments.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+");
            List<Index> indices = new ArrayList<>();

            // Parse all the index arguments and add to the list
            for (String arg : splitArgs) {
                indices.add(ParserUtil.parseIndex(arg));
            }

            return new DeleteCommand(indices);

        } catch (ParseException e) {
            throw new ParseException(
                    Messages.commandInvalidFormat(DeleteCommand.MESSAGE_USAGE), e);
        }
    }
}
