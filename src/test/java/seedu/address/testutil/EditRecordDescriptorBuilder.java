package seedu.address.testutil;

import java.util.List;

import seedu.address.logic.commands.EditRecordCommand.EditRecordDescriptor;
import seedu.address.model.record.Condition;
import seedu.address.model.record.Medication;
import seedu.address.model.record.Record;
import seedu.address.model.shared.DateTime;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditRecordDescriptor objects.
 */
public class EditRecordDescriptorBuilder {

    private EditRecordDescriptor descriptor;

    public EditRecordDescriptorBuilder() {
        descriptor = new EditRecordDescriptor();
    }

    public EditRecordDescriptorBuilder(EditRecordDescriptor descriptor) {
        this.descriptor = new EditRecordDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecordDescriptor} with fields containing
     * {@code record}'s details
     */
    public EditRecordDescriptorBuilder(Record record) {
        descriptor = new EditRecordDescriptor();
        descriptor.setDateTime(record.getDateTime());
        descriptor.setConditions(record.getConditions());
        descriptor.setMedications(record.getMedications());
    }

    /**
     * Sets the {@code DateTime} of the {@code EditRecordDescriptor} that we are
     * building.
     */
    public EditRecordDescriptorBuilder withDateTime(String dateTime) {
        DateTime newDateTime = new DateTime(dateTime);
        descriptor.setDateTime(newDateTime);
        return this;
    }

    /**
     * Sets the {@code patientIndex} of the {@code EditRecordDescriptor} that we are
     * building.
     */
    public EditRecordDescriptorBuilder withPatientIndex(Integer patientIndex) {
        descriptor.setPatientIndex(patientIndex);
        return this;
    }

    /**
     * Parses the {@code conditions} into a {@code ArrayList<Condition>} and set it
     * to the {@code EditRecordDescriptor}
     * that we are building.
     */
    public EditRecordDescriptorBuilder withConditions(String... conditions) {
        List<Condition> conditionList = SampleDataUtil.getConditionList(conditions);
        descriptor.setConditions(conditionList);
        return this;
    }

    /**
     * Parses the {@code medications} into a {@code ArrayList<Medication>}
     * and set it to the {@code EditRecordDescriptor} that we are building
     */
    public EditRecordDescriptorBuilder withMedications(String... medications) {
        List<Medication> medicationList = SampleDataUtil.getMedicationList(medications);
        descriptor.setMedications(medicationList);
        return this;
    }

    public EditRecordDescriptor build() {
        return descriptor;
    }
}
