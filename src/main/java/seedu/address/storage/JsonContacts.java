package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.model.Contacts;
import seedu.address.model.ReadOnlyContacts;
import seedu.address.model.contact.Contact;



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
        @JsonProperty("contacts") List<JsonContact> contacts
    ) {
        this.contacts.addAll(contacts);
    }

    /**
     * Attempts to convert this to the model's {@link Contact} type.
     *
     * @throws IllegalValueException If any data this contains is invalid.
     */
    public Contacts toModelType() throws IllegalValueException {
        Contacts contactsManager = new Contacts();
        for (JsonContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (contactsManager.contains(contact)) {
                throw new IllegalValueException(Messages.CONVERT_CONTACTS_DUPLICATE);
            }
            contactsManager.add(contact);
        }
        return contactsManager;
    }
}
