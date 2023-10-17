package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.band.Band;
import seedu.address.model.band.BandName;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Band objects.
 */
public class BandBuilder {

    public static final String DEFAULT_NAME = "Ace Jazz";

    private BandName name;
    private Set<Tag> genres;
    private Set<Musician> musicians;

    /**
     * Creates a {@code BandBuilder} with the default details.
     */
    public BandBuilder() {
        name = new BandName(DEFAULT_NAME);
        genres = new HashSet<>();
        musicians = new HashSet<>();
    }

    /**
     * Initializes the BandBuilder with the data of {@code bandToCopy}.
     */
    public BandBuilder(Band bandToCopy) {
        name = bandToCopy.getName();
        genres = new HashSet<>(bandToCopy.getGenres());
        musicians = new HashSet<>(bandToCopy.getMusicians());
    }

    /**
     * Sets the {@code Name} of the {@code Band} that we are building.
     */
    public BandBuilder withName(String name) {
        this.name = new BandName(name);
        return this;
    }

    /**
     * Parses the {@code genres} into a {@code Set<Tag>} and set it to the {@code Band} that we are building.
     */
    public BandBuilder withGenres(String... genres) {
        this.genres = SampleDataUtil.getTagSet(genres);
        return this;
    }

    /**
     * Sets the {@code musicians} of the {@code Band} that we are building.
     */
    public BandBuilder withMusicians(Musician... musicians) {
        this.musicians = SampleDataUtil.getMusicianSet(musicians);
        return this;
    }

    public Band build() {
        return new Band(name, genres, musicians);
    }

}
