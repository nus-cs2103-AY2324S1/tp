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

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
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
                && this.appointmentTime.equals(otherAppointment.appointmentTime);
    }
}
