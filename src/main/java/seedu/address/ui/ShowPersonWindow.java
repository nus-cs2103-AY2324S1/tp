package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.address.model.person.Person;

/**
 * Controller for a Show Person Details page
 */
public class ShowPersonWindow extends UiPart<Stage> {

    private static final String FXML = "ShowPersonWindow.fxml";

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private FlowPane subjects;
    @FXML
    private FlowPane tags;

    /**
     * Creates a new ShowPersonWindow.
     *
     * @param root Stage to use as the root of the ShowPersonWindow.
     */
    public ShowPersonWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new ShowPersonWindow.
     */
    public ShowPersonWindow() {
        this(new Stage());
    }

    /**
     * Sets the Details of the person to be shown.
     *
     * @param person The person whose details are to be shown.
     */
    public void setPersonDetails(Person person) {
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getSubjects().stream()
                .sorted(Comparator.comparing(subject -> subject.subjectName))
                .forEach(subject -> subjects.getChildren()
                        .add(new ColoredTextEntry(subject.subjectName.toString(), subject.getColour())));
    }


    /**
     * Shows the Show Person window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the Show Person window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Show Person window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Show Person window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
