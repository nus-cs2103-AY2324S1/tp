package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Contact;

/**
 * Jackson-friendly version of {@link Contact}.
 */
class JsonAdaptedContact {

    private final String contact;

    /**
     * Constructs a {@code JsonAdaptedContact} with the given {@code contact}.
     */
    @JsonCreator
    public JsonAdaptedContact(String contact) {
        this.contact = contact;
    }

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact source) {
        contact = source.contact;
    }

    @JsonValue
    public String getContact() {
        return contact;
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Contact toModelType() throws IllegalValueException {
        if (!Contact.isValidContactName(contact)) {
            throw new IllegalValueException(Contact.MESSAGE_CONSTRAINTS);
        }
        return new Contact(contact);
    }

}
