package seedu.address.ui;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;


/**
 * Panel containing the list of calendar rows which itself is made up of tutors and their assigned schedules.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    @FXML
    private ListView<PersonWithSchedules> calendarListView;
    @FXML
    private ListView<Label> timeListView;

    private final ObservableList<PersonWithSchedules> personWithSchedulesList;

    /**
     * Creates a {@code CalendarPanel} with the given personList and scheduleList.
     */
    public CalendarPanel(ObservableList<Person> personList, ObservableList<Schedule> scheduleList) {
        super(FXML);

        personWithSchedulesList = FXCollections.observableArrayList();
        createPersonWithSchedulesList(personList, scheduleList);

        calendarListView.setItems(personWithSchedulesList);
        calendarListView.setCellFactory(listView -> new CalendarRowViewCell());
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
        personList
            .addListener((ListChangeListener.Change<? extends Person> c) -> updateList(personList, scheduleList));
        scheduleList
            .addListener((ListChangeListener.Change<? extends Schedule> c) ->updateList(personList, scheduleList));

        updateList(personList, scheduleList);
    }

    private void updateList(ObservableList<Person> personList,
        ObservableList<Schedule> scheduleList) {
        personWithSchedulesList.clear();
        createTimetableLabels();
        for (Person person : personList) {
            List<Pair<Schedule, Index>> schedules = new ArrayList<>();
            for (int i = 0; i < scheduleList.size(); i++) {
                Schedule schedule = scheduleList.get(i);
                if (schedule.getTutor().equals(person)) {
                    schedules.add(new Pair<>(schedule, Index.fromZeroBased(i)));
                }
            }
            personWithSchedulesList.add(new PersonWithSchedules(person, schedules, null));
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a tutor and their assigned schedules using a
     * {@code ScheduleCard}.
     */
    class CalendarRowViewCell extends ListCell<PersonWithSchedules> {
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
        private final List<Pair<Schedule, Index>> schedules;
        private final List<Label> timeLabels;

        /**
         * Creates a {@code PersonWithSchedules} with
         * the given {@code Person}, schedules and timeLabels.
         */
        public PersonWithSchedules(Person person, List<Pair<Schedule, Index>> schedules, List<Label> timeLabels) {
            this.person = person;
            this.schedules = schedules;
            this.timeLabels = timeLabels;
        }

        public Person getPerson() {
            return person;
        }

        public List<Pair<Schedule, Index>> getSchedules() {
            return schedules;
        }

        public List<Label> getTimeLabels() {
            return timeLabels;
        }
    }

}
