package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.property.Price;
import seedu.address.model.property.PropAddress;
import seedu.address.model.property.PropName;
import seedu.address.model.property.PropPhone;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPropertyDescriptor objects.
 */
public class EditPropertyDescriptorBuilder {

    private EditPropertyDescriptor descriptor;

    public EditPropertyDescriptorBuilder() {
        descriptor = new EditPropertyDescriptor();
    }

    public EditPropertyDescriptorBuilder(EditPropertyDescriptor descriptor) {
        this.descriptor = new EditPropertyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditpropertyDescriptor} with fields containing {@code property}'s details
     */
    public EditPropertyDescriptorBuilder(Property property) {
        descriptor = new EditPropertyDescriptor();
        descriptor.setName(property.getName());
        descriptor.setPhone(property.getPhone());
        descriptor.setAddress(property.getAddress());
        descriptor.setPrice(property.getPrice());
        descriptor.setTags(property.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditpropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withName(String name) {
        descriptor.setName(new PropName(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditpropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new PropPhone(phone));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditpropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new PropAddress(address));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditpropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditpropertyDescriptor}
     * that we are building.
     */
    public EditPropertyDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPropertyDescriptor build() {
        return descriptor;
    }
}
