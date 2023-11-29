package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.treatment.Treatment;
import seedu.address.model.treatment.TreatmentCost;
import seedu.address.model.treatment.TreatmentName;
import seedu.address.model.treatment.TreatmentTime;

/**
 * Jackson-friendly version of {@link seedu.address.model.treatment.Treatment}.
 */
class JsonAdaptedTreatment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Treatment %s field is missing!";

    private final String name;
    private final String cost;
    private final String time;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTreatment(@JsonProperty("name") String name,
        @JsonProperty("cost") String cost,
        @JsonProperty("time") String time) {
        this.name = name;
        this.cost = cost;
        this.time = time;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedTreatment(Treatment source) {
        name = source.getName().value;
        cost = source.getCost().value;
        time = source.getTime().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Treatment}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               Treatment.
     */
    public Treatment toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, TreatmentName.class.getSimpleName()));
        }
        if (!TreatmentName.isValidName(name)) {
            throw new IllegalValueException(TreatmentName.MESSAGE_CONSTRAINTS);
        }
        final TreatmentName modelName = new TreatmentName(name);

        if (cost == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, TreatmentCost.class.getSimpleName()));
        }
        if (!TreatmentCost.isValidCost(cost)) {
            throw new IllegalValueException(TreatmentCost.MESSAGE_CONSTRAINTS);
        }
        final TreatmentCost modelCost = new TreatmentCost(cost);

        if (time == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, TreatmentTime.class.getSimpleName()));
        }
        if (!TreatmentTime.isValidTime(time)) {
            throw new IllegalValueException(TreatmentTime.MESSAGE_CONSTRAINTS);
        }
        final TreatmentTime modelTime = new TreatmentTime(time);

        return new Treatment(modelName, modelCost, modelTime);

    }

}
