package seedu.address.ui;

import java.util.stream.Collectors;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Patient;

/**
 * Panel containing the patients details.
 */
public class ViewPatientPanel extends ViewPersonPanel {
    private static final String card = "ViewPatientPanel.fxml";

    @FXML
    private Label age;

    @FXML
    private ListView<MedicalHistory> medicalHistoryListView;
    /**
     * Creates a {@code PatientCard} with the given {@code patient} and index to display.
     */
    public ViewPatientPanel(Patient patient) {
        super(patient, card);
        age.setText(": " + patient.getAge().value);
        medicalHistoryListView.setItems(new ObservableListWrapper<>(patient.getMedicalHistory()
                .stream().collect(Collectors.toList())));
        medicalHistoryListView.setCellFactory(listView -> new MedicalHistoryViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class MedicalHistoryViewCell extends ListCell<MedicalHistory> {
        @Override
        protected void updateItem(MedicalHistory medicalHistory, boolean empty) {
            super.updateItem(medicalHistory, empty);

            if (empty || medicalHistory == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MedicalHistoryView(medicalHistory, getIndex() + 1).getRoot());
            }
        }
    }
}
