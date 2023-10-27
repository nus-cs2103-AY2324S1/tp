package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.person.interaction.InteractionList;

/**
 * Jackson-friendly version of {@link Interaction}.
 */
public class JsonAdaptedInteractionList {

    private final List<JsonAdaptedInteraction> interactions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDatabase} with the given modules.
     */
    @JsonCreator
    public JsonAdaptedInteractionList(@JsonProperty("interactions") List<JsonAdaptedInteraction> interactions) {
        this.interactions.addAll(interactions);
    }

    /**
     * Converts the list into the form required in {@code Interaction}.
     *
     * @throws IllegalValueException if there were any input constraints violated.
     */
    public InteractionList toInteractionList() throws IllegalValueException {
        InteractionList interactionList = new InteractionList();
        for (JsonAdaptedInteraction jsonAdaptedInteraction : interactions) {
            Interaction interaction = jsonAdaptedInteraction.toModelType();
            interactionList.addInteraction(interaction);
        }
        return interactionList;
    }
}
