package seedu.address.model.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import seedu.address.model.Deck;

public class GoalTest {
    private Goal goal;

    @BeforeEach
    public void setUp() {
        goal = new Goal(new Deck()); // You can pass a Deck mock or null as an argument
    }

    @Test
    public void defaultGoalIsInitializedCorrectly() {
        assertEquals(0, goal.getCurrent());
        assertEquals(0, goal.getTarget());
        assertTrue(goal.getIsMet());
        assertEquals("Goal: 0/0, Congratulations, you have met your goal!", goal.getGoalText());
    }

    @Test
    public void setTargetAndCurrentValues() {
        goal.setTarget(10);
        goal.setCurrent(5);

        assertEquals(10, goal.getTarget());
        assertEquals(5, goal.getCurrent());
        assertFalse(goal.getIsMet());
        assertEquals("Goal: 5/10, Goal has not been met, please solve more cards!", goal.getGoalText());
    }

    @Test
    public void goalIsMet() {
        goal.setTarget(5);
        goal.setCurrent(5);

        assertEquals(5, goal.getTarget());
        assertEquals(5, goal.getCurrent());
        assertTrue(goal.getIsMet());
        assertEquals("Goal: 5/5, Congratulations, you have met your goal!", goal.getGoalText());
    }

    @Test
    public void testObservableProperty() {
        StringProperty goalTextProperty = goal.goalTextProperty();

        String[] changedValue = new String[1];
        InvalidationListener listener = observable -> changedValue[0] = goalTextProperty.get();

        goalTextProperty.addListener(listener);
        goal.setCurrent(3);

        assertEquals("Goal: 3/0, Congratulations, you have met your goal!", changedValue[0]);
        assertEquals("Goal: 3/0, Congratulations, you have met your goal!", goalTextProperty.get());

        goal.setTarget(5);

        assertEquals("Goal: 3/5, Goal has not been met, please solve more cards!", changedValue[0]);
        assertEquals("Goal: 3/5, Goal has not been met, please solve more cards!", goalTextProperty.get());

        goal.solvedCard();

        assertEquals("Goal: 4/5, Goal has not been met, please solve more cards!", changedValue[0]);
        assertEquals("Goal: 4/5, Goal has not been met, please solve more cards!", goalTextProperty.get());

        goal.setTarget(4);

        assertEquals("Goal: 4/4, Congratulations, you have met your goal!", changedValue[0]);
        assertEquals("Goal: 4/4, Congratulations, you have met your goal!", goalTextProperty.get());

        goalTextProperty.removeListener(listener);
    }
    @Test
    public void equals() {
        Goal goal = new Goal(new Deck());
        Goal goal2 = new Goal(new Deck());
        goal2.setTarget(5);

        // same values -> returns true
        assertTrue(goal.equals(new Goal(new Deck())));

        // same object -> returns true
        assertTrue(goal.equals(goal));

        // null -> returns false
        assertFalse(goal.equals(null));

        // different types -> returns false
        assertFalse(goal.equals(1));

        // different values -> returns false
        assertFalse(goal.equals(goal2));
    }
}
