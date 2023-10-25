package seedu.address.ui;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;


/**
 * Panel containing the list of persons.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    @FXML
    private ListView<PersonWithSchedules> personListView;
    @FXML
    private ListView<Label> timeListView;

    private ObservableList<PersonWithSchedules> personWithSchedulesList;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public CalendarPanel(ObservableList<Person> personList, ObservableList<Schedule> scheduleList) {
        super(FXML);

        personWithSchedulesList = FXCollections.observableArrayList();
        createPersonWithSchedulesList(personList, scheduleList);

        personListView.setItems(personWithSchedulesList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    private void createTimetableLabels() {
        ObservableList<Label> timeLabels = FXCollections.observableArrayList();

        LocalTime currentTime = LocalTime.of(0, 0);
        for (int i = 0; i < 24 * 2; i++) {
            Label label = new Label(currentTime.toString());
            timeLabels.add(label);
            currentTime = currentTime.plusMinutes(30);
        }

        personWithSchedulesList.add(0, new PersonWithSchedules(null, null, timeLabels));
    }

    private void createPersonWithSchedulesList(ObservableList<Person> personList,
        ObservableList<Schedule> scheduleList) {
        ListProperty<Person> observablePersonList = new SimpleListProperty<>(personList);
        ListProperty<Schedule> observableScheduleList = new SimpleListProperty<>(scheduleList);

        observablePersonList
            .addListener((ListChangeListener.Change<? extends Person> c) -> updateList(personList, scheduleList));
        observableScheduleList
            .addListener((ListChangeListener.Change<? extends Schedule> c) ->updateList(personList, scheduleList));

        updateList(personList, scheduleList);
    }

    private void updateList(ObservableList<Person> personList,
        ObservableList<Schedule> scheduleList) {
        personWithSchedulesList.clear();
        createTimetableLabels();
        for (Person person : personList) {
            List<Schedule> schedules = new ArrayList<>();
            for (Schedule schedule : scheduleList) {
                if (schedule.getTutor().equals(person)) {
                    schedules.add(schedule);
                }
            }
            personWithSchedulesList.add(new PersonWithSchedules(person, schedules, null));
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<PersonWithSchedules> {
        @Override
        protected void updateItem(PersonWithSchedules personWithSchedules, boolean empty) {
            super.updateItem(personWithSchedules, empty);
            if (empty || personWithSchedules == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (personWithSchedules.getTimeLabels() != null) {
                    setGraphic(new CalendarRow(personWithSchedules.getTimeLabels()).getRoot());
                } else {
                    setGraphic(new CalendarRow(personWithSchedules, getIndex() + 1).getRoot());
                }
            }
        }
    }

    /**
     * Packages person and schedules together.
     */
    public final class PersonWithSchedules {
        private final Person person;
        private final List<Schedule> schedules;
        private final List<Label> timeLabels;

        /**
         * Creates a {@code PersonWithSchedules} with
         * the given {@code Person}, schedules and timeLabels.
         */
        public PersonWithSchedules(Person person, List<Schedule> schedules, List<Label> timeLabels) {
            this.person = person;
            this.schedules = schedules;
            this.timeLabels = timeLabels;
        }

        public Person getPerson() {
            return person;
        }

        public List<Schedule> getSchedules() {
            return schedules;
        }

        public List<Label> getTimeLabels() {
            return timeLabels;
        }
    }

}
