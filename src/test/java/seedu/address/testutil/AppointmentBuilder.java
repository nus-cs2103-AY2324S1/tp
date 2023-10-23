package seedu.address.testutil;

import static seedu.address.logic.commands.appointmentcommands.AppointmentCommandTestUtil.VALID_DESCRIPTION;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Person;

/**
 * Apartment builder helps to build test appointment objects.
 */
public class AppointmentBuilder {


    private static final AppointmentTime DEFAULT_TIME = new AppointmentTimeBuilder().build();
    private static final Person DEFAULT_PATIENT = new PersonBuilder().build();
    private static final String DEFAULT_DESCRIPTION = VALID_DESCRIPTION;

    // Identity fields
    private AppointmentTime appointmentTime;
    private Person patient;
    //  private Set<Tag> tags = new HashSet<>();

    private AppointmentDescription appointmentDescription;
    /**
     * Creates a {@code AppointmentBuilder} with default settings.
     */
    public AppointmentBuilder() {
        appointmentTime = DEFAULT_TIME;
        patient = DEFAULT_PATIENT;
        appointmentDescription = new AppointmentDescription(DEFAULT_DESCRIPTION);
    }

    /**
     * Creates a {@code AppointmentBuilder} with data provided by {@code appointment}.
     */
    public AppointmentBuilder(Appointment appointment) {
        appointmentTime = appointment.getAppointmentTime();
        patient = appointment.getPerson();
        appointmentDescription = appointment.getAppointmentDescription();
    }

    public Appointment build() {
        return new Appointment(this.patient, this.appointmentTime, this.appointmentDescription);
    }

    /**
     * Sets the {@code AppointmentTime} for the Appointment being built.
     */
    public AppointmentBuilder withAppointmentTime(String start, String end) {
        this.appointmentTime = new AppointmentTimeBuilder().withStart(start).withEnd(end).build();
        return this;
    }

    /**
     * Sets the {@code Patient} for the Appointment being built.
     */
    public AppointmentBuilder withPatient(Person patient) {
        this.patient = patient;
        return this;
    }


    /**
     * Sets the {@code Description} for the Appointment being built.
     */
    public AppointmentBuilder withDescription(String description) {
        this.appointmentDescription = new AppointmentDescription(description);
        return this;
    }
}
