package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VisualTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Visual(null));
    }

    @Test
    public void isValidVisual() {
        // blank visual
        assertFalse(Visual.isValidVisual("")); // empty string
        assertFalse(Visual.isValidVisual(" ")); // spaces only

        // invalid visual
        assertFalse(Visual.isValidVisual("pie"));
        assertFalse(Visual.isValidVisual("chart"));
        assertFalse(Visual.isValidVisual("barchart"));

        // valid visual
        assertTrue(Visual.isValidVisual("BAR"));
        assertTrue(Visual.isValidVisual("TABLE"));
        assertTrue(Visual.isValidVisual("Bar"));
        assertTrue(Visual.isValidVisual("Table"));
        assertTrue(Visual.isValidVisual("BaR"));
        assertTrue(Visual.isValidVisual("TablE"));
        assertTrue(Visual.isValidVisual("bar"));
        assertTrue(Visual.isValidVisual("table"));
    }

    @Test
    public void hashCodeCheck() {
        Visual visual1 = new Visual("TABLE");
        Visual visual2 = new Visual("TABLE");
        assertEquals(visual1.hashCode(), visual2.hashCode());
    }

    @Test
    public void equals() {
        Visual visual = new Visual("BAR");

        // same values -> returns true
        assertTrue(visual.equals(new Visual("BAR")));

        // same object -> returns true
        assertTrue(visual.equals(visual));

        // null -> returns false
        assertFalse(visual.equals(null));

        // different types -> returns false
        assertFalse(visual.equals(5.0f));

        // different values -> returns false
        assertFalse(visual.equals(new Visual("TABLE")));
    }
}
