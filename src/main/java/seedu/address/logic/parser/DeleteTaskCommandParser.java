package seedu.address.logic.parser;

import static seedu.address.logic.AnimalMessages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.AnimalParserUtil.ParsedTwoIndices;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object
 */
public class DeleteTaskCommandParser implements AnimalParser<DeleteTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaskCommand.
     * and returns an DeleteTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        try {
            ParsedTwoIndices parsedTwoIndices = AnimalParserUtil.parseTwoIndices(args);
            Index targetAnimalIndex = parsedTwoIndices.getFirstIndex();
            Index targetTaskIndex = parsedTwoIndices.getSecondIndex();

            return new DeleteTaskCommand(targetAnimalIndex, targetTaskIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE),
                    pe);
        }
    }
}
