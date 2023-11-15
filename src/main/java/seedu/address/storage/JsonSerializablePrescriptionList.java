package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PrescriptionList;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.prescription.Prescription;

/**
 * An Immutable PrescriptionList that is serializable to JSON format.
 */
@JsonRootName(value = "prescriptionlist")
class JsonSerializablePrescriptionList {

    public static final String MESSAGE_DUPLICATE_PRESCRIPTION = "Prescriptions list "
        + "contains duplicate prescription(s).";

    private final List<JsonAdaptedPrescription> prescriptions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePrescriptionList} with the given prescriptions.
     */
    @JsonCreator
    public JsonSerializablePrescriptionList(
            @JsonProperty("prescriptions") List<JsonAdaptedPrescription> prescriptions) {
        this.prescriptions.addAll(prescriptions);
    }

    /**
     * Converts a given {@code ReadOnlyPrescriptionList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePrescriptionList}.
     */
    public JsonSerializablePrescriptionList(ReadOnlyPrescriptionList source) {
        prescriptions.addAll(source.getPrescriptionList().stream().map(
            JsonAdaptedPrescription::new).collect(Collectors.toList()));
    }

    /**
     * Converts this prescription list into the model's {@code PrescriptionList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PrescriptionList toModelType() throws IllegalValueException {
        PrescriptionList prescriptionList = new PrescriptionList();
        for (JsonAdaptedPrescription jsonAdaptedPrescription : prescriptions) {
            Prescription prescription = jsonAdaptedPrescription.toModelType();
            if (prescriptionList.hasPrescription(prescription)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRESCRIPTION);
            }
            prescriptionList.addPrescription(prescription);
        }
        return prescriptionList;
    }

}
