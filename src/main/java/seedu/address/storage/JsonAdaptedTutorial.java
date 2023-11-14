package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutorial.Tutorial;

/**
 * Jackson-friendly version of {@link Tutorial}.
 */
public class JsonAdaptedTutorial {

    private final String tutorial;

    /**
     * Constructs a {@code JsonAdaptedTutorial} with the given {@code tutorial}.
     *
     * @param tutorial The tutorial value to be used in the adapted object.
     */
    @JsonCreator
    public JsonAdaptedTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    /**
     * Converts a given {@code Tutorial} into this class for Jackson use.
     *
     * @param source The source Tutorial to be converted.
     */
    public JsonAdaptedTutorial(Tutorial source) {
        tutorial = source.value;
    }

    /**
     * Gets the tutorial value.
     *
     * @return The tutorial value as a string.
     */
    @JsonValue
    public String getTutorial() {
        return tutorial;
    }

    /**
     * Converts this Jackson-friendly adapted tutorial object into the model's {@code Tutorial} object.
     *
     * @return A Tutorial object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutorial.
     */
    public Tutorial toModelType() throws IllegalValueException {
        if (!Tutorial.isValidTutorial(tutorial)) {
            throw new IllegalValueException(Tutorial.MESSAGE_CONSTRAINTS);
        }
        return new Tutorial(tutorial);
    }
}
