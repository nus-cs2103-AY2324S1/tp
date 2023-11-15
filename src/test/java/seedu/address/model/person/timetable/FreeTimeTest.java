package seedu.address.model.person.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FreeTimeTest {

    @Test
    public void createValidFreeTime() {
        // Test creating a valid FreeTime object
        FreeTime freeTime = new FreeTime("Monday 0800 1000");
        assertEquals("[Monday 0800 1000]", freeTime.toString());
    }

    @Test
    public void createValidFreeTimeCaseInsensitive() {
        // Test creating a valid FreeTime object with case-insensitive day
        FreeTime freeTime = new FreeTime("tuesday 1400 1500");
        assertEquals("[Tuesday 1400 1500]", freeTime.toString());
    }

    @Test
    public void isValidFreeTime() {
        assertFalse(FreeTime.isValidFreeTime("")); // empty string
        assertTrue(FreeTime.isValidFreeTime("Monday 0800 1000")); // valid FreeTime
    }

    @Test
    public void compareTo() {
        FreeTime freeTime1 = new FreeTime("Monday 0800 1000");
        FreeTime freeTime2 = new FreeTime("Monday 0900 1100");
        FreeTime freeTime3 = new FreeTime("Tuesday 0900 1100");

        // Test compareTo
        assertTrue(freeTime1.compareTo(freeTime2) < 0);
        assertTrue(freeTime2.compareTo(freeTime1) > 0);
        assertTrue(freeTime1.compareTo(freeTime3) < 0);
    }

    @Test
    public void overlap() {
        FreeTime freeTime1 = new FreeTime("Monday 0800 1000");
        FreeTime freeTime2 = new FreeTime("Monday 0900 1100");
        FreeTime freeTime3 = new FreeTime("Tuesday 0900 1100");
        FreeTime freeTime4 = new FreeTime("Monday 1200 1300");

        // Test overlap
        FreeTime overlap = freeTime1.overlap(freeTime2);
        assertEquals("[Monday 0900 1000]", overlap.toString());

        overlap = freeTime1.overlap(freeTime3);
        assertNull(overlap);

        overlap = freeTime1.overlap(freeTime4);
        assertNull(overlap);
    }

    @Test
    public void isOverlap() {
        FreeTime freeTime1 = new FreeTime("Monday 0800 1000");
        FreeTime freeTime2 = new FreeTime("Monday 0900 1100");
        FreeTime freeTime3 = new FreeTime("Tuesday 0900 1100");

        // Test isOverlap
        assertTrue(freeTime1.isOverlap(freeTime2));
        assertFalse(freeTime1.isOverlap(freeTime3));
    }

    @Test
    public void equals() {
        FreeTime freeTime1 = new FreeTime("Monday 0800 1000");
        FreeTime freeTime2 = new FreeTime("Monday 0800 1000");
        FreeTime freeTime3 = new FreeTime("Tuesday 0900 1100");

        // Test equals
        assertTrue(freeTime1.equals(freeTime1));
        assertTrue(freeTime1.equals(freeTime2));
        assertFalse(freeTime1.equals(freeTime3));
        assertFalse(freeTime1.equals(null));

    }

    @Test
    public void testHashCode() {
        FreeTime freeTime1 = new FreeTime("Monday 0800 1000");
        FreeTime freeTime2 = new FreeTime("Monday 0800 1000");
        FreeTime freeTime3 = new FreeTime("Tuesday 0900 1100");

        // Test hashCode
        assertEquals(freeTime1.hashCode(), freeTime2.hashCode());
        assertNotEquals(freeTime1.hashCode(), freeTime3.hashCode());
    }

    @Test
    public void testToString() {
        FreeTime freeTime1 = new FreeTime("Monday 0800 1000");

        // Test toString
        assertEquals("[Monday 0800 1000]", freeTime1.toString());
    }

}
