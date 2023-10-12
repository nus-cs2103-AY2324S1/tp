package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditMusicianDescriptor;
import seedu.address.model.musician.Address;
import seedu.address.model.musician.Email;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.Name;
import seedu.address.model.musician.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditMusicianDescriptor objects.
 */
public class EditMusicianDescriptorBuilder {

    private EditMusicianDescriptor descriptor;

    public EditMusicianDescriptorBuilder() {
        descriptor = new EditMusicianDescriptor();
    }

    public EditMusicianDescriptorBuilder(EditMusicianDescriptor descriptor) {
        this.descriptor = new EditMusicianDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMusicianDescriptor} with fields containing {@code musician}'s details
     */
    public EditMusicianDescriptorBuilder(Musician musician) {
        descriptor = new EditMusicianDescriptor();
        descriptor.setName(musician.getName());
        descriptor.setPhone(musician.getPhone());
        descriptor.setEmail(musician.getEmail());
        descriptor.setAddress(musician.getAddress());
        descriptor.setTags(musician.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditMusicianDescriptor} that we are building.
     */
    public EditMusicianDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditMusicianDescriptor} that we are building.
     */
    public EditMusicianDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditMusicianDescriptor} that we are building.
     */
    public EditMusicianDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditMusicianDescriptor} that we are building.
     */
    public EditMusicianDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditMusicianDescriptor}
     * that we are building.
     */
    public EditMusicianDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditMusicianDescriptor build() {
        return descriptor;
    }
}
