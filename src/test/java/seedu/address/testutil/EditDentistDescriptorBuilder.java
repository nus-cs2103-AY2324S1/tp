package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditDentistCommand.EditDentistDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.dentist.Specialization;
import seedu.address.model.person.dentist.Yoe;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditDentistDescriptor objects.
 */
public class EditDentistDescriptorBuilder {

    private EditDentistDescriptor descriptor;

    public EditDentistDescriptorBuilder() {
        descriptor = new EditDentistDescriptor();
    }

    public EditDentistDescriptorBuilder(EditDentistDescriptor descriptor) {
        this.descriptor = new EditDentistDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDentistDescriptor} with fields containing {@code dentist}'s details
     */
    public EditDentistDescriptorBuilder(Dentist dentist) {
        descriptor = new EditDentistDescriptor();
        descriptor.setName(dentist.getName());
        descriptor.setPhone(dentist.getPhone());
        descriptor.setEmail(dentist.getEmail());
        descriptor.setAddress(dentist.getAddress());
        descriptor.setSpecialization(dentist.getSpecialization());
        descriptor.setYoe(dentist.getYoe());
        descriptor.setDentistId(dentist.getId());
        descriptor.setTags(dentist.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditDentistDescriptor} that we are building.
     */
    public EditDentistDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditDentistDescriptor} that we are building.
     */
    public EditDentistDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditDentistDescriptor} that we are building.
     */
    public EditDentistDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditDentistDescriptor} that we are building.
     */
    public EditDentistDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Specialization} of the {@code EditDentistDescriptor} that we are building.
     */
    public EditDentistDescriptorBuilder withSpecialization(String specialization) {
        descriptor.setSpecialization(new Specialization(specialization));
        return this;
    }

    /**
     * Sets the {@code Yoe} (Years of Experience) of the {@code EditDentistDescriptor} that we are building.
     */
    public EditDentistDescriptorBuilder withYoe(String yoe) {
        descriptor.setYoe(new Yoe(yoe));
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code EditDentistDescriptor} that we are building.
     */
    public EditDentistDescriptorBuilder withId(String id) {
        descriptor.setDentistId(Long.parseLong(id));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditDentistDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditDentistDescriptor build() {
        return descriptor;
    }
}
