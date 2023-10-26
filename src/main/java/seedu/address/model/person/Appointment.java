package seedu.address.model.person;

import java.time.LocalDateTime;

/**
 * The {@code Appointment} class represents a scheduled appointment between a doctor and a patient.
 * It includes information about the doctor, patient, and the appointment time.
 */
public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime appointmentTime;

    /**
     * Constructs a new appointment with the specified doctor, patient, and appointment time.
     *
     * @param doctor         The doctor involved in the appointment.
     * @param patient        The patient involved in the appointment.
     * @param appointmentTime The date and time of the appointment.
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
