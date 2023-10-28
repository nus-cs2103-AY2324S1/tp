package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UndoCommand object.
 */
public class UndoCommandParser implements Parser<UndoCommand> {

    /**
     * Parses the given {@code args} and generates an UndoCommand object.
     *
     * @param args User input string.
     * @return UndoCommand object.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public UndoCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new UndoCommand(1);
        } else {
            try {
                int steps = Integer.parseInt(trimmedArgs);
                if (steps < 0) {
                    throw new ParseException(String.format(
                            UndoCommand.INVALID_NEGATIVE_STEPS_TO_UNDO, UndoCommand.MESSAGE_USAGE));
                } else {
                    return new UndoCommand(steps);
                }
            } catch (NumberFormatException e) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
            }
        }
    }
}

