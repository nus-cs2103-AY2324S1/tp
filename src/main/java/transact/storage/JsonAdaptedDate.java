package transact.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import transact.commons.exceptions.IllegalValueException;
import transact.model.transaction.info.Date;

/**
 * Jackson-friendly version of {@link Date}.
 */
class JsonAdaptedDate {

    private final String date;

    @JsonCreator
    public JsonAdaptedDate(@JsonProperty("date") String date) {
        this.date = date;
    }

    public JsonAdaptedDate(Date source) {
        this.date = source.toString();
    }

    @JsonValue
    public String getDate() {
        return date;
    }

    public Date toModelType() throws IllegalValueException {
        return new Date(date);
    }
}
