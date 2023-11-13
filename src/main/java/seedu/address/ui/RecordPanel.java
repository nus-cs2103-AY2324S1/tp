package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.address.model.patient.Patient;

/**
 * A ui for the record panel that is displayed on the right side of the application.
 */
public class RecordPanel extends UiPart<Region> {

    private static final String FXML = "RecordPanel.fxml";

    private PatientListPanel patientListPanel;
    private int lastSelectedIndex;
    @FXML
    private AnchorPane recordView;

    /**
     * Creates a {@code RecordPanel} with the given {@code PatientListPanel}.
     */
    public RecordPanel(PatientListPanel patientList) {
        super(FXML);
        patientListPanel = patientList;
        patientListPanel.getPatientListView().getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Patient>() {
                    @Override
                    public void changed(ObservableValue<? extends Patient> observable,
                                        Patient oldValue, Patient newValue) {
                        if (newValue == null) {
                            clearRecordPanel();
                            return;
                        }
                        if (!newValue.equals(oldValue)) {
                            displayRecord(newValue);
                        }
                    }
                }
        );
    }

    private void clearRecordPanel() {
        recordView.getChildren().clear();
    }

    private void displayRecord(Patient patient) {
        requireNonNull(patient);
        recordView.getChildren().clear();
        Node record = new RecordCard(patient.getRecord()).getRoot();
        recordView.getChildren().add(record);
        setAnchorsFlush(record);
    }

    private void setAnchorsFlush(Node child) {
        AnchorPane.setBottomAnchor(child, 0.0);
        AnchorPane.setLeftAnchor(child, 0.0);
        AnchorPane.setRightAnchor(child, 0.0);
        AnchorPane.setTopAnchor(child, 0.0);
    }
}
