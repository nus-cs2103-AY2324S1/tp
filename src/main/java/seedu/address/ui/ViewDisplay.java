package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.scene.layout.StackPane;
import seedu.address.model.company.Company;
import seedu.address.model.person.Person;

/**
 * A ui for a large viewing area that is displayed at the bottom right of the application.
 */
public class ViewDisplay extends StackPane {

    private static final String FXML = "ViewDisplay.fxml";

    public ViewDisplay() {
        super();
    }

    /**
     * Displays a {@code Person} in the display box.
     *
     * @param person The person to be displayed.
     */
    public void displayEntity(Person person) {
        requireNonNull(person);
        this.getChildren().clear();
        this.getChildren().add(new ViewPersonDisplay(person).getRoot());
    }

    /**
     * Displays a {@code Company} in the display box.
     *
     * @param company The company to be displayed.
     */
    public void displayEntity(Company company) {
        requireNonNull(company);
        this.getChildren().clear();
        this.getChildren().add(new ViewCompanyDisplay(company).getRoot());
    }

}
