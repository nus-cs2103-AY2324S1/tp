package seedu.flashlingo.model.flashcard;

import seedu.flashlingo.logic.commands.AddCommand;

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
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OriginalWord)) {
            return false;
        }
        OriginalWord otherWord = (OriginalWord) other;
        return otherWord.word.equals(this.word);
    }
}
