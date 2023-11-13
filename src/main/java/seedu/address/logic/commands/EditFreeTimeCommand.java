package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.person.Person;

/**
 * Edits the free time of an existing person in the address book.
 */
public class EditFreeTimeCommand extends Command {

    public static final String COMMAND_WORD = "editft";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the free time of the teaching assistant "
            + "identified "
            + "by the index number used in the displayed teaching assistant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "DAY (must be between 1-5)"
            + "[" + PREFIX_FROM + "FROM "
            + PREFIX_TO + "TO] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DAY + "3 "
            + PREFIX_FROM + "14:00 "
            + PREFIX_TO + "15:00";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Teaching Assistant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    private final Index index;
    private final Integer day;
    private final EditCommand.EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param day                  of a week representing Mon to Fri
     * @param editPersonDescriptor details to edit the person with
     */
    public EditFreeTimeCommand(Index index, Integer day, EditCommand.EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(day);
        requireNonNull(editPersonDescriptor);
        this.index = index;
        this.day = day;
        this.editPersonDescriptor = new EditCommand.EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedFreeTimePerson(Person personToEdit, Integer dayOfWeek,
                                                     EditCommand.EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;
        FreeTime freeTimeToEdit = personToEdit.getFreeTime();
        FreeTime updatedFreeTime = freeTimeToEdit.updateAvailabilityForDay(dayOfWeek,
                editPersonDescriptor.getTimeInterval().orElse(freeTimeToEdit.getDay(dayOfWeek)));

        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getTelegram(), personToEdit.getTags(), updatedFreeTime,
                personToEdit.getCourses(), personToEdit.getHour());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedFreeTimePerson(personToEdit, day, editPersonDescriptor);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditFreeTimeCommand)) {
            return false;
        }

        EditFreeTimeCommand otherCommand = (EditFreeTimeCommand) other;
        return index.equals(otherCommand.index)
                && day.equals(otherCommand.day)
                && editPersonDescriptor.equals(otherCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("day", day)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }
}
