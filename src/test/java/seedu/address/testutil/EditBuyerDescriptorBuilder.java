package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.tag.Tag;

//Solution below adapted by https://github.com/nus-cs2103-AY2324S1/tp
/**
 * A utility class to help with building EditBuyerDescriptor objects.
 */
public class EditBuyerDescriptorBuilder {

    private EditBuyerDescriptor descriptor;

    public EditBuyerDescriptorBuilder() {
        descriptor = new EditBuyerDescriptor();
    }

    public EditBuyerDescriptorBuilder(EditBuyerDescriptor descriptor) {
        this.descriptor = new EditBuyerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBuyerDescriptor} with fields containing {@code person}'s details
     */
    public EditBuyerDescriptorBuilder(Buyer buyer) {
        descriptor = new EditBuyerDescriptor();
        descriptor.setName(buyer.getName());
        descriptor.setPhone(buyer.getPhone());
        descriptor.setEmail(buyer.getEmail());
        descriptor.setAddress(buyer.getAddress());
        descriptor.setHouseInfo(buyer.getHouseInfo());
        descriptor.setTags(buyer.getTags());
        descriptor.setPriority(buyer.getPriority());
    }

    /**
     * Sets the {@code Name} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code HouseInfo} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withHouseInfo(String houseInfo) {
        descriptor.setHouseInfo(new HouseInfo(houseInfo));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditBuyerDescriptor}
     * that we are building.
     */
    public EditBuyerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    public EditBuyerDescriptor build() {
        return descriptor;
    }
}
