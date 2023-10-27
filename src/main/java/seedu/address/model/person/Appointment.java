package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

/**
 * The {@code Appointment} class represents a scheduled appointment between a doctor and a patient.
 * It includes information about the doctor, patient, and the appointment time.
 */
public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime appointmentTime;
    private String status = "scheduled";

    /**
     * Constructs a new appointment with the specified doctor, patient, and appointment time.
     *
     * @param doctor         The doctor involved in the appointment.
     * @param patient        The patient involved in the appointment.
     * @param appointmentTime The date and time of the appointment.
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime) {
        requireNonNull(doctor);
        requireNonNull(patient);
        requireNonNull(appointmentTime);
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
    }

    /**
     * Constructs a new appointment with the specified doctor, patient, and appointment time.
     *
     * @param doctor         The doctor involved in the appointment.
     * @param patient        The patient involved in the appointment.
     * @param appointmentTime The date and time of the appointment.
     * @param status         The status of the appointment.
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime, String status) {
        requireNonNull(doctor);
        requireNonNull(patient);
        requireNonNull(appointmentTime);
        requireNonNull(status);
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getStatus() {
        return status;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    public void changeDoctor(Doctor newDoctor) {
        this.doctor = newDoctor;
    }

    public void changePatient(Patient newPatient) {
        this.patient = newPatient;
    }

    public void changeStatus(String newStatus) {
        this.status = newStatus;
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
        return this.doctor.equals(otherAppointment.doctor)
                && this.patient.equals(otherAppointment.patient)
                && this.appointmentTime.equals(otherAppointment.appointmentTime)
                && this.status.equals(otherAppointment.status);
    }
}
