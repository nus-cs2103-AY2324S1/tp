package seedu.flashlingo.model.flashcard.words;

/**
 * Encapsulates an input word
 */
abstract public class Word {
    /** The input word **/
    private final String word;
    /** The input word's language **/
    private final String language;

    /**
     * Evaluates whether this word is an original word
     * @return True or False depending on whether this is an original word
     */
    abstract boolean isOriginalWord();

    /**
     * Evaluates whether this word is a translated word
     * @return True or False depending on whether this is a translated word
     */
    abstract boolean isTranslatedWord();

    /**
     * Constructs a new Word
     * @param word The input word
     * @param language The input Word's language
     */
    public Word(String word, String language) {
        this.word = word;
        this.language = language;
    }

    /**
     * Evaluates and returns this word
     * @return The word encapsulated by this
     */
    public String getWord() {
        return word;
    }

    /**
     * Evaluates and returns this word's language
     * @return Language of this word
     */
    public String getLanguage(){
        return language;
    }

    /**
     * Checks whether this word contains a subpart
     * @param subpart The phrase to check for
     * @return True or False depending on whether this word contains subpart
     */
    public boolean hasSubpart(String subpart) {
        return this.word.toLowerCase().contains(subpart.toLowerCase());
    }

    public boolean isSameLanguage(String language) {
        return this.language.equals(language);
    }

    /**
     * Checks whether this word is equal to the passed object
     * @param other Passed object to check equality against
     * @return True or False depending on whether this and other are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Word)) {
            return false;
        }
        Word otherWord = (Word) other;
        return otherWord.word.equals(this.word);
    }

    /**
     * Evaluates and returns String representation of this Word
     * @return String representation of this Word
     */
    @Override
    public String toString() {
        return this.word;
    }
}

