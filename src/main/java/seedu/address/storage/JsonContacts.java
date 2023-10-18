package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.model.ReadOnlyContacts;
import seedu.address.model.Contacts;
import seedu.address.model.contact.Contact;



//TODO is there a simpler way to get Jackson to automatically map these?
/**
 * An Immutable ContactsManager that is serializable to JSON format.
 */
@JsonRootName(value = "ContactsManager")
class JsonContacts {

    private final List<JsonContact> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableContacts} with the given contacts.
     */
    @JsonCreator
    public JsonContacts(@JsonProperty("contacts") List<JsonContact> contacts) {
        this.contacts.addAll(contacts);
    }

    /**
     * Converts a given {@code ContactList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableContacts}.
     */
    public JsonContacts(ReadOnlyContacts source) {
        contacts.addAll(source.getUnmodifiableList().stream().map(JsonContact::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ContactsManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Contacts toModelType() throws IllegalValueException {
        Contacts contactsManager = new Contacts();
        for (JsonContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (contactsManager.contains(contact)) {
                throw new IllegalValueException(Messages.MESSAGE_CONTAIN_DUPLICATE_CONTACT);
            }
            contactsManager.add(contact);
        }
        return contactsManager;
    }

}
