package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.musician.Email;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.Name;
import seedu.address.model.musician.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Musician objects.
 */
public class MusicianBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private Name name;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;
    private Set<Tag> instruments;
    private Set<Tag> genres;

    /**
     * Creates a {@code MusicianBuilder} with the default details.
     */
    public MusicianBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
        instruments = new HashSet<>();
        genres = new HashSet<>();
    }

    /**
     * Initializes the MusicianBuilder with the data of {@code musicianToCopy}.
     */
    public MusicianBuilder(Musician musicianToCopy) {
        name = musicianToCopy.getName();
        phone = musicianToCopy.getPhone();
        email = musicianToCopy.getEmail();
        tags = new HashSet<>(musicianToCopy.getTags());
        instruments = new HashSet<>(musicianToCopy.getInstruments());
        genres = new HashSet<>(musicianToCopy.getGenres());
    }

    /**
     * Sets the {@code Name} of the {@code Musician} that we are building.
     */
    public MusicianBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Musician} that we are building.
     */
    public MusicianBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code instruments} into a {@code Set<Tag>} and set it to the {@code Musician} that we are building.
     */
    public MusicianBuilder withInstruments(String... instruments) {
        this.instruments = SampleDataUtil.getTagSet(instruments);
        return this;
    }

    /**
     * Parses the {@code genres} into a {@code Set<Tag>} and set it to the {@code Musician} that we are building.
     */
    public MusicianBuilder withGenres(String... genres) {
        this.genres = SampleDataUtil.getTagSet(genres);
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code Musician} that we are building.
     */
    public MusicianBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Musician} that we are building.
     */
    public MusicianBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Musician build() {
        return new Musician(name, phone, email, tags, instruments, genres);
    }

}
