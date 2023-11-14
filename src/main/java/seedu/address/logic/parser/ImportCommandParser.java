package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attachment.Attachment;

/**
 * Parses input arguments and creates a new {@code ImportCommand} object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ImportCommand}
     * and returns a {@code ImportCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Attachment attachment = ParserUtil.parseAttachment(args);
        return new ImportCommand(attachment);
    }
}
