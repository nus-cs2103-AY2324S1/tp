package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.junit.jupiter.api.Test;


public class AdmissionDateTest {

    @Test
    public void testConstructor_withValidDates() {
        assertDoesNotThrow(() -> new AdmissionDate("2023-10-23"));
    }

    @Test
    public void testConstructor_withNull() {
        assertThrows(NullPointerException.class, () -> new AdmissionDate(null));
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
            assertThrows(IllegalArgumentException.class, () -> new AdmissionDate(invalidDate));
        }
    }

    @Test
    public void testConstructor_withFutureDate_throwsIllegalArgumentException() {
        String futureDate = LocalDate.now(ZoneId.of("Asia/Singapore")).plusDays(1).toString();
        assertThrows(IllegalArgumentException.class, () -> new AdmissionDate(futureDate));
    }

    @Test
    public void testIsValidDate_withValidDates() {
        assertTrue(AdmissionDate.isValidDate("2023-10-23"));
    }

    @Test
    public void testIsValidDate_withNull() {
        assertThrows(NullPointerException.class, () -> AdmissionDate.isValidDate(null));
    }

    @Test
    public void testIsValidDate_withInvalidDates() {
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
            assertFalse(() -> AdmissionDate.isValidDate(invalidDate));
        }
    }

    @Test
    public void testEquals_withNull() {
        AdmissionDate admissionDate = new AdmissionDate("2023-01-01");
        assertNotEquals(null, admissionDate);
    }

    @Test
    public void testEquals_withEqualDate() {
        AdmissionDate admissionDate = new AdmissionDate("2023-01-01");
        assertEquals(admissionDate, new AdmissionDate("2023-01-01"));
    }

    @Test
    public void testEquals_withNotEqualDate() {
        AdmissionDate admissionDate = new AdmissionDate("2023-01-01");
        assertNotEquals(admissionDate, new AdmissionDate("2023-10-10"));
    }

    @Test
    public void testHashCode() {
        AdmissionDate admissionDate1 = new AdmissionDate("2023-01-01");
        AdmissionDate admissionDate2 = new AdmissionDate("2023-01-01");
        assertEquals(admissionDate1.hashCode(), admissionDate2.hashCode());

    }

    @Test
    public void testToString() {
        AdmissionDate admissionDate = new AdmissionDate("2023-01-01");
        assertEquals(admissionDate.toString(), "2023-01-01");
    }
}

