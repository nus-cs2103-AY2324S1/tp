package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.reminder.Reminder;
import seedu.address.testutil.PersonBuilder;

public class DashboardTest {

    private static final Person ALICE = new PersonBuilder()
            .withName("Alice")
            .withInteractions(List.of(new Interaction("note")))
            .build();

    private Model model = new ModelManager();
    private Dashboard dashboard = new Dashboard(model);

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        dashboard = new Dashboard(model);
    }

    @Test
    public void getTotalInteraction_change_success() {
        int totalInteraction = model.getAddressBook().getPersonList().stream()
                .mapToInt(person -> person.getInteractions().size()).sum();
        assertEquals(totalInteraction, dashboard.getTotalInteraction());

        model.addPerson(ALICE);
        int initAliceInteractions = ALICE.getInteractions().size();
        Person alice = model.getAddressBook().getPersonList().stream()
                .filter(person -> person.equals(ALICE)).findFirst().get();
        List<Interaction> aliceInteractions = alice.getInteractions();
        aliceInteractions.add(new Interaction("note"));

        assertEquals(initAliceInteractions + 1, dashboard.getTotalInteraction());
    }

    @Test
    public void openAndCloseDashboard_dashboardStateChanges() {
        assertFalse(dashboard.isDashboardOpen());
        dashboard.openDashboard();
        assertTrue(dashboard.isDashboardOpen());
        dashboard.closeDashboard();
        assertFalse(dashboard.isDashboardOpen());
    }

    @Test
    public void getReminderList_validReminders_correctListReturned() {
        ObservableList<Reminder> reminderListCopy = FXCollections.observableArrayList();
        ObservableList<Reminder> reminderList = dashboard.getReminderList();
        reminderListCopy.addAll(reminderList);

        assertEquals(reminderListCopy, reminderList);

        model.addPerson(ALICE);

        // New person added and interaction added to that person
        assertEquals(reminderListCopy, reminderList);

        // Right now, the dirty status is always on, so the reminder list will always be updated
        assertNotEquals(reminderListCopy, dashboard.getReminderList());
    }

    @Test
    public void equals_sameModel_true() {
        Dashboard dashboard1 = new Dashboard(model);
        assertEquals(dashboard, dashboard1);
    }

    @Test
    public void equals_differentModel_false() {
        Model model1 = new ModelManager();
        model1.addPerson(ALICE);
        Dashboard dashboard1 = new Dashboard(model1);
        assertNotEquals(dashboard, dashboard1);
    }
}
