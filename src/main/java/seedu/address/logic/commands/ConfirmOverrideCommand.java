package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/** Overrides the current appointment and replaces it with the new appointment after confirmation **/
public class ConfirmOverrideCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Appointment updated!";
    private Appointment appointment;
    private Person personToEdit;

    /**
     * Default constructor for a confirm override command
     * @param appointment new appointment
     * @param personToEdit person whose old appointment will be replaced by the new appointment
     */
    public ConfirmOverrideCommand(Appointment appointment, Person personToEdit) {
        requireNonNull(appointment);
        requireNonNull(personToEdit);
        this.appointment = appointment;
        this.personToEdit = personToEdit;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public Person getPersonToEdit() {
        return this.personToEdit;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        requireNonNull(this.appointment);
        requireNonNull(this.personToEdit);

        Person personWithApt = createPersonWithAppointment(this.personToEdit);

        assert personWithApt.getAppointment() instanceof Appointment
                    : "Schedule Command: person should have appointment";

        this.appointment.setPerson(personWithApt); //sets person to appointment

        model.setPerson(this.personToEdit, personWithApt);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(personWithApt)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConfirmOverrideCommand)) {
            return false;
        }

        ConfirmOverrideCommand otherOverrideCommand = (ConfirmOverrideCommand) other;
        return this.appointment.equals(otherOverrideCommand.getAppointment())
                && this.personToEdit.equals(otherOverrideCommand.getPersonToEdit());
    }

    /**
     * Returns the {@code Person} with the scheduled Appointment.
     *
     * @param personToEdit The Person the appointment is scheduled to.
     * @return The Person with scheduled appointment.
     */
    private Person createPersonWithAppointment(Person personToEdit) {
        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getNextOfKinName(), personToEdit.getNextOfKinPhone(),
                personToEdit.getFinancialPlans(), personToEdit.getTags(), this.appointment);
    }
}
