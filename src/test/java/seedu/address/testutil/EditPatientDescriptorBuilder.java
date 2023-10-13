package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Patient;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder extends EditPersonDescriptorBuilder {

    public EditPatientDescriptorBuilder() {
        setDescriptor(new EditPatientDescriptor());
    }

    public EditPatientDescriptorBuilder(EditPatientDescriptor editPatientDescriptor) {
        setDescriptor(new EditPatientDescriptor(editPatientDescriptor));
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code person}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        setDescriptor(editPatientDescriptor);
        editPersonDescriptorSetFields(patient);
    }

    /**
     * Sets the {@code MedicalHistory} of the {@code EditMedicalHistoryDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMedicalHistory(String medicalHistory) {
        EditCommand.EditPatientDescriptor editPatientDescriptor =
                (EditCommand.EditPatientDescriptor) super.getDescriptor();
        editPatientDescriptor.setMedicalHistory(new MedicalHistory(medicalHistory));
        return this;
    }

    @Override
    public EditPatientDescriptor build() {
        return (EditPatientDescriptor) getDescriptor();
    }
}
