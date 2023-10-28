package swe.context.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import swe.context.commons.exceptions.IllegalValueException;
import swe.context.logic.Messages;
import swe.context.model.alternate.AlternateContact;


/**
 * Immutable, Jackson-friendly version of {@link AlternateContact}.
 *
 * The data it contains may be invalid if the instance was deserialized from
 * JSON. Checks are done when converting {@link #toModelType()}.
 */
public final class JsonAlternateContact {
    private final String value;

    /**
     * Constructs by converting the specified {@link AlternateContact}.
     */
    public JsonAlternateContact(AlternateContact alternateContact) {
        this(alternateContact.value);
    }

    @JsonCreator
    public JsonAlternateContact(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    /**
     * Attempts to convert this to the model's {@link AlternateContact} type.
     *
     * @throws IllegalValueException If any data this contains is invalid.
     */
    public AlternateContact toModelType() throws IllegalValueException {
        if (!AlternateContact.isValid(this.value)) {
            throw new IllegalValueException(
                Messages.tagInvalid(this.value)
            );
        }

        return new AlternateContact(this.value);
    }
}
