package seedu.address.testutil;

import seedu.address.model.appointments.Appointment;
import seedu.address.model.appointments.AppointmentTime;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_DENTIST_ID = "1";
    public static final String DEFAULT_PATIENT_ID = "1";
    public static final String DEFAULT_DENTIST_NAME = "Alice";
    public static final String DEFAULT_PATIENT_NAME = "Amy";
    public static final String DEFAULT_START = "2023-10-19T16:00";
    public static final String DEFAULT_DURATION = "PT10H30M";
    public static final String DEFAULT_TREATMENT = "Braces";
    public static final String DEFAULT_COST = "100";

    private long dentistId;
    private long patientId;
    private String dentistName;
    private String patientName;
    private String start;
    private String duration;
    private AppointmentTime appointmentTime;
    private String treatment;
    private String cost;
    private long id;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        dentistId = Long.parseLong(DEFAULT_DENTIST_ID);
        patientId = Long.parseLong(DEFAULT_PATIENT_ID);
        dentistName = DEFAULT_DENTIST_NAME;
        patientName = DEFAULT_PATIENT_NAME;
        start = DEFAULT_START;
        duration = DEFAULT_DURATION;
        appointmentTime = new AppointmentTime(DEFAULT_START, DEFAULT_DURATION);
        treatment = DEFAULT_TREATMENT;
        cost = DEFAULT_COST;
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     *
     * @param appointmentToCopy The appointment used to initialize the AppointmentBuilder.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        dentistId = appointmentToCopy.getDentistId();
        patientId = appointmentToCopy.getPatientId();
        dentistName = appointmentToCopy.getDentistName();
        patientName = appointmentToCopy.getPatientName();
        start = appointmentToCopy.getStart();
        duration = appointmentToCopy.getDuration();
        appointmentTime = appointmentToCopy.getAppointmentTime();
        treatment = appointmentToCopy.getTreatment();
        cost = appointmentToCopy.getCost();
    }

    /**
     * Sets the {@code dentistId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDentistId(String dentistId) {
        this.dentistId = Long.parseLong(dentistId);
        return this;
    }

    /**
     * Sets the {@code patientId} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientId(String patientId) {
        this.patientId = Long.parseLong(patientId);
        return this;
    }

    /**
     * Sets the {@code dentistName} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDentistName(String dentistName) {
        this.dentistName = dentistName;
        return this;
    }

    /**
     * Sets the {@code patientName} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientName(String patientName) {
        this.patientName = patientName;
        return this;
    }

    /**
     * Sets the {@code AppointmentTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAppointmentTime(String start, String duration) {
        this.appointmentTime = new AppointmentTime(start, duration);
        return this;
    }

    /**
     * Sets the {@code treatment} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTreatment(String treatment) {
        this.treatment = treatment;
        return this;
    }

    /**
     * Sets the {@code cost} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withCost(String cost) {
        this.cost = cost;
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withId(String id) {
        this.id = Long.parseLong(id);
        return this;
    }

    /**
     * Sets the {@code start} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStart(String start) {
        this.start = start;
        return this;
    }

    /**
     * Sets the {@code duration} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDuration(String duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Builds and returns a new {@code Appointment} with the specified details.
     * The details must be set using the various "with" methods before calling this method.
     *
     * @return A new {@code Appointment} instance with the specified details.
     */
    public Appointment build() {
        Appointment newAppointment = new Appointment(dentistId, patientId, start, treatment);
        newAppointment.setDentistName(dentistName);
        newAppointment.setPatientName(patientName);
        newAppointment.setAppointmentTime(appointmentTime);
        newAppointment.setCost(cost);
        newAppointment.setId(id);

        return newAppointment;
    }

    /**
     * Builds and returns a new {@code Appointment} with the specified details for parsing test purposes.
     *
     * @return A new {@code Appointment} instance with the specified details.
     */
    public Appointment parserBuild() {
        Appointment appointment = new Appointment(dentistId, patientId, start, treatment);
        return appointment;
    }

}
