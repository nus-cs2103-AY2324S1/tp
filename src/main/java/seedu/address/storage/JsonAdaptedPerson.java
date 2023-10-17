package seedu.address.storage;

import java.time.MonthDay;
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
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
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
    private final Optional<MonthDay> birthday;
    private final Optional<String> linkedin;
    private final Optional<String> secondaryEmail;
    private final Optional<String> telegram;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final Optional<Integer> id;

    private final List<JsonAdaptedNote> notes = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("birthday") Optional<MonthDay> birthday, @JsonProperty("linkedin") Optional<String> linkedin,
            @JsonProperty("secondaryEmail") Optional<String> secondaryEmail,
            @JsonProperty("telegram") Optional<String> telegram,
            @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("id") Optional<Integer> id,
            @JsonProperty("notes") List<JsonAdaptedNote> notes
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.linkedin = linkedin;
        this.secondaryEmail = secondaryEmail;
        this.telegram = telegram;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.id = id;
        if (notes != null) {
            this.notes.addAll(notes);
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
        birthday = source.getBirthday().map(b -> b.birthday);
        linkedin = source.getLinkedin().map(l -> l.value);
        secondaryEmail = source.getSecondaryEmail().map(e -> e.value);
        telegram = source.getTelegram().map(t -> t.value);
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        id = source.getId().map(x -> x.intValue());
        notes.addAll(source.getNotes().stream()
            .map(JsonAdaptedNote::new)
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
        final List<Note> personNotes = new ArrayList<>();
        for (JsonAdaptedNote note : notes) {
            personNotes.add(note.toModelType());
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

        final Optional<Birthday> modelBirthday = birthday.map(monthDay -> new Birthday(monthDay));

        final Optional<Linkedin> modelLinkedin = linkedin.map(linkedin -> new Linkedin(linkedin));

        final Optional<Email> modelSecondaryEmail = secondaryEmail.map(email -> new Email(email));

        final Optional<Telegram> modelTelegram = telegram.map(telegram -> new Telegram(telegram));

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Optional<Integer> modelID = id.map(x -> x.intValue());
        final List<Note> modelNotes = new ArrayList<>(personNotes);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelBirthday, modelLinkedin,
                modelSecondaryEmail, modelTelegram, modelTags, modelID, modelNotes);
    }

}
