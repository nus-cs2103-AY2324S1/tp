package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_DEREK;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Ic;


/**
 * A utility class to help with building Person objects.
 */
public class AppointmentBuilder {
    public static final Ic DEFAULT_DOCTOR_IC = new Ic(VALID_NRIC_DEREK);
    public static final Ic DEFAULT_PATIENT_IC = new Ic(VALID_NRIC_BOB);
    public static final AppointmentTime DEFAULT_APPT_TIME = new AppointmentTime(VALID_DATE_1);
    private Ic doctorIc;
    private Ic patientIc;
    private AppointmentTime appointmentTime;

    /**
     * Constructor for the PersonBuilder class that initialises
     * a PersonBuilder instance populated with default values.
     */
    public AppointmentBuilder() {
        doctorIc = DEFAULT_DOCTOR_IC;
        patientIc = DEFAULT_PATIENT_IC;
        appointmentTime = DEFAULT_APPT_TIME;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        this.doctorIc = appointmentToCopy.getDoctor();
        this.patientIc = appointmentToCopy.getPatient();
        this.appointmentTime = appointmentToCopy.getAppointmentTime();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withDoctorIc(Ic doctorIc) {
        this.doctorIc = doctorIc;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withPatientIc(Ic patientIc) {
        this.patientIc = patientIc;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withAppointmentTime(AppointmentTime appointmentTime) {
        this.appointmentTime = appointmentTime;
        return this;
    }

    public Appointment build() {
        return new Appointment(doctorIc, patientIc, appointmentTime);
    }

}
