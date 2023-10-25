package seedu.flashlingo.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.logic.parser.FlashlingoParser;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * Panel containing the list of persons.
 */
public class FlashcardListPanel extends UiPart<Region> {
    private static final String FXML = "FlashcardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FlashcardListPanel.class);

    @FXML
    private ListView<FlashCard> flashcardListView;

    private Model model;
    private MainWindow mw;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public FlashcardListPanel(ObservableList<FlashCard> flashcardList, Model model, MainWindow mw) {
        super(FXML);
        this.model = model;
        this.mw = mw;
        flashcardListView.setItems(flashcardList);
        flashcardListView.setCellFactory(listView -> new FlashCardListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class FlashCardListViewCell extends ListCell<FlashCard> {
        @Override
        protected void updateItem(FlashCard fc, boolean empty) {
            super.updateItem(fc, empty);

            if (empty || fc == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (FlashlingoParser.getReviewSession()) {
                    setGraphic(new FlashcardBox(fc, getIndex() + 1, model, mw).getRoot());
                } else {
                    setGraphic(new FlashcardBoxNoButton(fc, getIndex() + 1).getRoot());
                }
            }
        }
    }

}
