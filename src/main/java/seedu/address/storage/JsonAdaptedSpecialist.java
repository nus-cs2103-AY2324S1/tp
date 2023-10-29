package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Specialist;
import seedu.address.model.person.Specialty;
import seedu.address.model.tag.Tag;

class JsonAdaptedSpecialist extends JsonAdaptedPerson {
    private final String specialty;
    private final String location;

    public JsonAdaptedSpecialist(Specialist source) {
        super(source);
        specialty = source.getSpecialty().value;
        location = source.getLocation().value;
    }
    public JsonAdaptedSpecialist(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                 @JsonProperty("email") String email, @JsonProperty("location") String location,
                                 @JsonProperty("tags") List<JsonAdaptedTag> tags,
                                 @JsonProperty("Specialty") String specialty) {
        super(name, phone, email, tags);
        this.location = location;
        this.specialty = specialty;
    }
    public String getLocation() throws IllegalValueException {
        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        return location;
    }

    public String getSpecialty() throws IllegalValueException {
        if (specialty == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Specialty.class.getSimpleName()));
        }
        if (!Specialty.isValidSpecialty(specialty)) {
            throw new IllegalValueException(Specialty.MESSAGE_CONSTRAINTS);
        }
        return specialty;
    }


    /**
     * Converts this Jackson-friendly adapted specialist object into the model's {@code Specialist} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted specialist.
     */
    @Override
    public Specialist toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : getTags()) {
            personTags.add(tag.toModelType());
        }

        final Name modelName = new Name(getName());
        final Phone modelPhone = new Phone(getPhone());
        final Email modelEmail = new Email(getEmail());
        final Location modelLocation = new Location(getLocation());
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Specialty modelSpecialty = new Specialty(getSpecialty());

        return new Specialist(modelName, modelPhone, modelEmail, modelLocation, modelTags, modelSpecialty);
    }
}
