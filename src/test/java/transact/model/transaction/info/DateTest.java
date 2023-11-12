package transact.model.transaction.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

public class DateTest {

    // Test Construction with Valid Date String
    @Test
    public void testConstructionWithValidDateString() {
        String dateString = "01/01/20";
        Date date = new Date(dateString);
        assertNotNull(date);
    }

    // Test Construction with Invalid Date String (Null, Invalid Format, Invalid Date)
    @Test
    public void testConstructionWithInvalidDateString() {
        String invalidDateString = "01-01-20"; // Invalid format
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDateString));

        String nullDateString = null; // Null String
        assertThrows(NullPointerException.class, () -> new Date(nullDateString));

        String invalidDate = "32/01/20"; // Invalid date
        assertEquals("01/02/20", new Date(invalidDate).toString());
    }

    // Test getDate Method
    @Test
    public void testGetDateMethod() throws Exception {
        String dateString = "01/01/20";
        Date date = new Date(dateString);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        assertEquals(sdf.parse(dateString), date.getDate());
    }

    // Test isValidDate Method with Valid Date String
    @Test
    public void testIsValidDateWithValidString() {
        assertTrue(Date.isValidDate("01/01/20"));
    }

    // Test isValidDate Method with Invalid Date String
    @Test
    public void testIsValidDateWithInvalidString() {
        assertFalse(Date.isValidDate("32/01/20")); // Invalid date
        assertFalse(Date.isValidDate("01-01-20")); // Invalid format
        assertFalse(Date.isValidDate("01/01/2020")); // Invalid year format
    }

    // Test toString Method
    @Test
    public void testToStringMethod() {
        Date date = new Date("01/01/20");
        assertEquals("01/01/20", date.toString());
    }

    // Test equals Method
    @Test
    public void testEqualsMethod() {
        Date date1 = new Date("01/01/20");
        Date date2 = new Date("01/01/20");
        Date date3 = new Date("01/02/20");
        Date date4 = new Date("32/01/20");
        assertTrue(date1.equals(date2));
        assertFalse(date1.equals(date3));
        assertTrue(date3.equals(date4));
    }

    // Test hashCode Method
    @Test
    public void testHashCodeMethod() {
        Date date1 = new Date("01/01/20");
        Date date2 = new Date("01/01/20");
        assertEquals(date1.hashCode(), date2.hashCode());
    }
}
