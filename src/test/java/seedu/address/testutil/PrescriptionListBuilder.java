package seedu.address.testutil;

import seedu.address.model.PrescriptionList;
import seedu.address.model.prescription.Prescription;

/**
 * A utility class to help with building PrescriptionList objects.
 * Example usage: <br>
 *     {@code PrescriptionList pl = new PrescriptionListBuilder().withPrescription(PrescA, PrescB).build();}
 */
public class PrescriptionListBuilder {

    private PrescriptionList prescriptionList;

    public PrescriptionListBuilder() {
        prescriptionList = new PrescriptionList();
    }

    public PrescriptionListBuilder(PrescriptionList prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    /**
     * Adds a new {@code Prescription} to the {@code PrescriptionList} that we are building.
     */
    public PrescriptionListBuilder withPrescription(Prescription prescription) {
        prescriptionList.addPrescription(prescription);
        return this;
    }

    public PrescriptionList build() {
        return prescriptionList;
    }
}
