package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.department.Department;

/**
 * An UI component that displays information of a {@code Department}.
 */
public class DepartmentCard extends UiPart<Region> {

    private static final String FXML = "DepartmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Department department;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;

    /**
     * Creates a {@code DepartmentCode} with the given {@code Department} and index to display.
     */
    public DepartmentCard(Department department) {
        super(FXML);
        this.department = department;
        name.setText(department.getName());
    }
}
