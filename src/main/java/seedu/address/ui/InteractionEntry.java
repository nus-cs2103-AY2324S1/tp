package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.interaction.Interaction;

/**
 * A UI component that displays information of a {@code Client} {@code Interaction}.
 */
public class InteractionEntry extends UiPart<Region> {

    private static final String FXML = "InteractionEntry.fxml";

    public final Interaction interaction;

    @FXML
    private Label note;
    @FXML
    private Label outcome;
    @FXML
    private Label date;

    /**
     * Creates a {@code InteractionEntry} with the given {@code Interaction}.
     */
    public InteractionEntry(Interaction interaction) {
        super(FXML);
        this.interaction = interaction;
        note.setText(interaction.getInteractionNote());
        date.setText(interaction.getDate().toString());

        Interaction.Outcome interactionOutcome = interaction.getOutcome();
        outcome.setText(interactionOutcome.toString());

        switch (interactionOutcome) {
        case CLOSED:
            outcome.getStyleClass().add("closed");
            break;
        case INTERESTED:
            outcome.getStyleClass().add("interested");
            break;
        case NOT_INTERESTED:
            outcome.getStyleClass().add("not-interested");
            break;
        case FOLLOWUP_REQUIRED:
            outcome.getStyleClass().add("followup-required");
            break;
        default:
            outcome.getStyleClass().add("unknown");
        }
    }
}
