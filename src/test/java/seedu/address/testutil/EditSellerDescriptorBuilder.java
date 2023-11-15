package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditSellerCommand.EditSellerDescriptor;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.model.tag.Tag;

//Solution below adapted by https://github.com/nus-cs2103-AY2324S1/tp
/**
 * A utility class to help with building EditSellerDescriptor objects.
 */
public class EditSellerDescriptorBuilder {

    private EditSellerDescriptor descriptor;

    public EditSellerDescriptorBuilder() {
        descriptor = new EditSellerDescriptor();
    }

    public EditSellerDescriptorBuilder(EditSellerDescriptor descriptor) {
        this.descriptor = new EditSellerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditSellerDescriptor} with fields containing {@code person}'s details
     */
    public EditSellerDescriptorBuilder(Seller seller) {
        descriptor = new EditSellerDescriptor();
        descriptor.setName(seller.getName());
        descriptor.setPhone(seller.getPhone());
        descriptor.setEmail(seller.getEmail());
        descriptor.setAddress(seller.getAddress());
        descriptor.setSellingAddress(seller.getSellingAddress());
        descriptor.setHouseInfo(seller.getHouseInfo());
        descriptor.setTags(seller.getTags());
        descriptor.setPriority(seller.getPriority());
    }

    /**
     * Sets the {@code Name} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }
    /**
     * Sets the {@code SellingAddress} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withSellingAddress(String sellingAddress) {
        descriptor.setAddress(new Address(sellingAddress));
        return this;
    }

    /**
     * Sets the {@code HouseInfo} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withHouseInfo(String houseInfo) {
        descriptor.setHouseInfo(new HouseInfo(houseInfo));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditSellerDescriptor}
     * that we are building.
     */
    public EditSellerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    public EditSellerDescriptor build() {
        return descriptor;
    }
}

