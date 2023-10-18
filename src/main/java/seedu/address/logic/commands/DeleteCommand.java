package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_DELETE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the employee identified by the index number used in the displayed employee list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE_FOR_NAME = COMMAND_WORD
            + ": Deletes the employee identified by the name used in the displayed employee list.\n"
            + "Parameters: n/NAME (must be present)\n"
            + "Example: " + COMMAND_WORD + " n/John";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Employee: %1$s";

    private final Index targetIndex;

    /* The name of the employee to be deleted*/
    private final NameContainsKeywordsPredicate name;

    /**
     * The constructor for DeleteCommand to take in index
     * @param targetIndex The index of the employee to be deleted
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.name = null;
    }

    /**
     * The constructor for DeleteCommand to take in name instead of index
     * @param name The name of the employee to be deleted
     */
    public DeleteCommand(NameContainsKeywordsPredicate name) {
        this.targetIndex = null;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (targetIndex == null) {
            return this.deleteByName(model);
        }
        return this.deleteByIndex(model);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        if ((this.targetIndex == null && otherDeleteCommand.targetIndex != null)
                || (this.targetIndex != null && otherDeleteCommand.targetIndex == null)) {
            return false;
        }

        if ((this.name == null && otherDeleteCommand.name != null)
                || (this.name != null && otherDeleteCommand.name == null)) {
            return false;
        }

        if (this.targetIndex != null) {
            return targetIndex.equals(otherDeleteCommand.targetIndex);
        }
        return this.name.equals(otherDeleteCommand.name);
    }

    @Override
    public String toString() {
        if (name != null) {
            return new ToStringBuilder(this)
                    .add("targetName", name)
                    .toString();
        }
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

    /**
     * Deletes the employee identified by the index number used in the displayed employee list.
     * @param model {@code Model} which the command should operate on.
     * @return A command result that contains the message to be displayed to the user.
     * @throws CommandException If the index is invalid.
     */
    public CommandResult deleteByIndex(Model model) throws CommandException {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person employeeToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(employeeToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(employeeToDelete)));
    }

    /**
     * Deletes the employee identified by the name used in the displayed employee list.
     * @param model {@code Model} which the command should operate on.
     * @return A command result that contains the message to be displayed to the user.
     * @throws CommandException If the name is invalid.
     */
    public CommandResult deleteByName(Model model) throws CommandException {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> fullList = model.getFilteredPersonList();
        List<Integer> indexes = model.getIndexOfFilteredPersonList(this.name);
        if (indexes.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }
        if (indexes.size() == 1) {
            Person employeeToDelete = fullList.get(indexes.get(0) - 1);
            model.deletePerson(employeeToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(employeeToDelete)));
        }
        model.updateFilteredPersonList(this.name);
        return new CommandResult(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_DELETE,
                model.getFilteredPersonList().size()), indexes);
    }
}
