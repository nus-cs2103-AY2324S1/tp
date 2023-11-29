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
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.dentist.Specialization;
import seedu.address.model.person.dentist.Yoe;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Dentist}.
 */
class JsonAdaptedDentist {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Dentist's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String specialization;
    private final String yearsOfExperience;
    private final String id;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedDentist} with the given dentist details.
     */
    @JsonCreator
    public JsonAdaptedDentist(@JsonProperty("name") String name,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email,
                              @JsonProperty("address") String address,
                              @JsonProperty("specialization") String specialization,
                              @JsonProperty("yoe") String yearsOfExperience,
                              @JsonProperty("id") String id,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.id = id;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Dentist} into this class for Jackson use.
     */
    public JsonAdaptedDentist(Dentist source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        specialization = source.getSpecialization().getValue();
        yearsOfExperience = source.getYoe().getValue();
        id = String.valueOf(source.getId());
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted dentist object into the model's {@code Dentist} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               dentist.
     */
    public Dentist toModelType() throws IllegalValueException {
        final List<Tag> dentistTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            dentistTags.add(tag.toModelType());
        }

        final Name modelName = validateName();
        final Phone modelPhone = validatePhone();
        final Email modelEmail = validateEmail();
        final Address modelAddress = validateAddress();
        final Specialization modelSpecialization = validateSpecialization();
        final Yoe modelYoe = validateYoe();
        final long modelDentistId = validateDentistId();
        final Set<Tag> modelTags = validateTags();

        return new Dentist(modelName, modelPhone, modelEmail, modelAddress,
                modelSpecialization, modelYoe, modelDentistId, modelTags);
    }

    /**
     * Validate the name supplied from storage.
     *
     * @return a valid name object.
     * @throws IllegalValueException if name supplied is not valid.
     */
    private Name validateName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    /**
     * Validate the phone supplied from storage.
     *
     * @return a valid phone object.
     * @throws IllegalValueException if phone supplied is not valid.
     */
    private Phone validatePhone() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    /**
     * Validate the email supplied from storage.
     *
     * @return a valid email object.
     * @throws IllegalValueException if email supplied is not valid.
     */
    private Email validateEmail() throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(email);
    }

    /**
     * Validate the address supplied from storage.
     *
     * @return a valid address object.
     * @throws IllegalValueException if address supplied is not valid.
     */
    private Address validateAddress() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(address);
    }

    /**
     * Validate the specialization supplied from storage.
     *
     * @return a valid specialization object.
     * @throws IllegalValueException if specialization supplied is not valid.
     */
    private Specialization validateSpecialization() throws IllegalValueException {
        if (specialization == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Specialization.class.getSimpleName()));
        }
        if (!Specialization.isValidSpecialization(specialization)) {
            throw new IllegalValueException(Specialization.MESSAGE_CONSTRAINTS);
        }
        return new Specialization(specialization);
    }

    /**
     * Validate the years of experience supplied from storage.
     *
     * @return a valid years of experience object.
     * @throws IllegalValueException if years of experience supplied is not valid.
     */
    private Yoe validateYoe() throws IllegalValueException {
        if (yearsOfExperience == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "Years of Experience")); // To be changed
        }
        if (!Yoe.isValidYoe(yearsOfExperience)) {
            throw new IllegalValueException(Yoe.MESSAGE_CONSTRAINTS);
        }
        //To be added more
        return new Yoe(yearsOfExperience);
    }

    /**
     * Validate the Dentist ID supplied from storage.
     *
     * @return a valid Dentist ID as a long integer.
     * @throws IllegalValueException if the provided Dentist ID is invalid.
     */
    private long validateDentistId() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException("Dentist ID value does not exist!");
        }
        return Long.parseLong(id);
    }


    /**
     * Validate the tags supplied from storage.
     *
     * @return a valid set of tags
     * @throws IllegalValueException if tags supplied are not valid.
     */
    private Set<Tag> validateTags() throws IllegalValueException {
        final List<Tag> dentistTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            dentistTags.add(tag.toModelType());
        }
        return new HashSet<>(dentistTags);
    }

}

