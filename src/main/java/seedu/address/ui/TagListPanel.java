package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tag.Tag;

/**
 * Panel containing the list of tags.
 */
public class TagListPanel extends UiPart<Region> {
    private static final String FXML = "TagListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TagListPanel.class);

    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TagListPanel} with the given {@code ObservableList}.
     */
    public TagListPanel(ObservableList<Tag> tagList) {
        super(FXML);
        updateTags(tagList);
    }

    // TODO: find a way to update the tag list when the tag list changes
    /**
     * Updates the tag list.
     */
    public void updateTags(ObservableList<Tag> updatedTagList) {
        tags.getChildren().clear();

        updatedTagList.stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName);
                    label.setStyle("-fx-font-size: 14px;");
                    tags.getChildren().add(label);
                });
    }
}
