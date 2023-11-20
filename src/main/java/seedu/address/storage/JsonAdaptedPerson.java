package seedu.address.storage;

import static seedu.address.model.person.Client.TYPE_CLIENT;
import static seedu.address.model.person.Lead.TYPE_LEAD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.KeyMilestone;
import seedu.address.model.person.Lead;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Type;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String type;
    private final String address;
    private String keyMilestone;
    private String meetingTime;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */

    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("type") String type,
                             @JsonProperty("address") String address,
                             @JsonProperty("keyMilestone") String keyMilestone,
                             @JsonProperty("meetingTime") String meetingTime,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.address = address;
        this.keyMilestone = keyMilestone;
        if (meetingTime != null) {
            this.meetingTime = meetingTime;
        }
        if (tags != null) {
            this.tags.addAll(tags);
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
        type = source.getType().value;
        if (type == TYPE_LEAD) {
            Lead sourceLead = (Lead) source;
            keyMilestone = sourceLead.getKeyMilestone().value;
        }
        meetingTime = source.getMeetingTime().map(MeetingTime::toString).orElse(null);
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
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

        final Optional<MeetingTime> modelMeetingTime;
        if (meetingTime == null) {
            modelMeetingTime = Optional.empty();
        } else {
            if (!MeetingTime.isValidMeetingTime(meetingTime)) {
                throw new IllegalValueException(MeetingTime.MESSAGE_CONSTRAINTS);
            }
            modelMeetingTime = Optional.of(new MeetingTime(meetingTime));
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName()));
        }
        if (type.equals(TYPE_CLIENT)) {
            return new Client(modelName, modelPhone, modelEmail, modelAddress, modelMeetingTime, modelTags);
        } else if (type.equals(TYPE_LEAD)) {
            if (keyMilestone == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        KeyMilestone.class.getSimpleName()));
            }
            if (!KeyMilestone.isValidKeyMilestone(keyMilestone)) {
                throw new IllegalValueException(KeyMilestone.MESSAGE_CONSTRAINTS);
            }
            final KeyMilestone modelKeyMilestone = new KeyMilestone(keyMilestone);
            return new Lead(modelName, modelPhone, modelEmail, modelAddress, modelKeyMilestone,
                    modelMeetingTime, modelTags);
        } else {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }
    }

}
