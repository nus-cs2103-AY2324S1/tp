package seedu.address.model.person.timetable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeBlockTest {

    @Test
    public void validTimeBlockCreation() {
        String validTimeBlockString = "Monday 0800 1000";
        assertDoesNotThrow(() -> new Cca("Sleep", validTimeBlockString));
    }

    @Test
    public void invalidTimeBlockCreation() {
        String invalidTimeBlockString = "InvalidTimeBlockString";
        assertThrows(IllegalArgumentException.class, () -> new Cca("Sleep",
                invalidTimeBlockString));
    }

    @Test
    public void isValidTimeBlockValid() {
        String validTimeBlockString = "Monday 0800 1000";
        assertTrue(Cca.isValidTimeBlock(validTimeBlockString));
    }

    @Test
    public void isValidTimeBlockInvalid() {
        String invalidTimeBlockString = "InvalidTimeBlockString";
        assertFalse(Cca.isValidTimeBlock(invalidTimeBlockString));
    }

    @Test
    public void compareTimeBlocks() {
        TimeBlock timeBlock1 = new Cca("sleep", "Monday 0900 1100");
        TimeBlock timeBlock2 = new Cca("sleep", "Monday 1200 1300");
        assertTrue(timeBlock1.compareTo(timeBlock2) < 0);
    }

    @Test
    public void compareTimeBlocksByStartTime() {
        TimeBlock timeBlock1 = new Cca("sleep", "Monday 0800 1000");
        TimeBlock timeBlock2 = new Cca("sleep", "Monday 0900 1100");
        assertTrue(timeBlock1.compareByStartTime(timeBlock2) < 0);
    }

    @Test
    public void compareTimeBlocksByDay() {
        TimeBlock timeBlock1 = new Cca("sleep", "Monday 0900 1100");
        TimeBlock timeBlock2 = new Cca("sleep", "Tuesday 1200 1300");
        assertTrue(timeBlock1.compareTo(timeBlock2) < 0);
    }

    @Test
    public void overlappingTimeBlocks() {
        TimeBlock timeBlock1 = new Cca("sleep", "Monday 0800 1000");
        TimeBlock timeBlock2 = new Cca("sleep", "Monday 0900 1100");
        assertTrue(timeBlock1.isOverlap(timeBlock2));
    }

    @Test
    public void nonOverlappingTimeBlocks() {
        TimeBlock timeBlock1 = new Cca("sleep", "Monday 0800 1000");
        TimeBlock timeBlock2 = new Cca("sleep", "Monday 1200 1300");
        TimeBlock timeBlock3 = new Cca("sleep", "Tuesday 0800 1000");
        assertFalse(timeBlock1.isOverlap(timeBlock2));
        assertFalse(timeBlock1.isOverlap(timeBlock3));
    }

    @Test
    public void isVarious() {
        TimeBlock timeBlock1 = new Cca("sleep", "Monday 0800 1000");
        TimeBlock timeBlock2 = new Module("CS2103", "Monday 0800 1000");
        assertFalse(timeBlock1.isModule());
        assertFalse(timeBlock1.isFreeTime());
        assertFalse(timeBlock1.isDatedEvent());
        assertFalse(timeBlock2.isCca());
    }

    @Test
    public void getStartTime() {
        TimeBlock timeBlock = new Cca("sleep", "Monday 0800 1000");
        assertEquals("0800", timeBlock.getStartTime());
    }

    @Test
    public void getEndTime() {
        TimeBlock timeBlock = new Cca("sleep", "Monday 0800 1000");
        assertEquals("1000", timeBlock.getEndTime());
    }

    @Test
    public void isOnDayMatching() {
        TimeBlock timeBlock = new Cca("sleep", "Monday 0800 1000");
        assertTrue(timeBlock.isOnDay(1)); // Monday is represented as 1
    }

    @Test
    public void isOnDayNotMatching() {
        TimeBlock timeBlock = new Cca("sleep", "Monday 0800 1000");
        assertFalse(timeBlock.isOnDay(2)); // Tuesday is not matching
    }

    @Test
    public void equalsAndHashCode() {
        TimeBlock timeBlock1 = new Cca("sleep", "Monday 0800 1000");
        TimeBlock timeBlock2 = new Cca("sleep", "Monday 0800 1000");
        assertEquals(timeBlock1, timeBlock2);
        assertEquals(timeBlock1.hashCode(), timeBlock2.hashCode());
    }
}

