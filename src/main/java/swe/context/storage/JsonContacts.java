package swe.context.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import swe.context.annotation.Nullable;
import swe.context.commons.exceptions.IllegalValueException;
import swe.context.logic.Messages;
import swe.context.model.Contacts;
import swe.context.model.ReadOnlyContacts;
import swe.context.model.contact.Contact;



/**
 * Immutable, Jackson-friendly version of {@link ReadOnlyContacts}.
 *
 * The data it contains may be invalid if the instance was deserialized from
 * JSON. Checks are done when converting {@link #toModelType()}.
 */
class JsonContacts {
    private final List<JsonContact> contacts = new ArrayList<>();

    /**
     * Constructs by converting the specified {@link ReadOnlyContacts}.
     */
    public JsonContacts(ReadOnlyContacts contacts) {
        this(
            contacts.getUnmodifiableList()
                .stream()
                .map(JsonContact::new)
                .collect(Collectors.toList())
        );
    }

    @JsonCreator
    public JsonContacts(
        @JsonProperty("contacts") @Nullable List<JsonContact> contacts
    ) {
        if (contacts != null) {
            this.contacts.addAll(contacts);
        }
    }

    /**
     * Attempts to convert this to the model's {@link Contact} type.
     *
     * @throws IllegalValueException If any data this contains is invalid.
     */
    public Contacts toModelType() throws IllegalValueException {
        Contacts contacts = new Contacts();
        for (JsonContact jsonContact : this.contacts) {
            Contact contact = jsonContact.toModelType();
            if (contacts.contains(contact)) {
                throw new IllegalValueException(Messages.CONVERT_CONTACTS_DUPLICATE);
            }
            contacts.add(contact);
        }
        return contacts;
    }
}
