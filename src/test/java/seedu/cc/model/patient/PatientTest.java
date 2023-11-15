package seedu.cc.model.patient;

//import static org.junit.jupiter.api.Assertions.assertEquals;x
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.cc.model.appointment.PatientAppointmentList;
import seedu.cc.model.medicalhistory.PatientMedicalHistory;
import seedu.cc.model.person.Age;
import seedu.cc.model.person.Email;
import seedu.cc.model.person.Name;
import seedu.cc.model.person.Phone;
import seedu.cc.model.tag.Tag;

public class PatientTest {
    private Patient patient;
    private Patient patientEmptyAppointment;
    private Patient patientEmptyMedicalHistory;
    private final PatientAppointmentList patientAppointmentList = new PatientAppointmentList();
    private final PatientMedicalHistory patientMedicalHistory = new PatientMedicalHistory();

    @BeforeEach
    public void setUp() {
        Name name = new Name("John Doe");
        Nric nric = new Nric("M1234567A");
        Phone phone = new Phone("98765432");
        Email email = new Email("johndoe@example.com");
        Age age = new Age("12");
        Set<Tag> tags = new HashSet<>();

        // TODO: Add appointment and medical history
        patient = new Patient(name, nric, phone, email, age, patientAppointmentList, patientMedicalHistory, tags);
        patientEmptyAppointment = new Patient(name, nric, phone, email, age, patientAppointmentList, tags);
        patientEmptyMedicalHistory = new Patient(name, nric, phone, email, age, patientMedicalHistory, tags);
    }

    @Test
    public void isSamePatient_sameNric_returnsTrue() {
        Patient anotherPatient = new Patient(patient.getName(), patient.getNric(), patient.getPhone(),
                patient.getEmail(), patient.getAge(),
                patientAppointmentList, patientMedicalHistory, patient.getTags());
        assertTrue(patient.isSamePatient(anotherPatient));
    }

    @Test
    public void isSamePatient_differentNric_returnsFalse() {
        Nric diffNric = new Nric("M7654321B");
        Patient anotherPatient = new Patient(patient.getName(), diffNric, patient.getPhone(),
                patient.getEmail(), patient.getAge(), patient.getTags());
        assertFalse(patient.isSamePatient(anotherPatient));
    }
    //    @Test
    //    public void getMedicalHistory_returnsCorrectMedicalHistory() {
    //        assertEquals(patientAppointmentList, patientEmptyMedicalHistory.getPatientMedicalHistory());
    //    }
    //
    //    @Test
    //    public void getAppointmentList_returnsCorrectAppointmentList() {
    //        assertEquals(patientMedicalHistory, patientEmptyAppointment.getPatientAppointmentList());
    //    }
}
