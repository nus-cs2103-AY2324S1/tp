package seedu.address.pojo;

import java.util.Date;

public class Flashcard {
    private String originalWord;
    private String translatedWord;
    private Date whenToReview; // Date the flashcard was needs to be reviewed
    private int level; // How many times successfully remembered

    public Flashcard(String originalWord, String translatedWord, Date whenToReview, int level) {
        this.originalWord = originalWord;
        this.translatedWord = translatedWord;
        this.whenToReview = whenToReview;
        this.level = level;
    }


    /**
     * Will update the whenToRead based on the algorithm and the level
     * If hasRemembered is true, will increment level by one and update whenToRead
     * Else, update whenToRead with original level
     *
     * @param hasRemembered If the user has successfully remembered the translated word
     */
    public void updateLastRead(Boolean hasRemembered) {
        if (hasRemembered) {
            this.level += 1;
        }
        // Current date in ms + 1 day per level in ms
        this.whenToReview = new Date(new Date().getTime() + this.level * 86400000);
    }
}
