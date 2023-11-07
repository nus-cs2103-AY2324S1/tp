package seedu.address.model.goal;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import seedu.address.model.Deck;

/**
 * Goal classes which contains the target number of cards to be
 * solved in the session, current number of cards solved, as well
 * as whether the goal is met.
 */
public class Goal implements Observable {
    public static final String MESSAGE_CONSTRAINTS =
            "Goal can only be a positive integer and must not be blank";

    private int target;
    private int current;
    private boolean isMet;

    // Define a StringProperty to hold the goal text
    private final StringProperty goalTextProperty = new SimpleStringProperty();

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
        this.isMet = current >= target;
        updateGoalText();
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

    public StringProperty goalTextProperty() {
        return goalTextProperty;
    }

    public String getGoalText() {
        return goalTextProperty.get();
    }

    public void setTarget(int target) {
        this.target = target;
        isMet = current >= target;
        updateGoalText();
    }

    public void setCurrent(int current) {
        this.current = current;
        isMet = current >= target;
        updateGoalText();
    }

    /**
     * Increments current counter by one when a
     * card is solved.
     */
    public void solvedCard() {
        this.current += 1;
        isMet = current >= target;
        updateGoalText();
    }

    private void updateGoalText() {
        String goalMet = isMet
                ? "Congratulations, you have met your goal!"
                : "Goal has not been met, please solve more cards!";
        goalTextProperty.set("Goal: " + current + "/" + target + ", " + goalMet);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Goal)) {
            return false;
        }

        Goal otherGoal = (Goal) other;
        return target == otherGoal.target && current == otherGoal.current;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        goalTextProperty.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        goalTextProperty.removeListener(listener);
    }
}
