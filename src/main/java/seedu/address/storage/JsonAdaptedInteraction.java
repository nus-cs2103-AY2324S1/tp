package seedu.address.storage;

import static seedu.address.model.person.interaction.Interaction.DEFAULT_DATE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.person.interaction.Interaction.Outcome;

/**
 * Jackson-friendly version of {@link Interaction}.
 */
class JsonAdaptedInteraction {
    public static final String INVALID_DATE_FIELD_MESSAGE = "Date format is invalid. Please use dd-MMM-yyyy format";
    public static final String INTERACTION_MISSING_FIELD_MESSAGE_FORMAT = "Interaction's %s field is missing!";

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedInteraction.class);

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

    public JsonAdaptedInteraction(Interaction interaction) {
        this.interactionNote = interaction.getInteractionNote();
        this.outcome = interaction.getOutcome().toString();
        this.date = DEFAULT_DATE_FORMAT.format(interaction.getDate()).toString();
        logger.info("Saving Interaction details into JSON: " + this.interactionNote + " " + this.outcome + " "
                + this.date);
    }

    /**
     * Converts this Jackson-friendly adapted interaction object into the model's {@code Interaction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interaction.
     */
    public Interaction toModelType() throws IllegalValueException {
        if ((interactionNote == null && outcome == null) || (interactionNote.isEmpty() && outcome.isEmpty())) {
            throw new IllegalValueException(
                    String.format(INTERACTION_MISSING_FIELD_MESSAGE_FORMAT, "interactionNote or outcome"));
        }

        if (outcome != null && !Outcome.isValidOutcome(outcome)) {
            throw new IllegalValueException(Interaction.Outcome.MESSAGE_CONSTRAINTS);
        }
        final Outcome modelOutcome = outcome == null ? Outcome.UNKNOWN : Outcome.valueOf(outcome);

        if (date == null) {
            throw new IllegalValueException(
                    String.format(INTERACTION_MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName()));
        }
        LocalDate modelDate;
        try {
            logger.info("Read Date into JSON: " + date);
            modelDate = LocalDate.parse(date, DEFAULT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(INVALID_DATE_FIELD_MESSAGE, e);
        }

        return new Interaction(interactionNote, modelOutcome, modelDate);
    }

}
