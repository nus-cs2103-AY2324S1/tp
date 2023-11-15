package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPrescriptionDescriptor;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;

/**
 * A utility class to help with building EditPrescriptionDescriptor objects.
 */
public class EditPrescriptionDescriptorBuilder {

    private EditPrescriptionDescriptor descriptor;

    public EditPrescriptionDescriptorBuilder() {
        descriptor = new EditPrescriptionDescriptor();
    }

    public EditPrescriptionDescriptorBuilder(EditPrescriptionDescriptor descriptor) {
        this.descriptor = new EditPrescriptionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPrescriptionDescriptor} with fields containing {@code prescription}'s details
     */
    public EditPrescriptionDescriptorBuilder(Prescription prescription) {
        descriptor = new EditPrescriptionDescriptor();
        descriptor.setName(prescription.getName());

        if (prescription.getDosage().isPresent()) {
            descriptor.setDosage(prescription.getDosage().get());
        }

        if (prescription.getFrequency().isPresent()) {
            descriptor.setFrequency(prescription.getFrequency().get());
        }

        descriptor.setStartDate(prescription.getStartDate());

        if (prescription.getEndDate().isPresent()) {
            descriptor.setEndDate(prescription.getEndDate().get());
        }

        if (prescription.getExpiryDate().isPresent()) {
            descriptor.setExpiryDate(prescription.getExpiryDate().get());
        }

        if (prescription.getTotalStock().isPresent()) {
            descriptor.setTotalStock(prescription.getTotalStock().get());
        }
        descriptor.setConsumptionCount(prescription.getConsumptionCount());
        descriptor.setIsCompleted(prescription.getIsCompleted());
        if (prescription.getNote().isPresent()) {
            descriptor.setNote(prescription.getNote().get());
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Dosage} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withDosage(String dosage) {
        descriptor.setDosage(new Dosage(dosage));
        return this;
    }

    /**
     * Sets the {@code Frequency} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withFrequency(String frequency) {
        descriptor.setFrequency(new Frequency(frequency));
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new Date(startDate));
        return this;
    }

    /**
     * Sets the {@code EndDate} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withEndDate(String endDate) {
        descriptor.setEndDate(new Date(endDate));
        return this;
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withExpiryDate(String expiryDate) {
        descriptor.setExpiryDate(new Date(expiryDate));
        return this;
    }

    /**
     * Sets the {@code TotalStock} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withTotalStock(String totalStock) {
        descriptor.setTotalStock(new Stock(totalStock));
        return this;
    }

    /**
     * Sets the {@code ConsumptionCount} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withConsumptionCount(String consumptionCount) {
        descriptor.setConsumptionCount(new ConsumptionCount(consumptionCount));
        return this;
    }

    /**
     * Sets the {@code isCompleted} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withIsCompleted(Boolean isCompleted) {
        descriptor.setIsCompleted(isCompleted);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withNote(String note) {
        descriptor.setNote(new Note(note));
        return this;
    }

    public EditPrescriptionDescriptor build() {
        return descriptor;
    }
}
