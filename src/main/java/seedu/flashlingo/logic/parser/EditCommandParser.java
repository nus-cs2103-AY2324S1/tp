package seedu.flashlingo.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD_LANGUAGE;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD_LANGUAGE;

import java.util.Optional;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.commands.EditCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        boolean isEdited = false;
        Prefix[] prefixes = new Prefix[] {PREFIX_ORIGINAL_WORD, PREFIX_ORIGINAL_WORD_LANGUAGE,
            PREFIX_TRANSLATED_WORD, PREFIX_TRANSLATED_WORD_LANGUAGE};
        String[] changes = new String[]{"", "", "", ""};
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, prefixes);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ORIGINAL_WORD, PREFIX_ORIGINAL_WORD_LANGUAGE,
                PREFIX_TRANSLATED_WORD, PREFIX_TRANSLATED_WORD_LANGUAGE);

        for (int i = 0; i < prefixes.length; i++) {
            Optional<String> temp = argMultimap.getValue(prefixes[i]);
            if (temp.isPresent()) {
                isEdited = true;
                changes[i] = temp.get().trim();
            }
        }

        if (!isEdited) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        return new EditCommand(index, changes);
    }
}
