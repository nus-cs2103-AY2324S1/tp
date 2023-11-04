package seedu.flashlingo.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.commons.util.StringUtil;
import seedu.flashlingo.logic.parser.exceptions.ParseException;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code String word} into a {@code OriginalWord}.
     */
    public static OriginalWord parseWord(String word, String language) {
        requireNonNull(word);
        final OriginalWord tarWord = new OriginalWord(word, language);
        return tarWord;
    }

    /**
     * Parses {@code String word} into a {@code Translation}.
     */
    public static TranslatedWord parseTranslation(String word, String language) {
        requireNonNull(word);
        final TranslatedWord tarWord = new TranslatedWord(word, language);
        return tarWord;
    }
}
