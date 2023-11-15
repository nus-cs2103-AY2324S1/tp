package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PrescriptionList;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;

/**
 * Contains utility methods for populating {@code PrescriptionList} with sample data.
 */
public class SampleDataUtil {
    public static Prescription[] getSamplePrescriptions() {
        return new Prescription[] {
            new Prescription(new Name("Aspirin"), new Dosage("1"), new Frequency("Daily"),
                            new Date("01/08/2023"), new Date("25/12/2023"), new Date("01/12/2024"),
                            new Stock("100"), new Note("Take before food"), getDrugSet("Paracetamol")),
            new Prescription(new Name("Propranolol"), new Dosage("4"), new Frequency("Daily"),
                            new Date("01/08/2023"), new Date("20/01/2024"), new Date("02/07/2026"),
                            new Stock("500"), new Note("Take after food"), getDrugSet()),
            new Prescription(new Name("Ergotamine Tartrate"), new Dosage("1"), new Frequency("Weekly"),
                            new Date("01/10/2023"), new Date("10/11/2025"), new Date("02/11/2026"),
                            new Stock("50"), new Note("May cause drowsiness"), getDrugSet()),
            new Prescription(new Name("Naproxen Sodium"), new Dosage("2"), new Frequency("Weekly"),
                            new Date("01/10/2023"), new Date("01/11/2023"), new Date("02/11/2026"),
                            new Stock("50"), new Note("Take before food"), getDrugSet()),
            new Prescription(new Name("Zomig Rapimelt"), new Dosage("1"), new Frequency("Weekly"),
                            new Date("01/10/2023"), new Date("01/11/2023"), new Date("12/06/2026"),
                            new Stock("20"), new Note("Allow to dissolve under tongue"), getDrugSet()),
            new Prescription(new Name("Omeprazole"), new Dosage("2"), new Frequency("Daily"),
                            new Date("01/10/2023"), new Date("01/11/2024"), new Date("02/11/2026"),
                            new Stock("200"), new Note("Take before food"), getDrugSet()),
        };
    }

    public static ReadOnlyPrescriptionList getSamplePrescriptionList() {
        PrescriptionList samplePrescriptionList = new PrescriptionList();
        for (Prescription samplePrescription : getSamplePrescriptions()) {
            samplePrescriptionList.addPrescription(samplePrescription);
        }
        return samplePrescriptionList;
    }

    /**
     * Returns a set containing the list of strings given.
     */
    public static Set<Name> getDrugSet(String... strings) {
        return Arrays.stream(strings)
                .map(Name::new)
                .collect(Collectors.toSet());
    }


    public static Prescription[] getSampleCompletedPrescriptions() {
        return new Prescription[] {
            new Prescription(new Name("Aspirin"), new Dosage("1"), new Frequency("Daily"),
                            new Date("01/08/2022"), new Date("01/01/2023"), new Date("01/12/2024"),
                            new Stock("100"), new Note("Take before food"), getDrugSet("Paracetamol")),
            new Prescription(new Name("Propranolol"), new Dosage("4"), new Frequency("Daily"),
                            new Date("01/08/2022"), new Date("01/01/2023"), new Date("02/07/2026"),
                            new Stock("500"), new Note("Take after food"), getDrugSet()),
            new Prescription(new Name("Ergotamine Tartrate"), new Dosage("1"), new Frequency("Weekly"),
                            new Date("01/10/2022"), new Date("01/01/2023"), new Date("02/11/2026"),
                            new Stock("50"), new Note("May cause drowsiness"), getDrugSet()),
            new Prescription(new Name("Naproxen Sodium"), new Dosage("2"), new Frequency("Weekly"),
                            new Date("01/10/2022"), new Date("01/01/2023"), new Date("02/11/2026"),
                            new Stock("50"), new Note("Take before food"), getDrugSet()),
            new Prescription(new Name("Zomig Rapimelt"), new Dosage("1"), new Frequency("Weekly"),
                            new Date("01/10/2022"), new Date("01/01/2023"), new Date("12/06/2026"),
                            new Stock("20"), new Note("Allow to dissolve under tongue"), getDrugSet()),
            new Prescription(new Name("Omeprazole"), new Dosage("2"), new Frequency("Daily"),
                            new Date("01/10/2022"), new Date("01/01/2023"), new Date("02/11/2026"),
                            new Stock("200"), new Note("Take before food"), getDrugSet()),
        };
    }

    public static ReadOnlyPrescriptionList getSampleCompletedPrescriptionList() {
        PrescriptionList sampleCompletedPrescriptionList = new PrescriptionList();
        for (Prescription sampleCompletedPrescription : getSampleCompletedPrescriptions()) {
            sampleCompletedPrescriptionList.addPrescription(sampleCompletedPrescription);
        }
        return sampleCompletedPrescriptionList;
    }
}
