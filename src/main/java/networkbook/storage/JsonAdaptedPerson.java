package networkbook.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import networkbook.commons.exceptions.IllegalValueException;
import networkbook.model.person.Address;
import networkbook.model.person.Email;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.tag.Tag;
import networkbook.model.util.UniqueList;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final List<JsonAdaptedProperty<Email>> emails = new ArrayList<>();
    private final String address;
    private final List<JsonAdaptedProperty<Tag>> tags = new ArrayList<>();
    private final String priority;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") List<JsonAdaptedProperty<Email>> emails, @JsonProperty("address") String address,
            @JsonProperty("tags") List<JsonAdaptedProperty<Tag>> tags, @JsonProperty("priority") String priority) {
        this.name = name;
        this.phone = phone;
        if (emails != null) {
            this.emails.addAll(emails);
        }
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.priority = priority;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        emails.addAll(source.getEmails().stream()
                .map(JsonAdaptedProperty::new)
                .collect(Collectors.toList()));
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedProperty::new)
                .collect(Collectors.toList()));
        priority = source.getPriority().map(Priority::toString).orElse(null);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedProperty<Tag> tag : tags) {
            personTags.add(tag.toModelType(Tag::isValidTagName, Tag.MESSAGE_CONSTRAINTS, Tag::new));
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

        if (emails == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!emails.stream()
                .map(JsonAdaptedProperty::getName)
                .allMatch(Email::isValidEmail)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final UniqueList<Email> modelEmails = new UniqueList<>();
        emails.forEach(email -> modelEmails.add(new Email(email.getName())));

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        Priority modelPriority = null;
        if (priority != null) {
            if (!Priority.isValidPriority(Priority.parsePriorityLevel(priority))) {
                throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
            }
            modelPriority = new Priority(priority);
        }

        return new Person(modelName, modelPhone, modelEmails, modelAddress, modelTags, modelPriority);
    }

}
