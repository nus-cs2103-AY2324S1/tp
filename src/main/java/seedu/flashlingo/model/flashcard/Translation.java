package seedu.flashlingo.model.flashcard;

/**
 * Represents the translated word
 *
 * @author Nathanael M. Tan
 * @version 1.2
 * @since 1.2
 */
public class Translation {
    private String word;
    public Translation(String word) {
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Translation)) {
            return false;
        }
        Translation otherWord = (Translation) other;
        return otherWord.word.equals(this.word);
    }
    @Override
    public String toString() {
        return this.word;
    }
}
