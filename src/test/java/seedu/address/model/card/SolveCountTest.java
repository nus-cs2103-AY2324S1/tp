package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        SolveCount count = new SolveCount();
        assertTrue(count.getSolveCount().equals(0));
        assertTrue(count.equals(0));
    }
}
