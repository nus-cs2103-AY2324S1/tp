package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttachCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attachment.Attachment;

/**
 * Parses input arguments and creates a new {@code AttachCommand} object
 */
public class AttachCommandParser implements Parser<AttachCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code AttachCommand} and returns a {@code AttachCommand} object for
     * execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttachCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttachCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_FILE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttachCommand.MESSAGE_USAGE));
        }
        List<Attachment> attachments = parseAttachments(argMultimap.getAllValues(PREFIX_FILE));

        return new AttachCommand(index, attachments);
    }

    /**
     * Parses {@code Collection<String> pathStrings} into a {@code List<Path>} if
     * {@code pathStrings} is non-empty.
     * If {@code pathStrings} contain only one element which is an empty string, it
     * will be parsed into a
     * {@code List<Path>} containing zero tags.
     */
    private List<Attachment> parseAttachments(Collection<String> pathStrings) throws ParseException {
        assert pathStrings != null;

        if (pathStrings.isEmpty()) {
            return List.of();
        }

        Collection<String> pathSet;
        if (pathStrings.size() == 1 && pathStrings.contains("")) {
            pathSet = Collections.emptySet();
        } else {
            pathSet = pathStrings;
        }

        return ParserUtil.parseAttachments(pathSet);
    }
}
