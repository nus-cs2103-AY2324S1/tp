package seedu.address.testutil;

import seedu.address.logic.commands.EditAnimalCommand.EditAnimalDescriptor;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;

/**
 * A utility class to help with building EditAnimalDescriptor objects.
 */
public class EditAnimalDescriptorBuilder {

    private EditAnimalDescriptor descriptor;

    public EditAnimalDescriptorBuilder() {
        descriptor = new EditAnimalDescriptor();
    }

    public EditAnimalDescriptorBuilder(EditAnimalDescriptor descriptor) {
        this.descriptor = new EditAnimalDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAnimalDescriptor} with fields containing {@code animal}'s details
     */
    public EditAnimalDescriptorBuilder(Animal animal) {
        descriptor = new EditAnimalDescriptor();
        descriptor.setName(animal.getName());
        descriptor.setDateOfBirth(animal.getDateOfBirth());
        descriptor.setAdmissionDate(animal.getAdmissionDate());
        descriptor.setSpecies(animal.getSpecies());
        descriptor.setBreed(animal.getBreed());
        descriptor.setSex(animal.getSex());
    }

    /**
     * Sets the {@code Name} of the {@code EditAnimalDescriptor} that we are building.
     */
    public EditAnimalDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code EditAnimalDescriptor} that we are building.
     */
    public EditAnimalDescriptorBuilder withDateOfBirth(String dateOfBirth) {
        descriptor.setDateOfBirth(new DateOfBirth(dateOfBirth));
        return this;
    }

    /**
     * Sets the {@code AdmissionDate} of the {@code EditAnimalDescriptor} that we are building.
     */
    public EditAnimalDescriptorBuilder withAdmissionDate(String admissionDate) {
        descriptor.setAdmissionDate(new AdmissionDate(admissionDate));
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code EditAnimalDescriptor} that we are building.
     */
    public EditAnimalDescriptorBuilder withSpecies(String species) {
        descriptor.setSpecies(new Species(species));
        return this;
    }

    /**
     * Sets the {@code Breed} of the {@code EditAnimalDescriptor} that we are building.
     */
    public EditAnimalDescriptorBuilder withBreed(String breed) {
        descriptor.setBreed(new Breed(breed));
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code EditAnimalDescriptor} that we are building.
     */
    public EditAnimalDescriptorBuilder withSex(String sex) {
        descriptor.setSex(new Sex(sex));
        return this;
    }



    public EditAnimalDescriptor build() {
        return descriptor;
    }
}
