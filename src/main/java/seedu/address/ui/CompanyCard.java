package seedu.address.ui;

import static seedu.address.commons.util.DateTimeParserUtil.parseDateTimeToString;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.company.Company;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipInterviewDateTime;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class CompanyCard extends UiPart<Region> {

    private static final String FXML = "CompanyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Company company;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox companyCardVbox;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private HBox nextInterviewHbox;
    @FXML
    private Label nextInterviewLabel;
    @FXML
    private Label nextInterviewDate;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        id.setText(displayedIndex + ". ");
        name.setText(company.getCompanyName().fullName);
        company.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        Optional<Internship> mostUrgentInternship = company.getMostUrgentInternship();
        String dateTimeUrgent = "None";

        if (mostUrgentInternship.isPresent()) {

            Internship internship = mostUrgentInternship.get();
            Optional<InternshipInterviewDateTime> urgentDateTime = internship.getInternshipDateTime();

            if (urgentDateTime.isPresent()) {
                LocalDateTime dateTime = urgentDateTime.get().getInternshipDateTime();
                dateTimeUrgent = parseDateTimeToString(dateTime);
            }
        }

        this.nextInterviewDate.setText(dateTimeUrgent);
    }
}
