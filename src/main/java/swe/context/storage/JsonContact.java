package swe.context.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import swe.context.annotation.Nullable;
import swe.context.commons.exceptions.IllegalValueException;
import swe.context.logic.Messages;
import swe.context.model.alternate.AlternateContact;
import swe.context.model.contact.Contact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;



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
    private final List<JsonAlternateContact> alternateContacts = new ArrayList<>();

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
                    .collect(Collectors.toList()),
            contact.getAlternates()
                    .stream()
                    .map(JsonAlternateContact::new)
                    .collect(Collectors.toList())
        );
    }

    @JsonCreator
    public JsonContact(
        @JsonProperty("name") @Nullable String name,
        @JsonProperty("phone") @Nullable String phone,
        @JsonProperty("email") @Nullable String email,
        @JsonProperty("note") @Nullable String note,
        @JsonProperty("tags") @Nullable List<JsonTag> tags,
        @JsonProperty("alternateContacts") @Nullable List<JsonAlternateContact> alternateContacts
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;

        if (tags != null) {
            this.tags.addAll(tags);
        }

        if (alternateContacts != null) {
            this.alternateContacts.addAll(alternateContacts);
        }
    }

    /**
     * Attempts to convert this to the model's {@link Contact} type.
     *
     * @throws IllegalValueException If any data this contains is invalid.
     */
    public Contact toModelType() throws IllegalValueException {
        final List<Tag> contactTags = new ArrayList<>();
        for (JsonTag tag : tags) {
            contactTags.add(tag.toModelType());
        }

        final List<AlternateContact> contactAlternates = new ArrayList<>();
        for (JsonAlternateContact alternateContact : alternateContacts) {
            contactAlternates.add(alternateContact.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(Messages.fieldMissing(Name.class.getSimpleName()));
        }
        if (!Name.isValid(name)) {
            throw new IllegalValueException(Messages.NAME_INVALID);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(Messages.fieldMissing(Phone.class.getSimpleName()));
        }
        if (!Phone.isValid(phone)) {
            throw new IllegalValueException(Messages.PHONE_INVALID);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(Messages.fieldMissing(Email.class.getSimpleName()));
        }
        if (!Email.isValid(email)) {
            throw new IllegalValueException(Messages.EMAIL_INVALID);
        }
        final Email modelEmail = new Email(email);

        if (this.note == null) {
            throw new IllegalValueException(Messages.fieldMissing(Note.class.getSimpleName()));
        }
        final Note modelNote = new Note(this.note);

        final Set<Tag> modelTags = new HashSet<>(contactTags);
        final Set<AlternateContact> modelAlternateContacts = new HashSet<>(contactAlternates);

        return new Contact(modelName, modelPhone, modelEmail, modelNote, modelTags, modelAlternateContacts);

    }
}
