package connexion.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import connexion.commons.core.LogsCenter;
import connexion.model.person.Person;
import connexion.model.person.Schedule;
import connexion.model.tag.Tag;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;



/**
 * Panel containing the list of persons.
 */
public class PersonViewPanel extends UiPart<Region> {
    private static final String FXML = "PersonViewPanel.fxml";

    public final Person person;

    private final Logger logger = LogsCenter.getLogger(PersonViewPanel.class);

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label company;
    @FXML
    private Label job;
    @FXML
    private Label email;
    @FXML
    private Label markStatus;
    @FXML
    private FlowPane tags;
    @FXML
    private Label lastModifiedDateTime;

    @FXML
    private Label noteHeader;
    @FXML
    private Label note;

    @FXML
    private Label schedule;
    @FXML
    private Label scheduleName;

    /**
     * Creates a {@code PersonViewPanel} with the given {@code Person} and index to display.
     */
    public PersonViewPanel(Person person) {
        super(FXML);
        if (person == null) {
            this.person = null;
            this.noteHeader.setText("");
            this.name.setText("To view a person's detail, type 'detail INDEX'");
        } else {
            this.person = person;
            name.setText(person.getName().getDetailString());
            phone.setText(person.getPhone().getDetailString());
            company.setText(person.getCompany().getDetailString());
            job.setText(person.getJob().getDetailString());
            email.setText(person.getEmail().getDetailString());
            markStatus.setText(person.getMarkStatus().toString());
            person.getTags().stream()
                    .sorted(Comparator.comparing(Tag::getValue))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.getListString())));
            lastModifiedDateTime.setText(
                    String.format("Last modified : %s", person.getLastModifiedDateTime().toString()));
            schedule.setText(person.getSchedule()
                    .map(Schedule::getDetailString).orElse(""));
            scheduleName.setText(person.getScheduleName()
                    .map(Object::toString).orElse("No scheduled meetings with this person yet"));
            note.setText(person.getNote().getDetailString());
        }
    }
}
