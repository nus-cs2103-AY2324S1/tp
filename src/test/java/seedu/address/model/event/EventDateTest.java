package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class EventDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(ParseException.class, () -> new EventDate(null));
    }

    @Test
    public void constructor_invalidEventDate_throwsIllegalArgumentException() {
        String invalidEventDate = "";
        assertThrows(ParseException.class, () -> new EventDate(invalidEventDate));
    }

    @Test
    public void isValidEventDate() {
        // null dates
        assertFalse(() -> EventDate.isValidDate(null));

        // invalid dates
        assertFalse(() -> EventDate.isValidDate("")); // empty string
        assertFalse(() -> EventDate.isValidDate(" ")); // spaces only
        assertFalse(() -> EventDate.isValidDate("2020-13-13")); // invalid month
        assertFalse(() -> EventDate.isValidDate("2020-01-32")); // invalid day
        assertFalse(() -> EventDate.isValidDate("2020-25-02")); // invalid format yyyy-dd-MM

        // valid dates
        assertTrue(() -> EventDate.isValidDate("2020-01-01")); // valid date
        assertTrue(() -> EventDate.isValidDate("2020-02-29")); // valid leap year date
        assertTrue(() -> EventDate.isValidDate("2020-02-31")); // corrects incorrect day in February
    }

    @Test
    public void equals() throws ParseException {
        EventDate eventDate = new EventDate("2020-01-01");

        // same values -> returns true
        assertTrue(eventDate.equals(new EventDate("2020-01-01")));

        // same object -> returns true
        assertTrue(eventDate.equals(eventDate));

        // null -> returns false
        assertFalse(eventDate.equals(null));

        // different types -> returns false
        assertFalse(eventDate.equals(5.0f));

        // different values -> returns false
        assertFalse(eventDate.equals(new EventDate("2020-01-02")));
    }

    @Test
    public void toStringTest() throws ParseException {
        EventDate eventDate = new EventDate("2020-01-01");
        assertTrue(eventDate.toString().equals("2020-01-01"));
    }

    @Test
    public void forDisplayTest() throws ParseException {
        EventDate eventDate = new EventDate("2020-01-01");
        assertTrue(eventDate.forDisplay().equals("01 Jan 2020"));
    }

    @Test
    public void getDateTest() throws ParseException {
        EventDate eventDate = new EventDate("2020-01-01");
        assertTrue(eventDate.getDate().toString().equals("2020-01-01"));
    }
}
