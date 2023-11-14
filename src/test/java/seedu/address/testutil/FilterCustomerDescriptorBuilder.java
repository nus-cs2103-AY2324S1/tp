package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCustomerCommand.FilterCustomerDescriptor;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.Customer;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building FilterCustomerDescriptor objects.
 */
public class FilterCustomerDescriptorBuilder {

    private FilterCustomerDescriptor descriptor;

    public FilterCustomerDescriptorBuilder() {
        descriptor = new FilterCustomerDescriptor();
    }

    public FilterCustomerDescriptorBuilder(FilterCustomerDescriptor descriptor) {
        this.descriptor = new FilterCustomerDescriptor(descriptor);
    }

    /**
     * Returns an {@code FilterCustomerDescriptorBuilder} with fields containing {@code customer}'s details
     */
    public FilterCustomerDescriptorBuilder(Customer customer) {
        descriptor.setBudget(customer.getBudget());
        descriptor.setTags(customer.getTags());
    }

    /**
     * Sets the {@code Budget} of the {@code EditCustomerDescriptor} that we are building.
     */
    public FilterCustomerDescriptorBuilder withBudget(String budget) {
        descriptor.setBudget(new Budget(budget));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditCustomerDescriptor}
     * that we are building.
     */
    public FilterCustomerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public FilterCustomerDescriptor build() {
        return descriptor;
    }
}
