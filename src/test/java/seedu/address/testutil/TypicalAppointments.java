package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment APPOINTMENT1 = new AppointmentBuilder()
            .withPatient(ALICE).withAppointmentTime("2023/10/10 10:00", "2023/10/10 11:00")
            .withDescription("Checkup").build();

    public static final Appointment APPOINTMENT2 = new AppointmentBuilder()
            .withPatient(BENSON).withAppointmentTime("2023/01/29 21:00", "2023/01/29 23:30")
            .withDescription("X-ray Checkup").build();

    // Manually added
    public static final Appointment APPOINTMENT3 = new AppointmentBuilder()
            .withPatient(HOON).withAppointmentTime("2021/04/18 15:00", "2023/04/18 17:30")
            .withDescription("Follow-up").build();

    public static final Appointment APPOINTMENT4 = new AppointmentBuilder()
            .withPatient(IDA).withAppointmentTime("2022/06/13 15:00", "2022/06/13 16:30")
            .withDescription("Follow-up").build();

}
