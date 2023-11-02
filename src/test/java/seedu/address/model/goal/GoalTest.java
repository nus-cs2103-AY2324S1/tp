package seedu.address.model.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.CS1101S;
import static seedu.address.testutil.TypicalCards.CS2100;

import org.junit.jupiter.api.Test;

import seedu.address.model.Deck;
import seedu.address.testutil.DeckBuilder;

public class GoalTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Goal(null));
    }

    @Test
    public void equals() {
        Deck deck = new DeckBuilder().withCard(CS2100).withCard(CS1101S).build();
        Goal goal = new Goal(deck);

        // same values -> returns true
        assertTrue(goal.equals(new Goal(new DeckBuilder().withCard(CS2100).withCard(CS1101S).build())));

        // same object -> returns true
        assertTrue(goal.equals(goal));

        // null -> returns false
        assertFalse(goal.equals(null));

        // different types -> returns false
        assertFalse(goal.equals(5.0f));

        // different values -> returns false
        assertFalse(goal.equals(new Goal(new Deck())));
    }

    @Test
    public void toStringMethod() {
        Deck deck = new DeckBuilder().withCard(CS2100).withCard(CS1101S).build();
        Goal goal = new Goal(deck);

        String expectedMessage = "Goal: 0/2, Goal has not been met, please solve more cards!";
        assertEquals(goal.toString(), expectedMessage);

        Deck otherDeck = new Deck();
        Goal otherGoal = new Goal(otherDeck);
        String otherExpectedMessage = "Goal: 0/0, Congratulations, you have met your goal!";
        assertEquals(otherGoal.toString(), otherExpectedMessage);
    }

    @Test
    public void solvedCardMethod() {
        Deck deck = new DeckBuilder().withCard(CS2100).build();
        Goal goal = new Goal(deck);
        goal.solvedCard();
        String expectedMessage = "Goal: 1/1, Congratulations, you have met your goal!";

        assertTrue(goal.getIsMet());
        assertEquals(goal.toString(), expectedMessage);
    }
}
