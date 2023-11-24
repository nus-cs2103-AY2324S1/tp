package wedlog.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import wedlog.address.commons.exceptions.IllegalValueException;
import wedlog.address.model.person.Address;
import wedlog.address.model.person.Email;
import wedlog.address.model.person.Name;
import wedlog.address.model.person.Phone;
import wedlog.address.model.person.Vendor;
import wedlog.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Vendor}.
 */
class JsonAdaptedVendor extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Vendor's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedVendor} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedVendor(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        super(name, phone, email, address, tags);
    }

    /**
     * Converts a given {@code Vendor} into this class for Jackson use.
     */
    public JsonAdaptedVendor(Vendor source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Vendor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Vendor toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        final Phone modelPhone;
        if (phone == null) {
            modelPhone = null;
        } else if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        } else {
            modelPhone = new Phone(phone);
        }

        final Email modelEmail;
        if (email == null) {
            modelEmail = null;
        } else if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        } else {
            modelEmail = new Email(email);
        }

        final Address modelAddress;
        if (address == null) {
            modelAddress = null;
        } else if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        } else {
            modelAddress = new Address(address);
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Vendor(modelName, modelPhone, modelEmail, modelAddress, modelTags);
    }

}
