package seedu.lovebook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.lovebook.model.person.Date;

/**
 * An UI component that displays information of a {@code Date}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/LoveBook-level4/issues/336">The issue on LoveBook level 4</a>
     */

    public final Date date;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label age;
    @FXML
    private Label height;

    @FXML
    private Label income;
    @FXML
    private Label gender;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Date} and index to display.
     */
    public PersonCard(Date date, int displayedIndex) {
        super(FXML);
        this.date = date;
        id.setText(displayedIndex + ". ");
        name.setText(date.getName().fullName);
        age.setText(date.getAge().value);
        gender.setText(date.getGender().value);
        height.setText(date.getHeight().value);
        income.setText(date.getIncome().value);
    }
}
