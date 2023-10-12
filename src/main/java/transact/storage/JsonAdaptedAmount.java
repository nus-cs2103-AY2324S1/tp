package transact.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import transact.commons.exceptions.IllegalValueException;
import transact.model.tag.Tag;
import transact.model.transaction.info.Amount;

import java.math.BigDecimal;

/**
 * Jackson-friendly version of {@link Amount}.
 */
class JsonAdaptedAmount {

    private final BigDecimal value;

    @JsonCreator
    public JsonAdaptedAmount(@JsonProperty("amount") BigDecimal value) {
        this.value = value;
    }

    public JsonAdaptedAmount(Amount source) {
        this.value = source.getValue();
    }

    @JsonValue
    public BigDecimal getValue() {
        return value;
    }

    public Amount toModelType() throws IllegalValueException {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        return new Amount(value);
    }
}

