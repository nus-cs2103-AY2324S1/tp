package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the list of courses.
 */
public class TagListPanel extends UiPart<Region> {
    private static final String FXML = "TagListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TagListPanel.class);

    @FXML
    private ListView<Pair<String, Integer>> tagListView;

    /**
     * Creates a {@code TagListPanel} with the given {@code ObservableList}.
     */
    public TagListPanel(ObservableList<Pair<String, Integer>> tagList) {
        super(FXML);
        tagListView.setItems(tagList);
        tagListView.setCellFactory(listView -> new TagListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tag} using a {@code
     * TagCard}.
     */
    class TagListViewCell extends ListCell<Pair<String, Integer>> {
        @Override
        protected void updateItem(Pair<String, Integer> tagPair, boolean empty) {
            super.updateItem(tagPair, empty);

            if (empty || tagPair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TagCard(tagPair).getRoot());
            }
        }
    }

}
