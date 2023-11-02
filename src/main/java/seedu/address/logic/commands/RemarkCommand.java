package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Ic;
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
    public static final String MESSAGE_DOESNT_EXIST = "This person hasn't been saved";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Person: %1$s";
    private static final Logger logger = LogsCenter.getLogger(RemarkCommand.class);
    private final Ic nric;
    private final Remark remark;

    /**
     * @param nric  of the person in the filtered person list to edit the remark
     * @param remark of the person to be updated to
     */
    public RemarkCommand(Ic nric, Remark remark) {
        requireAllNonNull(nric, remark);

        this.nric = nric;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Person personToEdit = getPersonToEdit(model);
        Person editedPerson = getEditedPerson(model, personToEdit);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        logger.info("Successfully added remark to person");
        return new CommandResult(generateSuccessMessage(editedPerson));
    }
    private Person getPersonToEdit(Model model) throws CommandException {
        List<Person> lastShownList = new ArrayList<>();
        lastShownList.addAll(model.getFilteredDoctorList());
        lastShownList.addAll(model.getFilteredPatientList());
        List<Person> personToEditList = lastShownList.stream()
                .filter(x -> x.getIc().equals(nric))
                .collect(Collectors.toList());
        if (personToEditList.size() == 0) {
            logger.warning("Could not edit - person isn't in adressbook");
            throw new CommandException(MESSAGE_DOESNT_EXIST);
        }
        //developer assumption - can't have 2 people with same IC
        assert personToEditList.size() < 2;
        Person personToEdit = personToEditList.get(0);
        return personToEdit;
    }

    private Person getEditedPerson(Model model, Person personToEdit) {
        requireAllNonNull(model, personToEdit);
        Person editedPerson;
        if (personToEdit.isDoctor()) {
            Doctor editedDoctor = new Doctor(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                    personToEdit.getAddress(), remark, personToEdit.getGender(),
                    personToEdit.getIc(), personToEdit.getAppointments(), personToEdit.getTags());
            editedPerson = editedDoctor;
        } else {
            assert personToEdit.isPatient();
            Patient patientToEdit = (Patient) personToEdit;
            Patient editedPatient = new Patient(patientToEdit.getName(), patientToEdit.getPhone(),
                    patientToEdit.getEmergencyContact(), patientToEdit.getEmail(), patientToEdit.getAddress(),
                    patientToEdit.getRemark(), patientToEdit.getGender(), patientToEdit.getIc(),
                    patientToEdit.getCondition(), patientToEdit.getBloodType(), patientToEdit.getAppointments(),
                    patientToEdit.getTags());
            editedPerson = editedPatient;
        }
        return editedPerson;
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
        return nric.equals(e.nric)
                && remark.equals(e.remark);
    }
}
