package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ReminderSchedulerTest {

    private Model model;
private Object mutex = new Object();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void threadStartAndShutdownTest() throws Exception{
        ReminderScheduler reminderScheduler = new ReminderScheduler(model, mutex);

        reminderScheduler.start();
        assertTrue(reminderScheduler.isAlive());

        // Test that a second start() call does not create a new thread (this wll test the isRunning field)
        assertDoesNotThrow(() -> reminderScheduler.start());

        // Access the private scheduler field
        Field schedulerField = ReminderScheduler.class.getDeclaredField("scheduler");
        schedulerField.setAccessible(true);
        ExecutorService scheduler = (ExecutorService) schedulerField.get(reminderScheduler);

        reminderScheduler.shutdown();
        assertTrue(scheduler.isShutdown());

        // Access the private isRunning field to make sure it shuts down
        Field isRunningField = ReminderScheduler.class.getDeclaredField("isRunning");
        isRunningField.setAccessible(true);
        boolean isRunning = (boolean) isRunningField.get(reminderScheduler);
        
        assertTrue(!isRunning);

        // Wait for the thread to die
        Thread.sleep(1000);

        assertTrue(!reminderScheduler.isAlive());
    }

    
}
