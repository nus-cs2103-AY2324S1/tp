package seedu.spendnsplit.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.spendnsplit.commons.exceptions.IllegalValueException;
import seedu.spendnsplit.logic.parser.ParserUtil;
import seedu.spendnsplit.logic.parser.exceptions.ParseException;
import seedu.spendnsplit.model.person.Name;
import seedu.spendnsplit.model.transaction.portion.Portion;
import seedu.spendnsplit.model.transaction.portion.Weight;

/**
 * Jackson-friendly version of {@link Portion}.
 */
public class JsonAdaptedPortion {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Portion's %s field is missing!";
    private final String personName;
    private final String weight;

    /**
     * Constructs a {@code JsonAdaptedPortion} with the given portion details.
     */
    @JsonCreator
    public JsonAdaptedPortion(@JsonProperty("personName") String personName, @JsonProperty("weight") String weight) {
        this.personName = personName;
        this.weight = weight;
    }

    /**
     * Converts a given {@code Portion} into this class for Jackson use.
     */
    public JsonAdaptedPortion(Portion source) {
        personName = source.getPersonName().fullName;
        weight = source.getWeight().value.toString();
    }

    /**
     * Converts this Jackson-friendly adapted portion object into the model's {@code Portion} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted portion.
     */
    public Portion toModelType() throws IllegalValueException {
        if (personName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(personName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(personName);
        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight;
        try {
            modelWeight = ParserUtil.parseWeight(weight);
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }
        return new Portion(modelName, modelWeight);
    }
}

