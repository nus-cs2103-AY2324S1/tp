package seedu.address.seplendidui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.localcourse.LocalCourse;

/**
 * An UI component that displays information of a {@code LocalCourse}.
 */
public class LocalCourseItem extends UiPart<Region> {

    private static final String FXML = "LocalCourseItemCard.fxml";

    public final LocalCourse localCourse;

    @FXML
    private HBox localCourseItemPane;
    @FXML
    private Label id;
    @FXML
    private Label localName;
    @FXML
    private Label localCode;

    /**
     * Creates a {@code LocalCourseItem} with the given {@code LocalCourse}
     */
    public LocalCourseItem(LocalCourse localCourse, int displayedIndex) {
        super(FXML);
        this.localCourse = localCourse;
        id.setText(displayedIndex + ".");
        localName.setText(localCourse.getLocalName().toString());
        localCode.setText(localCourse.getLocalCode().toString());
    }
}
