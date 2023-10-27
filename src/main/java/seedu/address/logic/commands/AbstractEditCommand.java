package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ListEntry;
import seedu.address.model.Model;

/**
 * Abstract class for edit commands
 */
public abstract class AbstractEditCommand<T extends ListEntry<? extends T>> extends Command {
    protected Integer index = null;
    protected final T editDescriptor;
    //private final T incrementDescriptor = null;
    protected T original = null;
    protected T edited;
    protected Model model;

    /**
     * Pass in index to indicate which entry to edit
     */
    public AbstractEditCommand(int index, T editDescriptor) {
        requireNonNull(editDescriptor);
        this.index = index;
        this.editDescriptor = editDescriptor;
    }

    /**
     * Pass in a temp object holding the value to be edited, will edit the currently displayed entry
     */
    public AbstractEditCommand(T editDescriptor) {
        requireNonNull(editDescriptor);
        this.editDescriptor = editDescriptor;
    }

    /**
     * Pass in original to avoid having to search for it again
     */
    public AbstractEditCommand(T editDescriptor, T original) {
        requireNonNull(editDescriptor);
        this.original = original;
        this.editDescriptor = editDescriptor;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        this.model = model;
        init();
        editFields();
        validateEdited();
        writeBack();
        return new CommandResult("Edited Person: " + edited.toString());
    }

    protected void init(T currentShownEntry, List<T> list) throws CommandException {
        if (original == null) {
            if (index == null) {
                original = currentShownEntry;
                if (original == null) {
                    throw new CommandException("No entry selected or shown.");
                }
            } else {
                try {
                    original = list.get(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    throw new CommandException("Index out of bounds, expected 1 to "
                            + model.getFilteredPersonList().size() + " but got " + index + ".");
                }
            }
            edited = original.clone();
        }
    }
    abstract void init() throws CommandException;
    private void editFields() throws CommandException {
        setNonDefaultFields();
        //setNonDefaultFields(edited, incrementDescriptor);
    }
    abstract void setNonDefaultFields() throws CommandException;
    protected void setNameRemarkTags() throws CommandException {
        edited.setNameIfNotDefault(editDescriptor.getName());
        edited.setRemarkIfNotDefault(editDescriptor.getRemark());
        edited.setTagsIfNotDefault(editDescriptor.getTags());
    }
    //abstract void incrementNonDefaultFields(T editedPerson, T editDescriptor);
    abstract void validateEdited() throws CommandException;
    protected void validateEdited(Predicate<T> predicate) throws CommandException {
        if (edited.equals(original)) {
            throw new CommandException("No change detected.");
        }
        if (!edited.getName().equals(original.getName()) && predicate.test(edited)) {
            throw new CommandException("Person " + edited.getName() + " already exists in the address book.");
        }
    }
    abstract void writeBack() throws CommandException;
}

