package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private Logic logic;
    private BooleanProperty reRenderUi;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        this.reRenderUi = logic.getReRenderUi();
        personListView.setItems(logic.getFilteredPersonList());
        personListView.setCellFactory(listView -> new PersonListViewCell());
        reRenderUi.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("refreshUI is called in personlistpanel");
                refreshUI();
            }
        });

    }

    private void refreshUI() {
        // Clear and re-set the items in the ListView
        personListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            System.out.println("updateItem is called in personlistpanel");
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                //logic.setDisplayedFieldsList(new String[]{"phone", "email", "address", "tags", "subjects"});
                setGraphic(new PersonCard(person, getIndex() + 1, logic.getDisplayedFieldsList()).getRoot());
            }
        }
    }

}
