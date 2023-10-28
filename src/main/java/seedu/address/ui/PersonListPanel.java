package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Specialist;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;
    @FXML
    private Label personType;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        // Set the text initially to handle the case when personList is empty
        if (personList.isEmpty()) {
            personType.setText("NO DATA");
        } else if (personList.get(0) instanceof Patient) {
            personType.setText("Patient");
        } else {
            personType.setText("Specialist");
        }

        // set the text fill color after JavaFX initialization to prevent race condition
        Platform.runLater(() -> {
            if (personList.isEmpty()) {
                personType.setTextFill(Color.GREY);
            } else if (personList.get(0) instanceof Patient) {
                personType.setTextFill(Color.AQUA);
            } else {
                personType.setTextFill(Color.GREENYELLOW);
            }
        });
        personList.addListener(
                new ListChangeListener<Person>() {
                    @Override
                    public void onChanged(Change<? extends Person> c) {
                        if (personList.isEmpty()) {
                            personType.setText("NO DATA");
                            personType.setTextFill(Color.GREY);
                        } else if (personList.get(0) instanceof Patient) {
                            personType.setText("Patient");
                            personType.setTextFill(Color.AQUA);
                        } else {
                            personType.setText("Specialist");
                            personType.setTextFill(Color.GREENYELLOW);
                        }
                    }
                }
        );
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);
            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (person instanceof Patient) {
                    setGraphic(new PatientCard((Patient) person, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new SpecialistCard((Specialist) person, getIndex() + 1).getRoot());
                }
            }
        }
    }

}
