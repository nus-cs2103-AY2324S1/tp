package seedu.cc.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;

/**
 * Panel containing the medical history.
 */
public class MedicalHistoryPanel extends UiPart<Region> {
    private static final String FXML = "MedicalHistoryPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MedicalHistoryPanel.class);

    @javafx.fxml.FXML
    private ListView<MedicalHistoryEvent> medicalHistoryEventListView;

    /**
     * Creates a {@code MedicalHistoryPanel} with the given {@code ObservableList}.
     */
    public MedicalHistoryPanel(ObservableList<MedicalHistoryEvent> medicalHistory) {
        super(FXML);
        medicalHistoryEventListView.setItems(medicalHistory);
        medicalHistoryEventListView.setCellFactory(listView -> new MedicalHistoryPanel
                .MedicalHistoryEventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class MedicalHistoryEventListViewCell extends ListCell<MedicalHistoryEvent> {
        @Override
        protected void updateItem(MedicalHistoryEvent medicalHistory, boolean empty) {
            super.updateItem(medicalHistory, empty);

            if (empty || medicalHistory == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MedicalHistoryEventCard(medicalHistory, getIndex() + 1).getRoot());
            }
        }
    }
}
