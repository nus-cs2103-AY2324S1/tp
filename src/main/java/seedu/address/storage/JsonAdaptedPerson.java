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
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PreferredContact;
import seedu.address.model.person.PreferredMeetingRegion;
import seedu.address.model.policy.Policy;
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
    private final String preferredContact;
    private final String preferredMeetingRegion;
    private final List<JsonAdaptedPolicy> policies = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("preferredContact") String preferredContact,
            @JsonProperty("preferredMeetingRegion") String preferredMeetingRegion,
            @JsonProperty("policies") List<JsonAdaptedPolicy> policies) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.preferredContact = preferredContact;
        this.preferredMeetingRegion = preferredMeetingRegion;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (policies != null) {
            this.policies.addAll(policies);
        }
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
        preferredContact = source.getPreferredContact().value;
        preferredMeetingRegion = source.getPreferredMeetingRegion().value;
        policies.addAll(source.getPolicies().stream()
                .map(JsonAdaptedPolicy::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (preferredContact == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PreferredContact.class.getSimpleName()));
        }

        if (!PreferredContact.isValidPreferredContact(preferredContact)) {
            throw new IllegalValueException(PreferredContact.MESSAGE_CONSTRAINTS);
        }
        final PreferredContact modelPreferredContact = new PreferredContact(preferredContact);

        if (preferredMeetingRegion == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PreferredMeetingRegion.class.getSimpleName()));
        }

        if (!PreferredMeetingRegion.isValidPreferredMeetingRegion(preferredMeetingRegion)) {
            throw new IllegalValueException(PreferredMeetingRegion.MESSAGE_CONSTRAINTS);
        }

        final PreferredMeetingRegion modelPreferredMeetingRegion = new PreferredMeetingRegion(preferredMeetingRegion);

        final List<Policy> personPolicies = new ArrayList<>();

        for (JsonAdaptedPolicy policy : policies) {
            personPolicies.add(policy.toModelType());
        }

        final Set<Policy> modelPolicies = new HashSet<>(personPolicies);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelPreferredMeetingRegion,
                modelPreferredContact, modelPolicies);
    }

}
