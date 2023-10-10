package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditSpecialistDescriptor;
import seedu.address.model.person.Specialist;
import seedu.address.model.person.Specialty;

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
    public EditSpecialistDescriptorBuilder(Specialist specialist) {
        EditSpecialistDescriptor editSpecialistDescriptor = new EditSpecialistDescriptor();
        setDescriptor(editSpecialistDescriptor);
        editPersonDescriptorSetFields(specialist);
    }


    /**
     * Sets the {@code Specialty} of the {@code EditSpecialtyDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSpecialty(String specialty) {
        EditSpecialistDescriptor editSpecialistDescriptor = (EditSpecialistDescriptor) super.getDescriptor();
        editSpecialistDescriptor.setSpecialty(new Specialty(specialty));
        return this;
    }

    @Override
    public EditSpecialistDescriptor build() {
        return (EditSpecialistDescriptor) getDescriptor();
    }
}
