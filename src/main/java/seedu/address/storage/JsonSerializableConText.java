package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.model.ContactsManager;
import seedu.address.model.ContactList;
import seedu.address.model.contact.Contact;

/**
 * An Immutable ContactsManager that is serializable to JSON format.
 */
@JsonRootName(value = "ContactsManager")
class JsonSerializableConText {

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableConText} with the given contacts.
     */
    @JsonCreator
    public JsonSerializableConText(@JsonProperty("contacts") List<JsonAdaptedContact> contacts) {
        this.contacts.addAll(contacts);
    }

    /**
     * Converts a given {@code ContactList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableConText}.
     */
    public JsonSerializableConText(ContactList source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ContactsManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ContactsManager toModelType() throws IllegalValueException {
        ContactsManager contactsManager = new ContactsManager();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (contactsManager.hasContact(contact)) {
                throw new IllegalValueException(Messages.MESSAGE_CONTAIN_DUPLICATE_CONTACT);
            }
            contactsManager.addContact(contact);
        }
        return contactsManager;
    }

}
