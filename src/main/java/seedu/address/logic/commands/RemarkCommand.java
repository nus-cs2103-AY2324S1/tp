package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;


/**
 * Remark a person.
 */
public class RemarkCommand extends Command {


    public static final String COMMAND_WORD = "remark";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to customer: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from customer: %1$s";
    public static final String MESSAGE_USAGE = "Usage: \n" + COMMAND_WORD
            + " <index> "
            + "[remarks]\n";

    private static final Logger logger = LogsCenter.getLogger(RemarkCommand.class);
    private final Index index;
    private final Remark remark;



    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param remark of the person to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {

        logger.fine("RemarkCommand executing...");

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), remark, personToEdit.getTags(), personToEdit.getInsurances(),
                personToEdit.getAppointment(), personToEdit.getAppointmentCount(), personToEdit.getPriority());

        CommandUtil.verifyPersonChanged(personToEdit, editedPerson);

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !remark.toString().isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, Messages.format(personToEdit));
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
