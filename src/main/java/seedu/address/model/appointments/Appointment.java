package seedu.address.model.appointments;


/**
 * Represents an Appointment in ToothTracker.
 */
public class Appointment {
    private final long dentistId;
    private final long patientId;
    private final String treatment;
    private final String start;
    private String dentistName;
    private String patientName;
    private AppointmentTime appointmentTime;
    private String duration;
    private String cost;
    private long id;

    /**
     * Constructs an Appointment with the specified details.
     *
     * @param dentistId The ID of the dentist for the appointment.
     * @param patientId The ID of the patient for the appointment.
     * @param start The start time of the appointment.
     * @param treatment The treatment provided.
     */
    public Appointment(long dentistId, long patientId, String start, String treatment) {
        this.dentistId = dentistId;
        this.patientId = patientId;
        this.start = start;
        this.treatment = treatment;
        this.id = -1;
    }

    /**
     * Constructs an Appointment with the specified details.
     *
     * @param dentistId The ID of the dentist for the appointment.
     * @param patientId The ID of the patient for the appointment.
     * @param start The start time of the appointment.
     * @param treatment The treatment provided.
     * @param id The appointment id.
     */
    public Appointment(long dentistId, long patientId, String start, String treatment, long id) {
        this.dentistId = dentistId;
        this.patientId = patientId;
        this.start = start;
        this.treatment = treatment;
        this.id = id;
    }

    public long getDentistId() {
        return this.dentistId;
    }

    public long getPatientId() {
        return this.patientId;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDentistName() {
        return this.dentistName;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public String getStart() {
        return this.start;
    }

    public String getDuration() {
        return this.duration;
    }
    public void setDuration(String other) {
        this.duration = other;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public AppointmentTime getAppointmentTime() {
        return this.appointmentTime;
    }
    public void setAppointmentTime(AppointmentTime other) {
        this.appointmentTime = other;
    }

    public String getTreatment() {
        return this.treatment;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    /**
     * Checks whether this appointment timing clashes with another.
     *
     * @param appointment The other appointment.
     * @return true if this appointment timing clashes with another
     *         false if it does not clash with another appointment.
     */
    public boolean isSameAppointment(Appointment appointment) {
        return this.appointmentTime.isClashingAppointment(appointment.getAppointmentTime());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return getDentistId() == (otherAppointment.getDentistId())
                && getPatientId() == (otherAppointment.getPatientId())
                && getDentistName() == (otherAppointment.getDentistName())
                && getPatientName() == (otherAppointment.getPatientName())
                && getStart().equals(otherAppointment.getStart())
                && getDuration() == (otherAppointment.getDuration())
                && getAppointmentTime().equals(otherAppointment.getAppointmentTime())
                && getTreatment() == (otherAppointment.getTreatment())
                && getCost() == (otherAppointment.getCost())
                && id == otherAppointment.getId();

    }
}
