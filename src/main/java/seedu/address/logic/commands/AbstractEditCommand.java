package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
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
    protected Function<T, T> getClashingEntry;
    protected Consumer<T> showMethod;

    /**
     * Pass in index to indicate which entry to edit
     */
    public AbstractEditCommand(Integer index, T editDescriptor) {
        requireNonNull(editDescriptor);
        this.index = index;
        this.editDescriptor = editDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        this.model = model;
        initModelMethods();
        init();
        editFields();
        validateEditedAndWriteBack();
        model.resetAllShowFields();
        showMethod.accept(edited);
        return new CommandResult("Edit success.\n from: " + original.toString() + "\n to: " + edited.toString());
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
                    throw new CommandException("Using edit command without specifying index when no entry is shown. "
                            + getUsageInfo() + ".");
                }
            } else {
                try {
                    original = list.get(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    String errMessage = list.isEmpty()
                                        ? "The index provided is invalid as the " + listName() + " list is empty."
                                        : "Index out of bounds, expected 1 to " + list.size()
                            + " but got " + index + ".";
                    throw new CommandException(errMessage);
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
        setNonDefaultFields();
    }
    protected void validateEditedAndWriteBack() throws CommandException {
        if (edited.equals(original)) {
            throw new CommandException("No edit detected. Please edit at least one field: "
                    + editableFieldsInfo() + " to different value.");
        }
        try {
            deleteMethod.accept(original);
        } catch (Exception e) {
            throw new CommandException("Internal Error in deleting original entry: " + original.toString());
        }
        if (hasClashWith.test(edited)) {
            try {
                addMethod.accept(original);
            } catch (Exception e) {
                throw new CommandException("Internal Error, clash detected for edited entry and"
                        + " error adding deleted original entry back. original: "
                        + original.toString() + " edited: " + edited.toString() + ".");
            }
            throw new CommandException("Clash detected.\nEdited: " + edited.toString() + "\nClashes with: "
                    + getClashingEntry.apply(edited).toString() + ".");
        }
        addMethod.accept(edited);
    }
    abstract String editableFieldsInfo();
    abstract String listName();
    public abstract String getUsageInfo();
}
