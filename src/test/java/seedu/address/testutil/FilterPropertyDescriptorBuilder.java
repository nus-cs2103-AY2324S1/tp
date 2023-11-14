package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterPropertyCommand.FilterPropertyDescriptor;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building FilterPropertyDescriptor objects.
 */
public class FilterPropertyDescriptorBuilder {

    private FilterPropertyDescriptor descriptor;

    public FilterPropertyDescriptorBuilder() {
        descriptor = new FilterPropertyDescriptor();
    }

    public FilterPropertyDescriptorBuilder(FilterPropertyDescriptor descriptor) {
        this.descriptor = new FilterPropertyDescriptor(descriptor);
    }

    /**
     * Returns an {@code FilterPropertyDescriptorBuilder} with fields containing {@code property}'s details
     */
    public FilterPropertyDescriptorBuilder(Property property) {
        descriptor.setPrice(property.getPrice());
        descriptor.setTags(property.getTags());
    }

    /**
     * Sets the {@code Price} of the {@code EditPropertyDescriptor} that we are building.
     */
    public FilterPropertyDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPropertyDescriptor}
     * that we are building.
     */
    public FilterPropertyDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public FilterPropertyDescriptor build() {
        return descriptor;
    }
}
