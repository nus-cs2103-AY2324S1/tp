package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.address.model.person.Person;

public class ShowPersonPanel extends UiPart<Region> {

    private static final String FXML = "ShowPersonPanel.fxml";
    private Person person;


    @FXML
    private HBox cardPane;
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

    public ShowPersonPanel(Person person) {
        super(FXML);
        this.person = person;
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

}
