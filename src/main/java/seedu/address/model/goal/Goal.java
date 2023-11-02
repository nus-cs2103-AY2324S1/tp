package seedu.address.model.goal;

import seedu.address.model.Deck;

/**
 * Goal classes which contains the target number of cards to be
 * solved in the session, current number of cards solved, as well
 * as whether the goal is met.
 */
public class Goal {
    private int target;
    private int current;
    private boolean isMet;

    /**
     * Constructor for the Goal class.
     * Sets the target to the size of the deck,
     * and current number of cards solved to be 0.
     *
     * @param deck Used to determine the base target value.
     */
    public Goal(Deck deck) {
        this.target = deck.getNumberOfCards();
        this.current = 0;
        this.isMet = target == current;
    }

    public int getTarget() {
        return this.target;
    }

    public int getCurrent() {
        return this.current;
    }

    public boolean getIsMet() {
        return this.isMet;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    /**
     * Increments current counter by one when a
     * card is solved.
     */
    public void solvedCard() {
        this.current += 1;
        isMet = current == target;
    }

    @Override
    public String toString() {
        String goalMet = isMet
                ? "Congratulations, you have met your goal!"
                : "Goal has not been met, please solve more cards!";
        return "Goal: " + current + "/" + target + ", " + goalMet;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Goal)) {
            return false;
        }

        Goal otherGoal = (Goal) other;
        return target == otherGoal.target && current == otherGoal.current;
    }
}
