package seedu.address.model.person.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HalfHourBlocksTest {

    @Test
    public void createHalfHourBlocks() {
        HalfHourBlocks halfHourBlocks = new HalfHourBlocks(16, 32);

        // Test creation of HalfHourBlocks
        assertTrue(halfHourBlocks.overlaps(new HalfHourBlocks(0, 48)));
        assertFalse(halfHourBlocks.overlaps(new HalfHourBlocks(0, 16)));
    }

    @Test
    public void compareTo() {
        HalfHourBlocks halfHourBlocks1 = new HalfHourBlocks(16, 32);
        HalfHourBlocks halfHourBlocks2 = new HalfHourBlocks(24, 40);
        HalfHourBlocks halfHourBlocks3 = new HalfHourBlocks(0, 8);

        // Test compareTo
        assertTrue(halfHourBlocks1.compareTo(halfHourBlocks2) < 0);
        assertTrue(halfHourBlocks2.compareTo(halfHourBlocks1) > 0);
        assertTrue(halfHourBlocks1.compareTo(halfHourBlocks3) > 0);
    }

    @Test
    public void overlaps() {
        HalfHourBlocks halfHourBlocks1 = new HalfHourBlocks(16, 32);
        HalfHourBlocks halfHourBlocks2 = new HalfHourBlocks(24, 40);
        HalfHourBlocks halfHourBlocks3 = new HalfHourBlocks(0, 8);

        // Test overlaps method
        assertTrue(halfHourBlocks1.overlaps(halfHourBlocks2));
        assertFalse(halfHourBlocks1.overlaps(halfHourBlocks3));
    }

    @Test
    public void getOverlap() {
        HalfHourBlocks halfHourBlocks1 = new HalfHourBlocks(16, 32);
        HalfHourBlocks halfHourBlocks2 = new HalfHourBlocks(24, 40);

        // Test getOverlap method
        HalfHourBlocks overlap = halfHourBlocks1.getOverlap(halfHourBlocks2);
        assertTrue(overlap.overlaps(halfHourBlocks1));
        assertTrue(overlap.overlaps(halfHourBlocks2));
    }

    @Test
    public void equals() {
        HalfHourBlocks halfHourBlocks1 = new HalfHourBlocks(8, 20);
        HalfHourBlocks halfHourBlocks2 = new HalfHourBlocks(8, 20);
        HalfHourBlocks halfHourBlocks3 = new HalfHourBlocks(12, 24);

        // Test equals method
        assertTrue(halfHourBlocks1.equals(halfHourBlocks1));
        assertTrue(halfHourBlocks1.equals(halfHourBlocks2));
        assertFalse(halfHourBlocks1.equals(halfHourBlocks3));
        assertFalse(halfHourBlocks1.equals(null));
    }

    @Test
    public void testHashCode() {
        HalfHourBlocks halfHourBlocks1 = new HalfHourBlocks(8, 20);
        HalfHourBlocks halfHourBlocks2 = new HalfHourBlocks(8, 20);
        HalfHourBlocks halfHourBlocks3 = new HalfHourBlocks(12, 24);

        // Test hashCode method
        assertEquals(halfHourBlocks1.hashCode(), halfHourBlocks2.hashCode());
        assertNotEquals(halfHourBlocks1.hashCode(), halfHourBlocks3.hashCode());
    }

    @Test
    public void testToString() {
        HalfHourBlocks halfHourBlocks = new HalfHourBlocks(8, 20);

        // Test toString method
        assertEquals("0400 1000", halfHourBlocks.toString());
    }
}
