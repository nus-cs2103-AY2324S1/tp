package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditSpecialistDescriptor;
import seedu.address.model.person.Specialist;

/**
 * A utility class to help with building EditSpecialistDescriptor objects.
 */
public class EditSpecialistDescriptorBuilder extends EditPersonDescriptorBuilder {

    public EditSpecialistDescriptorBuilder() {
        setDescriptor(new EditSpecialistDescriptor());
    }

    public EditSpecialistDescriptorBuilder(EditSpecialistDescriptor editSpecialistDescriptor) {
        setDescriptor(new EditSpecialistDescriptor(editSpecialistDescriptor));
    }

    /**
     * Returns an {@code EditSpecialistDescriptor} with fields containing {@code person}'s details
     */
    public EditSpecialistDescriptorBuilder(Specialist patient) {
        EditSpecialistDescriptor editSpecialistDescriptor = new EditSpecialistDescriptor();
        setDescriptor(editSpecialistDescriptor);
        editPersonDescriptorSetFields(patient);
    }

    @Override
    public EditSpecialistDescriptor build() {
        return (EditSpecialistDescriptor) getDescriptor();
    }
}
