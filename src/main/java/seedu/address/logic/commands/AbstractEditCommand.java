package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ListEntry;
import seedu.address.model.Model;

/**
 * Abstract class for edit commands
 */
public abstract class AbstractEditCommand<T extends ListEntry<? extends T>> extends Command {
    /**
     * index is the index of the entry to edit, if it is present.
     */
    protected Integer index = null;
    /**
     * editDescriptor is a temp object holding the value to be edited.
     */
    protected final T editDescriptor;
    protected T original = null;
    protected T edited;
    protected Model model;

    protected T currentShownEntry;
    protected List<T> list;
    protected Predicate<T> hasClashWith;
    protected Consumer<T> deleteMethod;
    protected Consumer<T> addMethod;

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
        initModelMethods();
        init();
        editFields();
        validateEditedAndWriteBack();
        return new CommandResult("Edited : " + edited.toString());
    }
    /**
     * You need to override this method and set the following fields:
     * currentShownEntry, list, hasClashWith, delete, add
     * For example, if you are editing a person, you need to set as follows:
     * currentShownEntry = model.getCurrentlyDisplayedPerson();
     * list = model.getFilteredPersonList();
     * hasClashWith = model::hasPersonClashWith;
     * deleteMethod = model::deletePerson;
     * addMethod = model::addPerson;
     */
    protected abstract void initModelMethods();
    /**
     * You need to override this method and set the non default fields of the edited object here.
     * For example, if you are editing a person, you need to set the phone, email, address, and subjects.
     * If you are editing a lesson, you need to set the day, start, end, and subject.
     * Note that common fields like name, remark, and tags are already set in the editFields method.
     */
    protected abstract void setNonDefaultFields() throws CommandException;

    /**
     * Build the original object if it is not present.
     * If index is present, use it to get the original object from the list.
     * If index is not present, use the currently displayed entry.
     * This is a helper template method for the init method to be overriden later
     */
    protected void init() throws CommandException {
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
                    // NOTE: should it be list.size() instead?
                    throw new CommandException("Index out of bounds, expected 1 to "
                            + model.getFilteredPersonList().size() + " but got " + index + ".");
                }
            }
            edited = original.clone();
        }
    }

    /**
     * Edit the fields of the original object, then increment the fields if needed.
     */
    private void editFields() throws CommandException {
        edited.setNameIfNotDefault(editDescriptor.getName());
        edited.setRemarkIfNotDefault(editDescriptor.getRemark());
        edited.setTagsIfNotDefault(editDescriptor.getTags());
        setNonDefaultFields();
        //incrementNonDefaultFields(edited, incrementDescriptor);
    }
    protected void validateEditedAndWriteBack() throws CommandException {
        if (edited.equals(original)) {
            throw new CommandException("No change detected.");
        }
        try {
            deleteMethod.accept(original);
        } catch (Exception e) {
            throw new CommandException("Error deleting original entry.");
        }
        if (hasClashWith.test(edited)) {
            try {
                addMethod.accept(original);
            } catch (Exception e) {
                throw new CommandException("Clash detected and Error adding original entry back.");
            }
            throw new CommandException("Clash detected.");
        }
        addMethod.accept(edited);
    }
}
