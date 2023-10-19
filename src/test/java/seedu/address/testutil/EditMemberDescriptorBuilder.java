package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditMemberDescriptor;
import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Gender;
import seedu.address.model.member.Member;
import seedu.address.model.member.Phone;
import seedu.address.model.shared.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditMemberDescriptor objects.
 */
public class EditMemberDescriptorBuilder {

    private EditCommand.EditMemberDescriptor descriptor;

    public EditMemberDescriptorBuilder() {
        descriptor = new EditMemberDescriptor();
    }

    public EditMemberDescriptorBuilder(EditCommand.EditMemberDescriptor descriptor) {
        this.descriptor = new EditCommand.EditMemberDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMemberDescriptor} with fields containing {@code member}'s details
     */
    public EditMemberDescriptorBuilder(Member member) {
        descriptor = new EditCommand.EditMemberDescriptor();
        descriptor.setName(member.getName());
        descriptor.setGender(member.getGender());
        descriptor.setPhone(member.getPhone());
        descriptor.setEmail(member.getEmail());
        descriptor.setAddress(member.getAddress());
        descriptor.setTags(member.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditMemberDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditMemberDescriptor}
     * that we are building.
     */
    public EditMemberDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditMemberDescriptor build() {
        return descriptor;
    }
}
