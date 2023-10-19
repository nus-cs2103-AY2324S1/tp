package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.annotation.Nullable;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Note;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;



/**
 * Immutable, Jackson-friendly version of {@link Contact}.
 *
 * The data it contains may be invalid if the instance was deserialized from
 * JSON. Checks are done when converting {@link #toModelType()}.
 */
class JsonContact {
    private final @Nullable String name;
    private final @Nullable String phone;
    private final @Nullable String email;
    private final @Nullable String note;
    private final List<JsonTag> tags = new ArrayList<>();

    /**
     * Constructs by converting the specified {@link Contact}.
     */
    public JsonContact(Contact contact) {
        this(
            contact.getName().value,
            contact.getPhone().value,
            contact.getEmail().value,
            contact.getNote().value,
            contact.getTags()
                    .stream()
                    .map(JsonTag::new)
                    .collect(Collectors.toList())
        );
    }

    @JsonCreator
    public JsonContact(
        @JsonProperty("name") @Nullable String name,
        @JsonProperty("phone") @Nullable String phone,
        @JsonProperty("email") @Nullable String email,
        @JsonProperty("note") @Nullable String note,
        @JsonProperty("tags") @Nullable List<JsonTag> tags
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;

        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Attempts to convert this to the model's {@link Contact} type.
     *
     * @throws IllegalValueException If any data this contains is invalid.
     */
    public Contact toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(Messages.MESSAGE_FIELD_MISSING, Name.class.getSimpleName()));
        }
        if (!Name.isValid(name)) {
            throw new IllegalValueException(Messages.MESSAGE_NAME_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(Messages.MESSAGE_FIELD_MISSING, Phone.class.getSimpleName()));
        }
        if (!Phone.isValid(phone)) {
            throw new IllegalValueException(Messages.MESSAGE_PHONE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(Messages.MESSAGE_FIELD_MISSING, Email.class.getSimpleName()));
        }
        if (!Email.isValid(email)) {
            throw new IllegalValueException(Messages.EMAIL_INVALID);
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
