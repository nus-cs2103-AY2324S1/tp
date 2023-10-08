package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
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

    @Override
    public EditPatientDescriptor build() {
        return (EditPatientDescriptor) getDescriptor();
    }
}
