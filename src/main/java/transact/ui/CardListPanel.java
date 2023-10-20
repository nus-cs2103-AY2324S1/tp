package transact.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import transact.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class CardListPanel extends UiPart<Region> {
    private static final String FXML = "CardListPanel.fxml";

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public CardListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new ItemListViewCell());
        personList.addListener((ListChangeListener<Person>) c -> {
            personListView.setVisible(!personList.isEmpty());
            personListView.setManaged(!personList.isEmpty());
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using
     * a {@code PersonCard}.
     */
    class ItemListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, person.getPersonId().value).getRoot());
            }
        }
    }
}
