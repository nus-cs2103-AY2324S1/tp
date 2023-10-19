package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.band.Band;
import seedu.address.model.band.BandName;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.Name;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Band}.
 */
public class JsonAdaptedBand {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Band's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedMusician> musicians = new ArrayList<>();
    private final List<JsonAdaptedTag> genres = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMusician} with the given band details.
     */
    @JsonCreator
    public JsonAdaptedBand(@JsonProperty("name") String name, @JsonProperty("genres") HashSet genres,
                               @JsonProperty("musicians") List<JsonAdaptedMusician> musicians) {
        this.name = name;
        if (musicians != null) {
            this.musicians.addAll(musicians);
        }
        // if (genres != null) {
        //    this.genres.addAll(genres);
        //}
    }

    /**
     * Converts a given {@code Band} into this class for Jackson use.
     */
    public JsonAdaptedBand(Band source) {
        name = source.getName().fullName;
        //genres.addAll(source.getGenres().stream()
        //.map(JsonAdaptedTag::new)
        //.collect(Collectors.toList()));
        musicians.addAll(source.getMusicians().stream().map(JsonAdaptedMusician::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted musician object into the model's {@code Musician} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted musician.
     */
    public Band toModelType() throws IllegalValueException {
        // final List<Tag> bandGenres = new ArrayList<>();
        final HashSet<Musician> bandMusicians = new HashSet<>();
        // for (JsonAdaptedTag genre : genres) {
        //    bandGenres.add(genre.toModelType());
        // }
        for (JsonAdaptedMusician musician : musicians) {
            bandMusicians.add(musician.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final BandName modelName = new BandName(name);

        final Set<Tag> modelGenres = new HashSet<>(); // bandGenres
        return new Band(modelName, modelGenres, bandMusicians);
    }
}
