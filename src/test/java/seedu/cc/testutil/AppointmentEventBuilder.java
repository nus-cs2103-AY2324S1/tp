package seedu.cc.testutil;

import java.util.Set;

import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.PatientAppointmentList;
import seedu.cc.model.appointment.Prescription;
import seedu.cc.model.util.Date;
import seedu.cc.model.util.Time;

/**
 * A utility class to help with building AppointmentEvent objects.
 */
public class AppointmentEventBuilder {

    private static final String DEFAULT_DATE = "2024-01-01";
    private static final String DEFAULT_TIME = "15:00";
    private static final String DEFAULT_PRESCRIPTION = "Paracetamol";

    private Date date;
    private Time time;
    private Set<Prescription> prescriptions;
    private boolean isDone;

    /**
     * Creates a {@code AppointmentEventBuilder} with the default details.
     */
    public AppointmentEventBuilder() {
        date = new Date(DEFAULT_DATE);
        time = new Time(DEFAULT_TIME);
        prescriptions = Set.of(new Prescription(DEFAULT_PRESCRIPTION));
        isDone = false;
    }

    /**
     * Initializes the AppointmentEventBuilder with the data of {@code appointmentEventToCopy}.
     */
    public AppointmentEventBuilder(AppointmentEvent appointmentEventToCopy) {
        date = appointmentEventToCopy.getDate();
        time = appointmentEventToCopy.getTime();
        prescriptions = appointmentEventToCopy.getPrescriptions();
        isDone = appointmentEventToCopy.isDone();
    }

    /**
     * Sets the {@code Date} of the {@code AppointmentEvent} that we are building.
     */
    public AppointmentEventBuilder withDateTime(String date, String time) {
        this.date = new Date(date);
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code Prescription} of the {@code AppointmentEvent} that we are building.
     */
    public AppointmentEventBuilder withPrescriptions(String prescription) {
        this.prescriptions = Set.of(new Prescription(prescription));
        return this;
    }

    public AppointmentEvent build() {
        return new AppointmentEvent(date, time, prescriptions);
    }

    /**
     * Builds a {@code PatientAppointment} with the {@code AppointmentEvent} that we are building.
     */
    public PatientAppointmentList buildAppointmentList() {
        PatientAppointmentList patientAppointmentList = new PatientAppointmentList();
        patientAppointmentList.addAppointmentList(new AppointmentEvent(date, time, prescriptions));
        return patientAppointmentList;
    }
}
