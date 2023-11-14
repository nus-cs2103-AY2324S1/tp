package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.UniversityCatalogue;
import seedu.address.model.university.University;

/**
 * An Immutable UniversityCatalogue that is serializable to JSON format.
 */
@JsonRootName(value = "universitycatalogue")
class JsonSerializableUniversityCatalogue {

    public static final String MESSAGE_DUPLICATE_UNIVERSITY = "University catalogue contains duplicate university "
            + "name.";

    private final List<JsonAdaptedUniversity> universities = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableUniversityCourseCatalogue} with the given universities.
     */
    @JsonCreator
    public JsonSerializableUniversityCatalogue(@JsonProperty("universities") List<JsonAdaptedUniversity>
                                                       universityList) {
        this.universities.addAll(universityList);
    }

    /**
     * Converts a given {@code ReadOnlyUniversityCourseCatalogue} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     *      {@code JsonSerializableUniversityCourseCatalogue}.
     */
    public JsonSerializableUniversityCatalogue(ReadOnlyUniversityCatalogue source) {
        universities.addAll(source.getUniversityList().stream().map(
                JsonAdaptedUniversity::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code UniversityCatalogue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniversityCatalogue toModelType() throws IllegalValueException {
        UniversityCatalogue universityCatalogue = new UniversityCatalogue();
        for (JsonAdaptedUniversity jsonAdaptedUniversity : universities) {
            University university = jsonAdaptedUniversity.toModelType();
            if (universityCatalogue.hasUniversity(university)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_UNIVERSITY);
            }
            universityCatalogue.addUniversity(university);
        }
        return universityCatalogue;
    }

}
