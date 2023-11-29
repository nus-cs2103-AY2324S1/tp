package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentWithId;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointments.Appointment;
import seedu.address.testutil.AppointmentBuilder;

public class ListAppointmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Appointment appointmentOne = new AppointmentBuilder()
                .withAppointmentTime("2023-10-21T16:00", "PT1H30M")
                .withId("1").build();
        Appointment appointmentTwo = new AppointmentBuilder()
                .withAppointmentTime("2023-10-22T12:00", "PT2H00M")
                .withId("2").build();

        model.addAppointment(appointmentOne);
        model.addAppointment(appointmentTwo);
        expectedModel.addAppointment(appointmentOne);
        expectedModel.addAppointment(appointmentTwo);
    }

    @Test
    public void execute_appointmentListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAppointmentCommand(), model,
                ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_appointmentListIsFiltered_showsEverything() {
        showAppointmentWithId(model, 1);
        assertCommandSuccess(new ListAppointmentCommand(), model,
                ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
