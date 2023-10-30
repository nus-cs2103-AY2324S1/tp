package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVATAR;

import java.util.Optional;

import seedu.address.logic.commands.UpdatePhotoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdatePhotoCommand object.
 */
public class UpdatePhotoCommandParser implements Parser<UpdatePhotoCommand> {

    /**
     * Creates an UpdatePhotoCommand from input arguments.
     *
     * @param args String command input by user.
     * @return An UpdatePhotoCommand to update photo of a specific contact.
     * @throws ParseException if the user input is not in the expected format.
     */
    public UpdatePhotoCommand parse(String args) throws ParseException {
        ArgumentMultimap commandElements = ArgumentTokenizer.tokenize(args, PREFIX_AVATAR);
        String index = commandElements.getPreamble();
        Optional<String> photoPath = commandElements.getValue(PREFIX_AVATAR);

        if (photoPath.isEmpty() || index.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePhotoCommand.MESSAGE_USAGE));
        }

        return new UpdatePhotoCommand(Integer.parseInt(index), photoPath.get());
    }
}









