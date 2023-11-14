package seedu.address.model.prescription;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that a {@code Prescription}'s {@code Date} is today.
 */
public class IsTodayPredicate implements Predicate<Prescription> {

    @Override
    public boolean test(Prescription prescription) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = prescription.getStartDate().getDate();
        long daysBetween = ChronoUnit.DAYS.between(startDate, today);

        if (today.isBefore(startDate)) {
            return false;
        }

        Optional<Frequency> frequency = prescription.getFrequency();
        if (frequency.isEmpty()) {
            return true;
        }

        int daysInWeek = 7;
        int daysInMonth = 30;

        switch (frequency.get().toString()) {
        case "Daily":
            return true;
        case "Weekly":
            return daysBetween % daysInWeek == 0;
        case "Monthly":
            return daysBetween % daysInMonth == 0;
        default:
            return false;
        }
    }
}
