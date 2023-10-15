package transact.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import transact.commons.exceptions.IllegalValueException;
import transact.model.transaction.info.Description;

/**
 * Jackson-friendly version of {@link Description}.
 */
class JsonAdaptedDescription {

    private final String description;

    @JsonCreator
    public JsonAdaptedDescription(@JsonProperty("description") String description) {
        this.description = description;
    }

    public JsonAdaptedDescription(Description source) {
        this.description = source.getValue();
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    public Description toModelType() throws IllegalValueException {
        return new Description(description);
    }
}
