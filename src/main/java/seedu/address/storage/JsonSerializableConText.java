package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.model.ConText;
import seedu.address.model.ReadOnlyConText;
import seedu.address.model.contact.Contact;

/**
 * An Immutable ConText that is serializable to JSON format.
 */
@JsonRootName(value = "ConText")
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
     * Converts a given {@code ReadOnlyConText} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableConText}.
     */
    public JsonSerializableConText(ReadOnlyConText source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ConText} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ConText toModelType() throws IllegalValueException {
        ConText conText = new ConText();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (conText.hasContact(contact)) {
                throw new IllegalValueException(Messages.MESSAGE_CONTAIN_DUPLICATE_CONTACT);
            }
            conText.addContact(contact);
        }
        return conText;
    }

}
