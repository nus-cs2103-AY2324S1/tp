package seedu.address.model.card;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class PracticeDateTest {
    @Test
    public void getDisplayName() {
        PracticeDate practiceDate1 = new PracticeDate(LocalDateTime.of(2023, 12, 8, 7, 4, 0));
        PracticeDate practiceDate2 = new PracticeDate(LocalDateTime.of(2024, 6, 30, 12, 1, 0));
        PracticeDate practiceDate3 = new PracticeDate(LocalDateTime.of(2022, 3, 17, 0, 0, 23));

        assertEquals(practiceDate1.getDisplayName(), "8 December 2023");
        assertEquals(practiceDate2.getDisplayName(), "30 June 2024");
        assertEquals(practiceDate3.getDisplayName(), "17 March 2022");
    }

    @Test
    public void equals() {
        PracticeDate practiceDate = new PracticeDate(LocalDateTime.of(2023, 12, 8, 7, 4, 0));

        // same values -> returns true
        assertTrue(practiceDate.equals(new PracticeDate(LocalDateTime.of(2023, 12, 8, 7, 4, 0))));

        // same object -> returns true
        assertTrue(practiceDate.equals(practiceDate));

        // null -> returns false
        assertFalse(practiceDate.equals(null));

        // different types -> returns false
        assertFalse(practiceDate.equals("clown"));

        // different values -> returns false
        assertFalse(practiceDate.equals(new PracticeDate(LocalDateTime.of(2024, 2, 8, 7, 4, 0))));
    }

    @Test
    public void hashcode() {
        PracticeDate practiceDate =
                new PracticeDate(LocalDateTime.of(2023, 12, 8, 7, 4, 0));

        // same values -> returns true
        assertEquals(practiceDate.hashCode(),
                new PracticeDate(LocalDateTime.of(2023, 12, 8, 7, 4, 0)).hashCode());

        // different types -> returns false
        assertNotEquals(practiceDate.hashCode(), "clown".hashCode());

        // different values -> returns false
        assertNotEquals(practiceDate.hashCode(),
                new PracticeDate(LocalDateTime.of(2024, 2, 8, 7, 4, 0)).hashCode());
    }
}
