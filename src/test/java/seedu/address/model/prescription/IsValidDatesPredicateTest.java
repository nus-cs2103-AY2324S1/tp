package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPrescriptions.EMPTY_PRESCRIPTION;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PrescriptionBuilder;

public class IsValidDatesPredicateTest {
    private static final IsValidDatesPredicate predicate = new IsValidDatesPredicate();

    @Test
    public void test_isValidDates_returnsTrue() {
        // Only start date
        assertTrue(predicate.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION).withStartDate("01/01/2023").build()));

        // Only start and end date
        assertTrue(predicate.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
                .withName("Aspirin")
                .withStartDate("01/01/2023")
                .withEndDate("10/10/2023")
                .build()));

        // Only start and expiry date
        assertTrue(predicate.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
            .withName("Aspirin")
            .withStartDate("01/01/2023")
            .withExpiryDate("11/10/2023")
            .build()));

        // Start, end and expiry date
        assertTrue(predicate.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
            .withName("Aspirin")
            .withStartDate("01/01/2023")
            .withEndDate("10/10/2023")
            .withExpiryDate("11/10/2023")
            .build()));
    }

    @Test
    public void test_isValidDates_returnsFalse() {
        // Start date after end date
        assertFalse(predicate.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
                .withName("Aspirin")
                .withStartDate("01/01/2023")
                .withEndDate("10/10/2022")
                .build()));

        // Start date after expiry date
        assertFalse(predicate.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
            .withName("Aspirin")
            .withStartDate("01/01/2023")
            .withExpiryDate("11/10/2022")
            .build()));

        // End date after expiry date
        assertFalse(predicate.test(new PrescriptionBuilder(EMPTY_PRESCRIPTION)
            .withName("Aspirin")
            .withStartDate("01/01/2022")
            .withEndDate("11/10/2023")
            .withExpiryDate("11/10/2022")
            .build()));
    }
}
