package seedu.address.seplendidui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.note.Note;

/**
 * A UI component that displays information of a {@code note}.
 */
public class NoteDetail extends UiPart<Region> {

    private static final String FXML = "NoteDetailCard.fxml";

    public final Note note;

    @FXML
    private HBox noteDetailPane;
    @FXML
    private Label tags;
    @FXML
    private TextFlow noteContent;

    /**
     * Creates a {@code noteDetail} with the given {@code note}
     */
    public NoteDetail(Note note) {
        super(FXML);
        this.note = note;
        TextFlowHeightUpdater.setAutoFitText(noteContent);

        noteContent.getChildren().add(new Text(note.getContent().toString()));
        tags.setText("Tags:" + note.getTags().toString());
    }
}
