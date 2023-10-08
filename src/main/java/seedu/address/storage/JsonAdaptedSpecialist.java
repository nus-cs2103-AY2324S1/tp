package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Specialist;
import seedu.address.model.tag.Tag;

class JsonAdaptedSpecialist extends JsonAdaptedPerson {

    public JsonAdaptedSpecialist(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        super(name, phone, email, address, tags);
    }

    public JsonAdaptedSpecialist(Specialist source) {
        super(source);
    }

    @Override
    public Specialist toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : getTags()) {
            personTags.add(tag.toModelType());
        }

        final Name modelName = new Name(getName());
        final Phone modelPhone = new Phone(getPhone());
        final Email modelEmail = new Email(getEmail());
        final Address modelAddress = new Address(getAddress());
        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Specialist(modelName, modelPhone, modelEmail, modelAddress, modelTags);
    }
}
