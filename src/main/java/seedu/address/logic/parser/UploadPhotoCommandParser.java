package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AVATAR;

import java.util.Optional;

import seedu.address.logic.commands.UploadPhotoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UploadPhotoCommandParser implements Parser<UploadPhotoCommand> {

    public UploadPhotoCommand parse(String args) throws ParseException {
        ArgumentMultimap commandElements = ArgumentTokenizer.tokenize(args, PREFIX_AVATAR);
        String index = commandElements.getPreamble();
        Optional<String> photoPath = commandElements.getValue(PREFIX_AVATAR);

        return new UploadPhotoCommand(Integer.parseInt(index), photoPath.get());
    }
}
