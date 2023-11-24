package wedlog.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import wedlog.address.logic.commands.VendorEditCommand.EditVendorDescriptor;
import wedlog.address.model.person.Address;
import wedlog.address.model.person.Email;
import wedlog.address.model.person.Name;
import wedlog.address.model.person.Phone;
import wedlog.address.model.person.Vendor;
import wedlog.address.model.tag.Tag;

/**
 * A utility class to help with building EditVendorDescriptor objects.
 */
public class EditVendorDescriptorBuilder {

    private EditVendorDescriptor descriptor;

    public EditVendorDescriptorBuilder() {
        descriptor = new EditVendorDescriptor();
    }

    public EditVendorDescriptorBuilder(EditVendorDescriptor descriptor) {
        this.descriptor = new EditVendorDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditVendorDescriptor} with fields containing {@code vendor}'s details
     */
    public EditVendorDescriptorBuilder(Vendor vendor) {
        descriptor = new EditVendorDescriptor();
        descriptor.setName(vendor.getName());
        descriptor.setPhone(vendor.getPhone().orElse(null));
        descriptor.setEmail(vendor.getEmail().orElse(null));
        descriptor.setAddress(vendor.getAddress().orElse(null));
        descriptor.setTags(vendor.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditVendorDescriptor}
     * that we are building.
     */
    public EditVendorDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withoutPhone() {
        descriptor.setPhone(null);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withoutEmail() {
        descriptor.setEmail(null);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditVendorDescriptor} that we are building.
     */
    public EditVendorDescriptorBuilder withoutAddress() {
        descriptor.setAddress(null);
        return this;
    }

    public EditVendorDescriptor build() {
        return descriptor;
    }
}
