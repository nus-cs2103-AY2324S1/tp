package seedu.address.seplendidui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.partnercourse.PartnerCourse;

/**
 * An UI component that displays information of a {@code PartnerCourse}.
 */
public class PartnerCourseItem extends UiPart<Region> {

    private static final String FXML = "PartnerCourseItemCard.fxml";

    public final PartnerCourse partnerCourse;

    @FXML
    private HBox partnerCourseItemPane;
    @FXML
    private Label id;
    @FXML
    private Label partnerName;
    @FXML
    private Label partnerCode;
    @FXML
    private Label partnerUniversity;

    /**
     * Creates a {@code PartnerCourseItem} with the given {@code PartnerCourse}
     */
    public PartnerCourseItem(PartnerCourse partnerCourse, int displayedIndex) {
        super(FXML);
        this.partnerCourse = partnerCourse;
        id.setText(displayedIndex + ".");
        partnerName.setText(partnerCourse.getPartnerName().toString());
        partnerCode.setText(partnerCourse.getPartnerCode().toString());
        partnerUniversity.setText(partnerCourse.getPartnerUniversity().getUniversityName().toString());
    }
}
