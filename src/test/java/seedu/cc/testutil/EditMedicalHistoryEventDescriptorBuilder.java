package seedu.cc.testutil;

import seedu.cc.logic.commands.medhisteventcommands.EditMedicalHistoryEventCommand.EditMedicalHistoryEventDescriptor;
import seedu.cc.model.medicalhistory.MedicalCondition;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.medicalhistory.Treatment;
import seedu.cc.model.util.Date;

/**
 * A utility class to help with building EditMedicalHistoryEventDescriptor objects.
 */
public class EditMedicalHistoryEventDescriptorBuilder {

    private final EditMedicalHistoryEventDescriptor descriptor;

    public EditMedicalHistoryEventDescriptorBuilder() {
        descriptor = new EditMedicalHistoryEventDescriptor();
    }

    public EditMedicalHistoryEventDescriptorBuilder(EditMedicalHistoryEventDescriptor descriptor) {
        this.descriptor = new EditMedicalHistoryEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMedicalHistoryEventDescriptor} with fields containing {@code event}'s details
     */
    public EditMedicalHistoryEventDescriptorBuilder(MedicalHistoryEvent event) {
        descriptor = new EditMedicalHistoryEventDescriptor();
        descriptor.setTreatment(event.getTreatment());
        descriptor.setDate(event.getDate());
        descriptor.setMedicalCondition(event.getMedicalCondition());
    }

    /**
     * Sets the {@code Treatment} of the {@code EditMedicalHistoryEventDescriptor} that we are building.
     */
    public EditMedicalHistoryEventDescriptorBuilder withTreatment(String treatment) {
        descriptor.setTreatment(new Treatment(treatment));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditMedicalHistoryEventDescriptor} that we are building.
     */
    public EditMedicalHistoryEventDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code MedicalCondition} of the {@code EditMedicalHistoryEventDescriptor} that we are building.
     */
    public EditMedicalHistoryEventDescriptorBuilder withMedicalCondition(String medicalCondition) {
        descriptor.setMedicalCondition(new MedicalCondition(medicalCondition));
        return this;
    }

    public EditMedicalHistoryEventDescriptor build() {
        return descriptor;
    }
}
