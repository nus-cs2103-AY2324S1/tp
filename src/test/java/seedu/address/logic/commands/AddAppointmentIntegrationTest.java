package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalDoctor.CHERYL;
import static seedu.address.testutil.TypicalDoctor.DEREK;
import static seedu.address.testutil.TypicalPatient.AMY;
import static seedu.address.testutil.TypicalPatient.BOB;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddAppointmentIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newAppointment_success() {
        Doctor derek = new DoctorBuilder(DEREK).build();
        Patient bob = new PatientBuilder(BOB).build();
        model.addPerson(derek);
        model.addPerson(bob);
        Appointment validAppointment =
                new AppointmentBuilder().withDoctorIc(derek.getIc()).withPatientIc(bob.getIc()).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addAppointment(validAppointment);

        assertCommandSuccess(new AddAppointmentCommand(validAppointment), model,
                String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                expectedModel);

        // check that the appointments have been added to all patients, doctors and appointment list
        Set<Appointment> patientAppointments = bob.getAppointments();
        Set<Appointment> doctorAppointments = derek.getAppointments();
        assertTrue(patientAppointments.contains(validAppointment));
        assertTrue(doctorAppointments.contains(validAppointment));
        assertTrue(model.getFilteredAppointmentList().contains(validAppointment));
    }

    @Test
    public void execute_appointmentWithPatientNotInModel_throwsCommandException() {
        Doctor derek = new DoctorBuilder(DEREK).build();
        Patient bob = new PatientBuilder(BOB).build();
        // only add doctor
        model.addPerson(derek);
        Appointment invalidAppointment =
                new AppointmentBuilder().withDoctorIc(derek.getIc()).withPatientIc(bob.getIc()).build();

        assertCommandFailure(new AddAppointmentCommand(invalidAppointment), model,
                AddAppointmentCommand.MESSAGE_INVALID_PATIENT);
    }

    @Test
    public void execute_appointmentWithDoctorNotInModel_throwsCommandException() {
        Doctor derek = new DoctorBuilder(DEREK).build();
        Patient bob = new PatientBuilder(BOB).build();
        // only add patient
        model.addPerson(bob);
        Appointment invalidAppointment =
                new AppointmentBuilder().withDoctorIc(derek.getIc()).withPatientIc(bob.getIc()).build();

        assertCommandFailure(new AddAppointmentCommand(invalidAppointment), model,
                AddAppointmentCommand.MESSAGE_INVALID_DOCTOR);
    }

    @Test
    public void execute_appointmentWithDoctorHasAppointmentAtTheSameTime_throwsCommandException() {
        Doctor derek = new DoctorBuilder(DEREK).build();
        Patient bob = new PatientBuilder(BOB).build();
        model.addPerson(derek);
        model.addPerson(bob);
        Appointment validAppointment =
                new AppointmentBuilder().withDoctorIc(derek.getIc()).withPatientIc(bob.getIc()).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addAppointment(validAppointment);

        assertCommandSuccess(new AddAppointmentCommand(validAppointment), model,
                String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                expectedModel);

        Patient amy = new PatientBuilder(AMY).build();
        model.addPerson(amy);
        // create another appointment between Amy and Doctor Derek at the same time
        Appointment invalidAppointment =
                new AppointmentBuilder().withDoctorIc(derek.getIc()).withPatientIc(amy.getIc()).build();

        assertCommandFailure(new AddAppointmentCommand(invalidAppointment), model,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT_DOCTOR);
    }

    @Test
    public void execute_appointmentWithPatientHasAppointmentAtTheSameTime_throwsCommandException() {
        Doctor derek = new DoctorBuilder(DEREK).build();
        Patient bob = new PatientBuilder(BOB).build();
        model.addPerson(derek);
        model.addPerson(bob);
        Appointment validAppointment =
                new AppointmentBuilder().withDoctorIc(derek.getIc()).withPatientIc(bob.getIc()).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addAppointment(validAppointment);

        assertCommandSuccess(new AddAppointmentCommand(validAppointment), model,
                String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                expectedModel);

        Doctor cheryl = new DoctorBuilder(CHERYL).build();
        model.addPerson(cheryl);
        // create another appointment between patient Bob and Doctor cheryl at the same time
        Appointment invalidAppointment =
                new AppointmentBuilder().withDoctorIc(cheryl.getIc()).withPatientIc(bob.getIc()).build();

        assertCommandFailure(new AddAppointmentCommand(invalidAppointment), model,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT_PATIENT);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Doctor derek = new DoctorBuilder(DEREK).build();
        Patient bob = new PatientBuilder(BOB).build();
        model.addPerson(derek);
        model.addPerson(bob);
        Appointment validAppointment =
                new AppointmentBuilder().withDoctorIc(derek.getIc()).withPatientIc(bob.getIc()).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addAppointment(validAppointment);

        assertCommandSuccess(new AddAppointmentCommand(validAppointment), model,
                String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                expectedModel);

        // try to add the same appointment again will give an error
        assertCommandFailure(new AddAppointmentCommand(validAppointment), model,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT_PATIENT);
    }

}
