package seedu.flashlingo.model.flashcard;

/**
 * Represents the level of familiarity to a flashcard
 *
 * @author Nathanael M. Tan
 * @version 1.2
 * @since 1.2
 */
public class ProficiencyLevel {
    private int level; // Base level of 1
    private int deleteThreshold; // After which level of proficiency to permanently remove flashcard
    public ProficiencyLevel(int level) {
        this.level = level;
    }

    /**
     * Returns the time interval to the next review of the flashcard
     * Based upon the Leitner System, to study with spaced repetition
     * @return
     */
    public long calculateNextReviewInterval() {
        return this.level * 86400000L;
    }

    /**
     * Upon successfully remembering the flashcard, increment level to increase review interval
     */
    public void upgradeLevel() {
        this.level += 1;
    }

    /**
     * Upon failure to remember, downgrade level to decrease interval to review
     */
    public void downgradeLevel() {
        if (level != 1) { // Prevent going below base level
            this.level -= 1;
        }
    }

    /**
     * Whether to delete
     * @return Whether to permanently delete the flashcard after sufficient successful attempts at remembering
     */
    public boolean toDelete() {
        if (level >= deleteThreshold) {
            return true;
        }
        return false;
    }

    /**
     * Returns the level of proficiency
     * @return The level of proficiency.
     */
    public int getLevel() {
        return level;
    }
    @Override
    public String toString() {
        return String.valueOf(this.level);
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
