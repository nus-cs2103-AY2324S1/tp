package seedu.address.ui;

import static seedu.address.commons.util.DateTimeParserUtil.parseDateTimeToString;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipInterviewDateTime;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    private static final String FXML = "InternshipListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;

    @FXML
    private HBox cardPane;
    @FXML
    private Label roleName;
    @FXML
    private Label internId;
    @FXML
    private Label internshipDesc;
    @FXML
    private Label internshipDateTime;
    @FXML
    private Label interviewDateTimeLabel;
    @FXML
    private HBox dateTimeHBox;
    @FXML
    private VBox internshipCardVBox;

    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        internId.setText(displayedIndex + ". ");
        roleName.setText(internship.getInternshipName().fullName);
        internshipDesc.setText(internship.getInternshipDescription().value);

        Optional<InternshipInterviewDateTime> dateTime = internship.getInternshipDateTime();
        if (dateTime.isPresent()) {
            internshipDateTime.setText(parseDateTimeToString(dateTime.get().getInternshipDateTime()));
        } else {
            internshipCardVBox.getChildren().remove(dateTimeHBox);
        }


    }
}

