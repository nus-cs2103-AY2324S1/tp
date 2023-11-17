package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateOfBirthTest {
    @Test
    public void testConstructor_withValidDates() {
        assertDoesNotThrow(() -> new DateOfBirth("2023-10-23")); // ISO_LOCAL_DATE
    }

    @Test
    public void testConstructor_withNull() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void testConstructor_withFutureDate_throwsIllegalArgumentException() {
        String futureDate = LocalDate.now(ZoneId.of("Asia/Singapore")).plusDays(1).toString();
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(futureDate));
    }

    @Test
    public void testConstructor_withInvalidDate() {
        List<String> invalidDateInputs = List.of(
            " ", // space
            "", // empty
            "abc123", // alphaNumeric
            "1*1*2*", // NumericAndSymbols
            "01012023", // DDMMYYYY
            "01-01-2023", // DD-MM-YYYY
            "12302023", // MMDDYYYY
            "12-30-2023", // MM-DD-YYYY
            "40309999", // Out of calendar
            "20230928", // invalid format
            "28 September 2023", // invalid format
            "28 Sep 2023" // invalid format
        );
        for (String invalidDate : invalidDateInputs) {
            assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDate));
        }
    }

    @Test
    public void testIsValidDate_withValidDates() {
        assertTrue(DateOfBirth.isValidDate("2023-10-23")); //ISO_LOCAL_DATE
    }

    @Test
    public void testIsValidDate_withNull() {
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDate(null));
    }

    @Test
    public void testIsValidDate_withInvalidDates() {
        List<String> invalidDateInputs = List.of(
            " ", // space
            "", // empty
            "abc123", // alphaNumeric
            "1*1*2*", // numericAndSymbols
            "01012023", // DDMMYYYY
            "01-01-2023", // DD-MM-YYYY
            "12302023", // MMDDYYYY
            "12-30-2023", // MM-DD-YYYY
            "40309999", // out of calendar
            "20230928", // invalid format
            "28 September 2023", // invalid format
            "28 Sep 2023" // invalid format
        );
        for (String invalidDate : invalidDateInputs) {
            assertFalse(() -> DateOfBirth.isValidDate(invalidDate));
        }
    }

    @Test
    public void testGetAge_withEqualDate() {
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Singapore"));
        DateOfBirth dateOfBirth = new DateOfBirth(currentDate.toString());
        assertEquals(dateOfBirth.getAge(), String.format(DateOfBirth.AGE_FORMAT, 0, 0 , 0));
    }

    @Test
    public void testGetAge_withDayDifference() {
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Singapore"));
        LocalDate currentDateMinusOneDay = currentDate.minusDays(1);
        DateOfBirth dateOfBirth = new DateOfBirth(currentDateMinusOneDay.toString());
        assertEquals(dateOfBirth.getAge(), String.format(DateOfBirth.AGE_FORMAT, 0, 0 , 1));
    }

    @Test
    public void testGetAge_withMonthDifference() {
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Singapore"));
        LocalDate currentDateMinusOneMonth = currentDate.minusMonths(1);
        int differenceInDays = currentDate.lengthOfMonth() - currentDateMinusOneMonth.lengthOfMonth();
        if (differenceInDays < 0) {
            differenceInDays = 0;
        }
        DateOfBirth dateOfBirth = new DateOfBirth(currentDateMinusOneMonth.toString());
        assertEquals(dateOfBirth.getAge(), String.format(DateOfBirth.AGE_FORMAT, 0, 1, differenceInDays));
    }

    @Test
    public void testGetAge_withYearDifference() {
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Singapore"));
        LocalDate currentDateMinusOneYear = currentDate.minusYears(1);
        DateOfBirth dateOfBirth = new DateOfBirth(currentDateMinusOneYear.toString());
        assertEquals(dateOfBirth.getAge(), String.format(DateOfBirth.AGE_FORMAT, 1, 0, 0));
    }

    @Test
    public void testEquals_withNull() {
        DateOfBirth dateOfBirth = new DateOfBirth("2023-01-01");
        assertNotEquals(null, dateOfBirth);
    }

    @Test
    public void testEquals_withEqualDate() {
        DateOfBirth dateOfBirth = new DateOfBirth("2023-01-01");
        assertEquals(dateOfBirth, new DateOfBirth("2023-01-01"));
    }

    @Test
    public void testEquals_withNotEqualDate() {
        DateOfBirth dateOfBirth = new DateOfBirth("2023-01-01");
        assertNotEquals(dateOfBirth, new DateOfBirth("2023-10-10"));
    }

    @Test
    public void testHashCode() {
        DateOfBirth dateOfBirth1 = new DateOfBirth("2023-01-01");
        DateOfBirth dateOfBirth2 = new DateOfBirth("2023-01-01");
        assertEquals(dateOfBirth1.hashCode(), dateOfBirth2.hashCode());

    }

    @Test
    public void testToString() {
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Singapore"));
        DateOfBirth dateOfBirth = new DateOfBirth(currentDate.toString());
        assertEquals(dateOfBirth.getAge(), String.format(DateOfBirth.AGE_FORMAT, 0, 0, 0));
    }
}
