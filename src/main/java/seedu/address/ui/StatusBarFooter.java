package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    private ObservableList<Person> unfilteredList;
    private ObservableList<Person> filteredList;

    @FXML
    private Label saveLocationStatus;
    @FXML
    private Label studentCountStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation, ObservableList<Person> unfilteredList, ObservableList<Person> filteredList) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
        this.unfilteredList = unfilteredList;
        this.filteredList = filteredList;

        unfilteredList.addListener(new PersonListChangeListener());
        filteredList.addListener(new PersonListChangeListener());

        updateCountStatus();
    }

    private void updateCountStatus() {
        studentCountStatus.setText(String.format("%d of %d students", filteredList.size(), unfilteredList.size()));
    }

    private class PersonListChangeListener implements ListChangeListener<Person> {
        @Override
        public void onChanged(Change<? extends Person> change) {
            updateCountStatus();
        }
    }
}
