package seedu.cc.testutil;

import java.util.Set;

import seedu.cc.logic.commands.appointmentcommands.EditAppointmentEventCommand.EditAppointmentEventDescriptor;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.Prescription;
import seedu.cc.model.util.Date;
import seedu.cc.model.util.Time;

/**
 * A utility class to help with building EditAppointmentEventDescriptor objects.
 */
public class EditAppointmentEventDescriptorBuilder {

    private final EditAppointmentEventDescriptor descriptor;

    public EditAppointmentEventDescriptorBuilder() {
        descriptor = new EditAppointmentEventDescriptor();
    }

    public EditAppointmentEventDescriptorBuilder(EditAppointmentEventDescriptor descriptor) {
        this.descriptor = new EditAppointmentEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentEventDescriptor} with fields containing {@code event}'s details
     */
    public EditAppointmentEventDescriptorBuilder(AppointmentEvent event) {
        descriptor = new EditAppointmentEventDescriptor();
        descriptor.setDate(event.getDate());
        descriptor.setTime(event.getTime());
        descriptor.setPrescriptions(event.getPrescriptions());
    }

    /**
     * Sets the {@code Prescription} of the {@code EditAppointmentEventDescriptor} that we are building.
     */
    public EditAppointmentEventDescriptorBuilder withPrescription(String prescription) {
        descriptor.setPrescriptions(Set.of(new Prescription(prescription)));
        return this;
    }

    /**
     * Sets the {@code Date} and {@code Time} of the {@code EditAppointmentEventDescriptor} that we are building.
     */
    public EditAppointmentEventDescriptorBuilder withDateTime(String date, String time) {
        descriptor.setDate(new Date(date));
        descriptor.setTime(new Time(time));
        return this;
    }

    /**
     * Sets the {@code Prescription} of the {@code EditAppointmentEventDescriptor} that we are building.
     */
    public EditAppointmentEventDescriptorBuilder withPrescriptions(Set<Prescription> prescriptions) {
        descriptor.setPrescriptions(prescriptions);
        return this;
    }

    public EditAppointmentEventDescriptor build() {
        return descriptor;
    }
}
