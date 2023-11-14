package seedu.address.seplendidui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.localcourse.LocalCourse;

/**
 * An UI component that displays information of a {@code LocalCourse}.
 */
public class LocalCourseDetail extends UiPart<Region> {

    private static final String FXML = "LocalCourseDetailCard.fxml";

    public final LocalCourse localCourse;

    @FXML
    private HBox localCourseDetailPane;
    @FXML
    private TextFlow localName;
    @FXML
    private Label localCode;
    @FXML
    private Label localUnit;
    @FXML
    private TextFlow localDescription;

    /**
     * Creates a {@code LocalCourseDetail} with the given {@code LocalCourse}
     */
    public LocalCourseDetail(LocalCourse localCourse) {
        super(FXML);
        this.localCourse = localCourse;
        TextFlowHeightUpdater.setAutoFitText(localName);
        TextFlowHeightUpdater.setAutoFitText(localDescription);

        localCode.setText(localCourse.getLocalCode().toString());
        localUnit.setText("Units: " + localCourse.getLocalUnit().toString());
        localName.getChildren().add(new Text(localCourse.getLocalName().toString()));
        localDescription.getChildren().add(new Text(localCourse.getLocalDescription().toString()));
    }
}
