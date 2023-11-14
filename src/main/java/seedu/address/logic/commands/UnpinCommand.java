package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Unpins the person identified using it's displayed index from the address
 * book.
 */
public class UnpinCommand extends Command {

    public static final String COMMAND_WORD = "unpin";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unpins the person identified by the index number used in the displayed pinned list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNPIN_PERSON_SUCCESS = "Unpinned Person: %1$s";

    private final Index targetIndex;

    public UnpinCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPinnedList = model.getPinnedPersonList();

        if (targetIndex.getZeroBased() >= lastShownPinnedList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnpin = lastShownPinnedList.get(targetIndex.getZeroBased());
        Person unpinnedPerson = new Person(personToUnpin.getName(), personToUnpin.getNric(), personToUnpin.getEmail(),
                personToUnpin.getPhone(), personToUnpin.getGender(), personToUnpin.getAge(),
                personToUnpin.getBloodType(), personToUnpin.getAllergies(), personToUnpin.getRecords(),
                personToUnpin.getAppointments(), false);

        model.setPerson(personToUnpin, unpinnedPerson);
        return new CommandResult(String.format(MESSAGE_UNPIN_PERSON_SUCCESS, Messages.format(personToUnpin)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnpinCommand)) {
            return false;
        }

        UnpinCommand otherUnpinCommand = (UnpinCommand) other;
        return targetIndex.equals(otherUnpinCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
