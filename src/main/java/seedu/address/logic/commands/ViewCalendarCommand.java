package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.appointments.Appointment;
import seedu.address.ui.CalendarWindow;

/**
 * Displays a calendar view window.
 */
public class ViewCalendarCommand extends Command {

    public static final String COMMAND_WORD = "view-calendar";

    public static final String SHOWING_CALENDAR_MESSAGE = "Calendar displayed.";

    private final CalendarWindow calendarWindow = CalendarWindow.getInstance();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Appointment> appointments = model.getFilteredAppointmentList();
        calendarWindow.loadAppointments(appointments);

        return new CommandResult(SHOWING_CALENDAR_MESSAGE, false, false, true);
    }
}

