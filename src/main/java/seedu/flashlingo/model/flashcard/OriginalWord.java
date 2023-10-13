package seedu.flashlingo.model.flashcard;

/**
 * Represents the original word
 *
 * @author Nathanael M. Tan
 * @version 1.2
 * @since 1.2
 */
public class OriginalWord {
    private final String word;
    public OriginalWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
