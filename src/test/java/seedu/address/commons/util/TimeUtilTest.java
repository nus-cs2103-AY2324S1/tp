package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TimeUtilTest {
    @Test
    public void testParseDateString_withValidDateInputs() {
        List<String> validDateInputs = List.of(
            "2023-09-28"
        );
        LocalDate expected = LocalDate.of(2023, 9, 28); // arbitrary test date
        for (String input : validDateInputs) {
            try {
                LocalDate actual = TimeUtil.parseDateString(input);
                assertEquals(expected, actual);
            } catch (NoSuchElementException e) {
                fail();
            }
        }
    }

    @Test
    public void testParseDateString_withInvalidDateInputs_shouldThrowNoSuchElementException() {
        List<String> invalidDateInputs = List.of(
            "", // empty
            "abc", // non-numeric
            "2023-13-25", // invalid month
            "2023-09-32", // invalid day
            "2021-02-29", // 29 Feb on a non-leap year.
            "2023090", // incomplete string
            "15 Febrary 2023", // mis-spelt month
            "20230928", // invalid format
            "28 September 2023", // invalid format
            "28 Sep 2023" // invalid format
        );
        for (String invalidInput : invalidDateInputs) {
            assertThrows(NoSuchElementException.class, () -> TimeUtil.parseDateString(invalidInput));
        }
    }

    @Test
    public void testIsValidDate_withValidDateInputs() {
        List<String> validDateInputs = List.of(
            "2023-09-28"
        );
        for (String validInput : validDateInputs) {
            assertTrue(TimeUtil.isValidDate(validInput));
        }
    }

    @Test
    public void testIsValidDate_withInvalidInputs() {
        List<String> invalidDateInputs = List.of(
            "", // empty
            "abc", // non-numeric
            "2023-13-25", // invalid month
            "2023-09-32", // invalid day
            "2021-02-29", // 29 Feb on a non-leap year.
            "2023090", // incomplete string
            "15 Febrary 2023", // mis-spelt month
            "20230928", // invalid format
            "28 September 2023", // invalid format
            "28 Sep 2023" // invalid format
        );
        for (String invalidInput : invalidDateInputs) {
            assertFalse(TimeUtil.isValidDate(invalidInput));
        }
    }

    @Test
    public void testFindFormatter_withValidDateStringFormats() {
        List<String> validDateInputs = List.of(
            "2023-09-28" // ISO_LOCAL_DATE
        );

        for (String validInput : validDateInputs) {
            Optional<DateTimeFormatter> optionalFormatter = TimeUtil.findFormatter(validInput);
            assertTrue(optionalFormatter.isPresent(), "Valid date string: \"" + validInput + "\" could not be parsed.");
        }
    }

    @Test
    public void testFindFormatter_withInvalidDateStringFormats() {
        List<String> invalidDateInputs = List.of(
            "", // empty
            "abc", // non-numeric
            "2023-13-25", // invalid month
            "2023-09-32", // invalid day
            "2021-02-29", // 29 Feb on a non-leap year.
            "2023090", // incomplete string
            "15 Febrary 2023", // mis-spelt month
            "20230928", // invalid format
            "28 September 2023", // invalid format
            "28 Sep 2023" // invalid format
        );
        for (String invalidInput : invalidDateInputs) {
            Optional<DateTimeFormatter> optionalFormatter = TimeUtil.findFormatter(invalidInput);
            assertFalse(optionalFormatter.isPresent());
        }
    }

    @Test
    public void testGetValidDateFormats_shouldReturnExampleFormatStrings() {
        // Call the method to test
        String formatsString = TimeUtil.getValidDateFormats();

        // Define the expected date strings. These should match the formats used in your TimeUtil class.
        String expectedDate = "'2023-10-15'"; // Corresponds to DateTimeFormatter.ISO_LOCAL_DATE

        // Check that the formatsString contains all expected date strings
        assertTrue(formatsString.contains(expectedDate), "Formats string should contain " + expectedDate);
    }

    @Test
    public void testIsInFuture_withFutureDate_returnsTrue() {
        LocalDate futureDate = LocalDate.now(ZoneId.of("Asia/Singapore")).plusDays(1);
        assertTrue(TimeUtil.isFutureDate(futureDate));
    }

    @Test
    public void testIsInFuture_withPastDate_returnsFalse() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertFalse(TimeUtil.isFutureDate(pastDate));
    }
}
