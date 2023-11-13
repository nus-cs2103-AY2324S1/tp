package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CycleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cycle(null));
    }

    @Test
    public void constructor_invalidCycle_throwsIllegalArgumentException() {
        String invalidCycle = "";
        assertThrows(IllegalArgumentException.class, () -> new Cycle(invalidCycle));
    }

    @Test
    public void isValidCycle() {
        // null cycle
        assertThrows(NullPointerException.class, () -> Cycle.isValidCycle(null));

        // blank cycle
        assertFalse(Cycle.isValidCycle("")); // empty string
        assertFalse(Cycle.isValidCycle(" ")); // spaces only

        // Invalid CYCLE values that violate the pattern
        assertFalse(Cycle.isValidCycle("-Invalid Cycle")); // Starts with a hyphen.
        assertFalse(Cycle.isValidCycle("Invalid#Cycle")); // Contains a '#' symbol.
        assertFalse(Cycle.isValidCycle("Invalid Cycle!")); // Contains '!' symbol.
        assertFalse(Cycle.isValidCycle("Invalid-Cycle@2023")); // Contains '@' symbol and numbers.

        // valid cycle
        assertTrue(Cycle.isValidCycle("Summer 2024"));
        assertTrue(Cycle.isValidCycle("Spring 2023"));
        assertTrue(Cycle.isValidCycle("Off-cycle 2025")); // Includes hyphen
        assertTrue(Cycle.isValidCycle("Fall Semester 2023")); // Long cycle
        assertTrue(Cycle.isValidCycle("Custom Cycle")); // No year
    }

    @Test
    public void equals() {
        Cycle cycle = new Cycle("Summer 2024");

        // same values -> returns true
        assertTrue(cycle.equals(new Cycle("Summer 2024")));

        // same object -> returns true
        assertTrue(cycle.equals(cycle));

        // null -> returns false
        assertFalse(cycle.equals(null));

        // different types -> returns false
        assertFalse(cycle.equals(5.0f));

        // different values -> returns false
        assertFalse(cycle.equals(new Cycle("Summer 2023")));
    }

    @Test
    public void compareTo() {
        Cycle cycle1 = new Cycle("Summer 2024");
        Cycle cycle2 = new Cycle("Fall 2023");
        Cycle cycle3 = new Cycle("Fall 2023");

        // cycle2 < cycle1
        assertTrue(cycle2.compareTo(cycle1) < 0);

        // cycle2 = cycle3
        assertEquals(0, cycle2.compareTo(cycle3));

        // cycle1 < cycle2
        assertTrue(cycle1.compareTo(cycle2) > 0);
    }
}

