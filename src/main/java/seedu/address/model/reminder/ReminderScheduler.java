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
        long initialDelay = ChronoUnit.MILLIS.between(now, nextMidnight);

        // Start the scheduler to wake up the ReminderScheduler occasionally
        scheduler.scheduleAtFixedRate(() -> {
            synchronized (mutex) {
                mutex.notifyAll();
            }
        }, initialDelay, FREQUENCY, TimeUnit.MINUTES); // Wakes up Day
    }

    @Override
    public void run() {
        while (true) {
            synchronized (mutex) {
                try {
                    mutex.wait();
                    logger.info("ReminderScheduler thread woken up");
                    //TODO: @zhyuhan Rather than changing the reminderlist which could cause thread access issues
                    //(ie someone just nice update the reminderlist at the same time)),
                    //Change this to just update the Dashboard/Reminder UI with reminders after for the new day
                    model.getReminderList().updateReminders();
                } catch (InterruptedException e) {
                    logger.info("ReminderScheduler thread interrupted");
                    break;
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
