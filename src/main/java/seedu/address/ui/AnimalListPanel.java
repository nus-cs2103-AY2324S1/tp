package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.animal.Animal;

/**
 * Panel containing the list of animals.
 */
public class AnimalListPanel extends UiPart<Region> {

    private static final String FXML = "AnimalListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AnimalListPanel.class);
    private final AnimalDetailPanel animalDetailPanel;

    @FXML
    private ListView<Animal> animalListView;

    /**
     * Creates a AnimalListPanel with the given AnimalList.
     * @param animalList ObservableList of Animals
     * @param detailPanel detailedPanel of Animals
     */
    public AnimalListPanel(ObservableList<Animal> animalList, AnimalDetailPanel detailPanel) {
        super(FXML);
        this.animalDetailPanel = detailPanel;
        animalListView.setItems(animalList);
        animalListView.setCellFactory(listView -> new AnimalListViewCell());
        animalListView.getSelectionModel()
                .selectedItemProperty()
                .addListener((((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        detailPanel.updateDetails(newValue);
                    }
                }
                )));
    }

    public void selectAnimal(Animal animal) {
        animalListView.getSelectionModel().select(animal);
    }

    public void clearSelection() {
        animalListView.getSelectionModel().clearSelection();
    }

    static class AnimalListViewCell extends ListCell<Animal> {
        @Override
        protected void updateItem(Animal animal, boolean empty) {
            super.updateItem(animal, empty);

            if (empty || animal == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AnimalCard(animal, getIndex() + 1).getRoot());
            }
        }
    }

}
