package seedu.address.storage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.person.interaction.Interaction.Outcome;

/**
 * Jackson-friendly version of {@link Interaction}.
 */
class JsonAdaptedInteraction {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Interaction's %s field is missing!";
    private static final String INVALID_DATE_FIELD_MESSAGE = "Date format is invalid. Please use dd-MMM-yyyy format";


    private final DateFormat defaultDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    private final String interactionNote;
    private final String outcome;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedInteraction} with the given details.
     */
    @JsonCreator
    public JsonAdaptedInteraction(@JsonProperty("interactionNote") String interactionNote,
                                  @JsonProperty("outcome") String outcome,
                                  @JsonProperty("date") String date
                                  ) {
        this.interactionNote = interactionNote;
        this.outcome = outcome;
        this.date = date;
    }

    /**
     * Converts this Jackson-friendly adapted interaction object into the model's {@code Interaction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interaction.
     */
    public Interaction toModelType() throws IllegalValueException {
        if (interactionNote == null && outcome == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "interactionNote or outcome"));
        }

        if (outcome != null && !Outcome.isValidOutcome(outcome)) {
            throw new IllegalValueException(Interaction.Outcome.MESSAGE_CONSTRAINTS);
        }
        final Outcome modelOutcome = outcome == null ? Outcome.UNKNOWN : Outcome.valueOf(outcome);

        if (date == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        Date modelDate;
        try {
            modelDate = defaultDateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalValueException(INVALID_DATE_FIELD_MESSAGE, e);
        }

        return new Interaction(interactionNote, modelOutcome, modelDate);
    }

}
