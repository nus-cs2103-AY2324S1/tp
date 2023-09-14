package seedu.address.pojo;

import java.util.Date;

public class Flashcard {
    private String originalWord;
    private String translatedWord;
    private Date whenToReview; // Date the flashcard was needs to be reviewed
    private int level; // How many times successfully remembered
    private boolean toDelete = false;

    public Flashcard(String originalWord, String translatedWord, Date whenToReview, int level) {
        this.level = level;
        this.whenToReview = whenToReview;
        this.translatedWord = translatedWord;
        this.originalWord = originalWord;
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
        if (level == 5) {
            toDelete = true; // If remembered enough, delete from the list
        }
         // Current date in ms + 1 day per level in ms
        this.whenToReview = new Date(new Date().getTime() + this.level * 86400000);
    }

    /**
     * Formats Flashcard for writing to textFile
     *
     * @return Tab separated String formatted for writing
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(originalWord);
        sb.append("\t");
        sb.append(translatedWord);
        sb.append("\t");
        sb.append(whenToReview.toString());
        sb.append("\t");
        sb.append(level);
        return sb.toString();
    }

    public boolean isOverdue() {
        return this.whenToReview.before(new Date());
    }


}
