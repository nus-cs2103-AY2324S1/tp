package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.policy.Policy;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedPolicy {
    private final String policyString;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given {@code policyName}.
     */
    @JsonCreator
    public JsonAdaptedPolicy(
            String policyString) {
        this.policyString = policyString;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        policyString = source.toString();
    }

    @JsonValue
    public String getPolicyString() {
        return policyString;
    }

    /**
     * Converts this Jackson-friendly adapted policy object into the model's
     * {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted tag.
     */
    public Policy toModelType() throws IllegalValueException {
        return Policy.fromString(policyString);
    }
}
