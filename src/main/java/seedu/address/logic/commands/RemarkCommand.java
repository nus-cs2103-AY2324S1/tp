package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

/**
 * The RemarkCommand class represents a command in a software application for editing remarks associated with persons.
 * This command allows the user to add or update a remark for a person identified by their index number in the last
 * person listing. If a remark already exists for the person, it will be overwritten by the new input remark.
 * If the remark input is empty, the existing remark for the person can be removed.
 */
public class RemarkCommand extends Command {
    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Likes to swim.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Person: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Person: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index  of the person in the filtered person list to edit the remark
     * @param remark of the person to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // combine doctor list and patient list
        List<Person> lastShownList = new ArrayList<>();
        lastShownList.addAll(model.getFilteredDoctorList());
        lastShownList.addAll(model.getFilteredPatientList());

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (personToEdit.isDoctor()) {
            Doctor editedDoctor = new Doctor(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                    personToEdit.getAddress(), remark, personToEdit.getGender(),
                    personToEdit.getIc(), personToEdit.getTags());
            model.setPerson(personToEdit, editedDoctor);
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

            return new CommandResult(generateSuccessMessage(editedDoctor));
        }
        @SuppressWarnings("unchecked") //Since Person is abstract, every Person is either a Patient or a Doctor
        Patient editedPerson = (Patient) personToEdit;
        Patient editedPatient = new Patient(editedPerson.getName(), editedPerson.getPhone(),
                editedPerson.getEmergencyContact(), editedPerson.getEmail(), editedPerson.getAddress(),
                editedPerson.getRemark(), editedPerson.getGender(), editedPerson.getIc(),
                editedPerson.getCondition(), editedPerson.getBloodType(), editedPerson.getTags());

        model.setPerson(personToEdit, editedPatient);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPatient));



    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
