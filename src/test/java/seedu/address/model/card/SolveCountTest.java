package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SolveCountTest {
    @Test
    public void incrementSolveCountMethod() {
        SolveCount count = new SolveCount();
        count.incrementSolveCount();
        assertEquals(count.getSolveCount(), 1);

        count.incrementSolveCount();
        assertEquals(count.getSolveCount(), 2);
    }

    @Test
    public void equalsMethod() {
        SolveCount solveCount = new SolveCount();
        SolveCount otherSolveCount = new SolveCount();

        // same object -> returns true
        assertTrue(solveCount.equals(solveCount));

        // null -> returns false
        assertFalse(solveCount.equals(null));

        // integer object -> returns true
        assertTrue(solveCount.equals(0));

        // other objects -> false
        assertFalse(solveCount.equals("other object"));

        // different values -> returns false
        otherSolveCount.incrementSolveCount();
        assertFalse(solveCount.equals(otherSolveCount));
    }
}
