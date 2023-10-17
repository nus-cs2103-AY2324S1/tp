package seedu.flashlingo.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD_LANGUAGE;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD_LANGUAGE;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.commands.EditCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;
import seedu.flashlingo.model.tag.Tag;

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
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ORIGINAL_WORD, PREFIX_ORIGINAL_WORD_LANGUAGE,
                    PREFIX_TRANSLATED_WORD, PREFIX_TRANSLATED_WORD_LANGUAGE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ORIGINAL_WORD, PREFIX_ORIGINAL_WORD_LANGUAGE,
                PREFIX_TRANSLATED_WORD, PREFIX_TRANSLATED_WORD_LANGUAGE);

        OriginalWord word = new OriginalWord(argMultimap.getValue(PREFIX_ORIGINAL_WORD).get(),
                argMultimap.getValue(PREFIX_ORIGINAL_WORD_LANGUAGE).get());
        TranslatedWord translation = new TranslatedWord(argMultimap.getValue(PREFIX_TRANSLATED_WORD).get(),
                argMultimap.getValue(PREFIX_TRANSLATED_WORD_LANGUAGE).get());
        return new EditCommand(index, new FlashCard(word, translation, new Date(), 1));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
