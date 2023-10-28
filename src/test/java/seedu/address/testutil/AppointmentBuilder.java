package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.testutil.TypicalDoctor.DEREK;
import static seedu.address.testutil.TypicalPatient.BENSON;

import java.time.LocalDateTime;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;



/**
 * A utility class to help with building Person objects.
 */
public class AppointmentBuilder {
    public static final Doctor DEFAULT_DOCTOR = DEREK;
    public static final Patient DEFAULT_PATIENT = BENSON;
    public static final LocalDateTime DEFAULT_APPT_TIME = VALID_DATE_1;
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime appointmentTime;
    private String status;

    /**
     * Constructor for the PersonBuilder class that initialises
     * a PersonBuilder instance populated with default values.
     */
    public AppointmentBuilder() {
        doctor = DEFAULT_DOCTOR;
        patient = DEFAULT_PATIENT;
        appointmentTime = DEFAULT_APPT_TIME;
        status = "Scheduled";
    }
    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        this.doctor = appointmentToCopy.getDoctor();
        this.patient = appointmentToCopy.getPatient();
        this.appointmentTime = appointmentToCopy.getAppointmentTime();
        this.status = appointmentToCopy.getStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
        return this;
    }

    public Appointment build() {
        return new Appointment(doctor, patient, appointmentTime, status);
    }

}
