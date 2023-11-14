package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.musician.Email;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.Name;
import seedu.address.model.musician.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Musician}.
 */
class JsonAdaptedMusician {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Musician's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedInstrument> instruments = new ArrayList<>();
    private final List<JsonAdaptedGenre> genres = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMusician} with the given musician details.
     */
    @JsonCreator
    public JsonAdaptedMusician(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                               @JsonProperty("email") String email,
                               @JsonProperty("tags") List<JsonAdaptedTag> tags,
                               @JsonProperty("instruments") List<JsonAdaptedInstrument> instruments,
                               @JsonProperty("genres") List<JsonAdaptedGenre> genres) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (instruments != null) {
            this.instruments.addAll(instruments);
        }
        if (genres != null) {
            this.genres.addAll(genres);
        }
    }

    /**
     * Converts a given {@code Musician} into this class for Jackson use.
     */
    public JsonAdaptedMusician(Musician source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        instruments.addAll(source.getInstruments().stream()
                .map(JsonAdaptedInstrument::new)
                .collect(Collectors.toList()));
        genres.addAll(source.getGenres().stream()
                .map(JsonAdaptedGenre::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted musician object into the model's {@code Musician} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted musician.
     */
    public Musician toModelType() throws IllegalValueException {
        final List<Tag> musicianTags = new ArrayList<>();
        final List<Tag> musicianInstruments = new ArrayList<>();
        final List<Tag> musicianGenres = new ArrayList<>();

        for (JsonAdaptedTag tag : tags) {
            musicianTags.add(tag.toModelType());
        }
        for (JsonAdaptedTag instrument : instruments) {
            musicianInstruments.add(instrument.toModelType());
        }
        for (JsonAdaptedTag genre : genres) {
            musicianGenres.add(genre.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        final Set<Tag> modelTags = new HashSet<>(musicianTags);
        final Set<Tag> modelInstruments = new HashSet<>(musicianInstruments);
        final Set<Tag> modelGenres = new HashSet<>(musicianGenres);
        return new Musician(modelName, modelPhone, modelEmail, modelTags, modelInstruments, modelGenres);
    }

}
