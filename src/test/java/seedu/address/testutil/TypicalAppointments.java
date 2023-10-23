package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

public class TypicalAppointments {

    public static final Appointment APPOINTMENT1 = new AppointmentBuilder()
            .withPatient(ALICE).withAppointmentTime("2023/10/10 10:00", "2023/10/10 11:00")
            .withDescription("Checkup").build();

    public static final Appointment APPOINTMENT2 = new AppointmentBuilder()
            .withPatient(BENSON).withAppointmentTime("2023/01/29 21:00", "2023/01/29 23:30")
            .withDescription("Checkup").build();

}
