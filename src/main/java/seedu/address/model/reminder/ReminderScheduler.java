package seedu.address.model.reminder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;
/**
 * Represents a thread that manages the reminders.
 */
public class ReminderScheduler extends Thread {
    private static final long FREQUENCY = TimeUnit.DAYS.toMinutes(1); // Frequency of a Day in minutes

    private final Model model;
    private final Object mutex;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private Logger logger = LogsCenter.getLogger(ReminderScheduler.class);

    //This is defensive programming to prevent multiple ReminderScheduler from running
    private boolean isRunning = false;

    /**
     * Creates a ReminderScheduler object.
     *
     * @param taskQueue
     * @param reminderMutex
     */
    public ReminderScheduler(Model model, Object reminderMutex) {
        this.mutex = reminderMutex;
        this.model = model;
    }

    /**
     * Starts the ReminderScheduler thread.
     */
    public void start() {
        if (!isRunning) {
            super.start();
            isRunning = true;
        }

        // Calculate the initial delay until 00:00
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIDNIGHT);
        // +1 to ensure that the scheduler will run just after midnight
        long initialDelay = ChronoUnit.MILLIS.between(now, nextMidnight) + 1;

        // Start the scheduler to wake up the ReminderScheduler occasionally
        scheduler.scheduleAtFixedRate(() -> {
            synchronized (mutex) {
                mutex.notifyAll();
            }
        }, initialDelay, FREQUENCY, TimeUnit.MINUTES); // Wakes up Day
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (mutex) {
                try {
                    mutex.wait();
                    logger.info("ReminderScheduler thread woken up");
                    model.updateReminderList();
                } catch (InterruptedException e) {
                    logger.info("ReminderScheduler thread interrupted");
                    return;
                }
            }
        }
    }

    /**
     * Shuts down the ReminderScheduler thread.
     */
    public void shutdown() {
        isRunning = false;
        scheduler.shutdownNow();
        this.interrupt();
    }

}
