package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Note;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;



/**
 * Jackson-friendly version of {@link Contact}.
 */
class JsonAdaptedContact {
    private final String name;
    private final String phone;
    private final String email;
    private final String note;
    private final List<JsonTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedContact} with the given contact details.
     */
    @JsonCreator
    public JsonAdaptedContact(
        @JsonProperty("name") String _name,
        @JsonProperty("phone") String _phone,
        @JsonProperty("email") String _email,
        @JsonProperty("note") String _note,
        @JsonProperty("tags") List<JsonTag> _tags
    ) {
        this.name = _name;
        this.phone = _phone;
        this.email = _email;
        this.note = _note;
        if (_tags != null) {
            this.tags.addAll(_tags);
        }
    }

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact contact) {
        name = contact.getName().fullName;
        phone = contact.getPhone().value;
        email = contact.getEmail().value;
        note = contact.getNote().text;
        tags.addAll(contact.getTags().stream()
                .map(JsonTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Contact toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(Messages.MESSAGE_FIELD_MISSING, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Messages.MESSAGE_NAME_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(Messages.MESSAGE_FIELD_MISSING, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Messages.MESSAGE_PHONE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(Messages.MESSAGE_FIELD_MISSING, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Messages.MESSAGE_EMAIL_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (this.note == null) {
            throw new IllegalValueException(String.format(Messages.MESSAGE_FIELD_MISSING, Note.class.getSimpleName()));
        }
        final Note modelNote = new Note(this.note);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Contact(modelName, modelPhone, modelEmail, modelNote, modelTags);
    }

}
