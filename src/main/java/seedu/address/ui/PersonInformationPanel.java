package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}
 */
public class PersonInformationPanel extends UiPart<Region> {
    private static final String FXML = "PersonInformationPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private final Person person;

    @FXML
    private Label name;
    @FXML
    private FlowPane tags;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private Label linkedIn;
    @FXML
    private Label github;


    /**
     * Creates a {@code PersonInformationPanel} with the given {@code Person}.
     * @param person the person to display
     */
    public PersonInformationPanel(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        remark.setText(person.getRemark().value);
        person.getTags().stream()
                .sorted(java.util.Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        linkedIn.setText(person.getLinkedIn().value);
        github.setText(person.getGithub().value);

    }




}
