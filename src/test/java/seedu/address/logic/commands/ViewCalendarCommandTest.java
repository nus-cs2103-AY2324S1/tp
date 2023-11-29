package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.appointments.Appointment;
import seedu.address.ui.CalendarWindow;

public class ViewCalendarCommandTest {

    private Model model;
    private CalendarWindow calendarWindowMock;

    @BeforeEach
    public void setUp() {
        model = mock(Model.class);
        calendarWindowMock = mock(CalendarWindow.class);
        CalendarWindow.setInstance(calendarWindowMock);
    }

    @Test
    public void execute_calendarOpened() {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        when(model.getFilteredAppointmentList()).thenReturn(appointments);

        ViewCalendarCommand viewCalendarCommand = new ViewCalendarCommand();

        CommandResult commandResult = viewCalendarCommand.execute(model);

        assertEquals(ViewCalendarCommand.SHOWING_CALENDAR_MESSAGE, commandResult.getFeedbackToUser());
        verify(calendarWindowMock).loadAppointments(appointments);
    }
}
