package seedu.address.model.prescription;

import java.util.function.Predicate;

/**
 * Tests that a {@code Prescription} is completed.
 */
public class IsCompletedPredicate implements Predicate<Prescription> {

    @Override
    public boolean test(Prescription prescription) {
        if (prescription.getDosage().isEmpty()) {
            return false;
        }

        int consumptionCount = Integer.parseInt(prescription.getConsumptionCount().toString());
        int dosage = Integer.parseInt(prescription.getDosage().get().toString());

        return consumptionCount >= dosage;
    }
}
