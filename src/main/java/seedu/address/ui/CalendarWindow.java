package seedu.address.ui;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointments.Appointment;

/**
 * Controller for a calendar page.
 */
public class CalendarWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(CalendarWindow.class);

    private static CalendarWindow instance = null;
    private final CalendarView calendarView;
    private final List<Entry<Appointment>> calendarEntries = new ArrayList<>();

    /**
     * Creates a new CalendarWindow.
     *
     * @param root Stage to use as the root of the CalendarWindow.
     */
    public CalendarWindow(Stage root) {
        super(root);
        this.calendarView = new CalendarView();
        root.setScene(new Scene(calendarView));

        root.setWidth(800);
        root.setHeight(600);

        initialiseCalendarSettings();
    }

    /**
     * Creates a new CalendarWindow.
     */
    private CalendarWindow() {
        this(new Stage());
    }


    public static CalendarWindow getInstance() {
        if (instance == null) {
            instance = new CalendarWindow();
        }
        return instance;
    }

    /**
     * Sets the CalendarWindow instance.
     * This method should only be used in testing environments to create a mock or stub.
     * Caution in using this method as it can lead to unpredictable behavior if misused.
     *
     * @param newInstance The new instance of CalendarWindow to use as the singleton instance.
     */
    public static void setInstance(CalendarWindow newInstance) {
        instance = newInstance;
    }

    /**
     * Shows the calendar window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing Calendar of ToothTracker.");
        getCalendarRoot().show();
        getCalendarRoot().centerOnScreen();
    }

    /**
     * Returns true if the calendar window is currently being shown.
     */
    public boolean isShowing() {
        return getCalendarRoot().isShowing();
    }

    /**
     * Hides the calendar window.
     */
    public void hide() {
        getCalendarRoot().hide();
    }

    /**
     * Focuses on the calendar window.
     */
    public void focus() {
        getCalendarRoot().requestFocus();
    }

    private void initialiseCalendarSettings() {
        calendarView.setShowPageToolBarControls(false);
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowPrintButton(false);
        calendarView.setShowSourceTrayButton(false);
        calendarView.setShowSearchField(false);

        calendarView.showMonthPage(); // Default view is Month

        calendarView.setEntryEditPolicy(param -> false); // Disable drag and drop of events
        calendarView.setEntryFactory(param -> null); // Disable adding events by double-clicking on calendar
        calendarView.setEntryDetailsCallback(param -> null); // Disable pop up of event details
    }

    /**
     * Ensure there's an "Appointment" calendar and return it.
     * If it doesn't exist, create it and add to calendarView.
     */
    private Calendar getCalendar() {
        return calendarView.getCalendarSources().get(0).getCalendars().get(0);
    }

    /**
     * Converts an appointment to a CalendarFX entry.
     *
     * @param appointment Appointment to be converted.
     * @return Entry object representing the appointment.
     */
    private Entry<Appointment> convertToEntry(Appointment appointment) {
        Entry<Appointment> entry = new Entry<>();

        // Combine dentist and patient names for title
        String title = "[ID:" + appointment.getId() + "]"
                + " P: " + appointment.getPatientName() + ", D: " + appointment.getDentistName();
        entry.setTitle(title);

        // Set start and end times
        LocalDateTime startTime = appointment.getAppointmentTime().getStart();
        Duration duration = appointment.getAppointmentTime().getDuration();
        LocalDateTime endTime = startTime.plus(duration);
        entry.setInterval(startTime, endTime);

        entry.setLocation("Treatment: " + appointment.getTreatment() + " Cost: " + appointment.getCost());
        entry.setUserObject(appointment);
        return entry;
    }

    /**
     * Adds an appointment to the calendar.
     *
     * @param appointment Appointment to be added.
     */
    public void addAppointment(Appointment appointment) {
        Entry<Appointment> entry = convertToEntry(appointment);
        calendarEntries.add(entry);

        Calendar appointmentCalendar = getCalendar();
        appointmentCalendar.addEntry(entry);
    }

    /**
     * Loads a list of appointments into the calendar.
     *
     * @param appointments List of appointments to be loaded.
     */
    public void loadAppointments(List<Appointment> appointments) {
        Calendar appointmentCalendar = getCalendar();
        // Clear old entries
        appointmentCalendar.clear();

        for (Appointment appt : appointments) {
            Entry<Appointment> entry = convertToEntry(appt);
            calendarEntries.add(entry);
            appointmentCalendar.addEntry(entry);
        }

    }

    /**
     * Removes an appointment from the calendar.
     *
     * @param appointment Appointment to be removed.
     */
    public void deleteAppointment(Appointment appointment) {
        // Find the entry
        Entry<Appointment> entryToRemove = calendarEntries.stream()
                .filter(e -> e.getUserObject().equals(appointment))
                .findFirst()
                .orElse(null);

        if (entryToRemove == null) {
            logger.warning("No matching entry found for appointment: " + appointment);
            return;
        }
        entryToRemove.removeFromCalendar();
    }

}
