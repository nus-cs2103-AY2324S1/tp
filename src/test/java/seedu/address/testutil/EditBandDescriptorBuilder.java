package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.band.EditBandCommand.EditBandDescriptor;
import seedu.address.model.band.Band;
import seedu.address.model.band.BandName;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditBandDescriptor objects.
 */
public class EditBandDescriptorBuilder {
    private EditBandDescriptor descriptor;

    public EditBandDescriptorBuilder() {
        descriptor = new EditBandDescriptor();
    }

    public EditBandDescriptorBuilder(EditBandDescriptor descriptor) {
        this.descriptor = new EditBandDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBandDescriptor} with fields containing {@code band}'s details
     */
    public EditBandDescriptorBuilder(Band band) {
        descriptor = new EditBandDescriptor();
        descriptor.setBandName(band.getName());
        descriptor.setGenres(band.getGenres());
    }

    /**
     * Sets the {@code Name} of the {@code EditBandDescriptor} that we are building.
     */
    public EditBandDescriptorBuilder withName(String name) {
        descriptor.setBandName(new BandName(name));
        return this;
    }

    /**
     * Parses the {@code genres} into a {@code Set<Tag>} and set it to the {@code EditMusicianDescriptor}
     * that we are building.
     */
    public EditBandDescriptorBuilder withGenres(String... genres) {
        Set<Tag> genreSet = Stream.of(genres).map(Tag::new).collect(Collectors.toSet());
        descriptor.setGenres(genreSet);
        return this;
    }

    public EditBandDescriptor build() {
        return descriptor;
    }
}
