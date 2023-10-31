package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DOCTORS;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.tag.Tag;

/**
 * Adds a doctor to the database.
 */
public class AddDoctorSpecialisationCommand extends Command {

    public static final String COMMAND_WORD = "add_spec";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a specialisation to doctor. "
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_TAG + "specialisation";

    public static final String MESSAGE_SUCCESS = "New specialisation for doctor added: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This specialisation already exists on the doctor";

    private final Index index;
    private final Tag specialisation;

    /**
     * Creates an AddDoctorTagCommand to add the specified {@code Doctor}
     */
    public AddDoctorSpecialisationCommand(Index index, Tag specialisation) {
        requireAllNonNull(index, specialisation);

        this.index = index;
        this.specialisation = specialisation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToEdit = lastShownList.get(index.getZeroBased());

        HashSet<Tag> doctorTags = doctorToEdit.getTags();

        if (doctorTags.contains(specialisation)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        doctorTags.add(specialisation);

        Doctor editedDoctor = new Doctor(
                doctorToEdit.getName(), doctorToEdit.getNric(),
                doctorToEdit.getRemark(), doctorTags);

        model.setDoctor(doctorToEdit, editedDoctor);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);


        return new CommandResult(String.format(MESSAGE_SUCCESS, doctorToEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddDoctorSpecialisationCommand)) {
            return false;
        }

        AddDoctorSpecialisationCommand otherAddCommand = (AddDoctorSpecialisationCommand) other;
        return index.equals(otherAddCommand.index)
                && specialisation.equals(otherAddCommand.specialisation);
    }
    @Override
    public int hashCode() {
        return Objects.hash(index, specialisation);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("specialisation", specialisation)
                .toString();
    }
}
