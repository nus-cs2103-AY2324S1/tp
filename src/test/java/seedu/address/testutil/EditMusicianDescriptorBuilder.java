package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.musician.EditCommand.EditMusicianDescriptor;
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
        descriptor.setTags(musician.getTags());
        descriptor.setInstruments(musician.getInstruments());
        descriptor.setGenres(musician.getGenres());
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditMusicianDescriptor}
     * that we are building.
     */
    public EditMusicianDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code instruments} into a {@code Set<Tag>} and set it to the {@code EditMusicianDescriptor}
     * that we are building.
     */
    public EditMusicianDescriptorBuilder withInstruments(String... instruments) {
        Set<Tag> instrumentSet = Stream.of(instruments).map(Tag::new).collect(Collectors.toSet());
        descriptor.setInstruments(instrumentSet);
        return this;
    }

    /**
     * Parses the {@code genres} into a {@code Set<Tag>} and set it to the {@code EditMusicianDescriptor}
     * that we are building.
     */
    public EditMusicianDescriptorBuilder withGenres(String... genres) {
        Set<Tag> genreSet = Stream.of(genres).map(Tag::new).collect(Collectors.toSet());
        descriptor.setGenres(genreSet);
        return this;
    }

    public EditMusicianDescriptor build() {
        return descriptor;
    }
}
