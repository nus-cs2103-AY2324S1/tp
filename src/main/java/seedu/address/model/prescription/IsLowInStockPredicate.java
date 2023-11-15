package seedu.address.model.prescription;

import java.util.function.Predicate;

/**
 * Tests that a {@code Prescription}'s {@code Stock} is low in stock.
 */
public class IsLowInStockPredicate implements Predicate<Prescription> {
    @Override
    public boolean test(Prescription prescription) {
        if (prescription.getTotalStock().isEmpty() || prescription.getDosage().isEmpty()) {
            return false;
        }
        int prescriptionTotalStock = Integer.parseInt(prescription.getTotalStock().get().toString());
        int prescriptionDosages = Integer.parseInt(prescription.getDosage().get().toString());

        int minimumStock = 10;
        int minimumDosages = 0;
        int minimumDaysOfStock = 7;

        return prescriptionDosages > minimumDosages
                && prescriptionTotalStock <= minimumStock
                || prescriptionTotalStock / prescriptionDosages <= minimumDaysOfStock;
    }
}
