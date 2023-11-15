package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String animalName;
    private final String availability;
    private final String animalType;
    private final String housing;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("animalName") String animalName,
                             @JsonProperty("availability") String availability,
                             @JsonProperty("animalType") String animalType,
                             @JsonProperty("housing") String housing) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.animalName = animalName;
        this.availability = availability;
        this.animalType = animalType;
        this.housing = housing;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        animalName = source.getAnimalName().fullName;
        availability = source.getAvailability().value;
        animalType = source.getAnimalType().value;
        housing = source.getHousing().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        try {
            final List<Tag> personTags = new ArrayList<>();
            for (JsonAdaptedTag tag : tags) {
                personTags.add(tag.toModelType());
            }

            if (name == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Name.class.getSimpleName()));
            }
            if (!Name.isValidName(name)) {
                throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
            }
            final Name modelName = new Name(name);

            if (phone == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Phone.class.getSimpleName()));
            }
            if (!Phone.isValidPhone(phone)) {
                throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
            }
            final Phone modelPhone = new Phone(phone);

            if (email == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Email.class.getSimpleName()));
            }
            if (!Email.isValidEmail(email)) {
                throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
            }
            final Email modelEmail = new Email(email);

            if (address == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Address.class.getSimpleName()));
            }
            if (!Address.isValidAddress(address)) {
                throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
            }
            final Address modelAddress = new Address(address);

            final Set<Tag> modelTags = new HashSet<>(personTags);

            if (animalName == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Name.class.getSimpleName()));
            }
            if (!Name.isValidName(animalName)) {
                throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
            }
            final Name modelAnimalName = new Name(animalName);

            if (availability == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Availability.class.getSimpleName()));
            }
            if (!Availability.isValidAvailability(availability)) {
                throw new IllegalValueException(Availability.MESSAGE_CONSTRAINTS);
            }
            final Availability modelAvailability = new Availability(availability);

            if (animalType == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        AnimalType.class.getSimpleName()));
            }
            if (!AnimalType.isValidAnimalType(animalType)) {
                throw new IllegalValueException(AnimalType.MESSAGE_CONSTRAINTS);
            }
            final AnimalType modelAnimaltype = new AnimalType(animalType, modelAvailability);

            if (housing == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Housing.class.getSimpleName()));
            }
            if (!Housing.isValidHousing(housing)) {
                throw new IllegalValueException(Housing.MESSAGE_CONSTRAINTS);
            }
            final Housing modelHousing = new Housing(housing);

            return new Person(modelName, modelPhone, modelEmail, modelAddress, modelHousing,
                    modelAvailability, modelAnimalName, modelAnimaltype, modelTags);

        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }

}
