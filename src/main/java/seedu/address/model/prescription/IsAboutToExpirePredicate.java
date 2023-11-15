package seedu.address.model.prescription;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Prescription}'s {@code Date} is in a week.
 */
public class IsAboutToExpirePredicate implements Predicate<Prescription> {

    @Override
    public boolean test(Prescription prescription) {
        if (prescription.getExpiryDate().isEmpty()) {
            return false;
        }

        LocalDate prescriptionExpiryDate = prescription.getExpiryDate().get().getDate();
        LocalDate today = LocalDate.now();

        return prescriptionExpiryDate.isBefore(today.plusDays(7));
    }
}
