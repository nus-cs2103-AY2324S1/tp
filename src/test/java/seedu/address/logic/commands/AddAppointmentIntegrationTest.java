package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalDoctor.DEREK;
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
import seedu.address.model.person.Person;
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
        //expectedModel.addAppointment(validAppointment);

        assertCommandSuccess(new AddAppointmentCommand(validAppointment), model,
                String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                expectedModel);

        Set<Appointment> patientAppointments = bob.getAppointments();
        Set<Appointment> doctorAppointments = derek.getAppointments();
        assertTrue(patientAppointments.contains(validAppointment));
        assertTrue(doctorAppointments.contains(validAppointment));
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPatientList().get(0);
        assertCommandFailure(new AddCommand(personInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
