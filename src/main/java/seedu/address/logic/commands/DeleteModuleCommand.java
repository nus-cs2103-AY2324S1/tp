package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;

/**
 * Deletes a module identified using its index from the address book.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "deleteModule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a module from the list\n"
            + "Parameters: INDEX\n"
            + "INDEX should not be negative!";

    public static final String MESSAGE_SUCCESS = "Deleted module: %s";

    public static final String MESSAGE_EXCEED_LIST = "The INDEX is out of bounds!";

    private Index targetIndex;

    /**
     * Creates an DeleteModuleCommand to add the specified {@code Person}
     */
    public DeleteModuleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_EXCEED_LIST);
        }

        Module toDelete = lastShownList.get(targetIndex.getZeroBased());

        for (Person personToEdit : model.getFilteredPersonList()) {
            // checks for matching modules in the person
            if (personToEdit.getModules().contains(toDelete)) {
                Person editedPerson = createEditedPersonDeletingModule(personToEdit, toDelete);
                model.setPerson(personToEdit, editedPerson);
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
                personToEdit = editedPerson;
            }
            Set<Tutorial> tutorials = personToEdit.getTutorials();
            // checks for tutorials with matching modules in the person
            for (Tutorial tutorial : tutorials) {
                if (tutorial.getModuleCode().equals(toDelete.getModuleCode())) {
                    Person editedPerson = createEditedPersonDeletingTutorial(personToEdit, tutorial);
                    model.setPerson(personToEdit, editedPerson);
                    model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
                }
            }
        }
        model.deleteModule(toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private Person createEditedPersonDeletingModule(Person personToEdit, Module toDelete) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Set<Tag> updatedTags = personToEdit.getTags();

        Set<Module> updatedModules = new HashSet<>(personToEdit.getModules());
        updatedModules.remove(toDelete);

        Set<Tutorial> updatedTutorials = personToEdit.getTutorials();
        StudentNumber updatedStudentNumber = personToEdit.getStudentNumber();
        Telegram updatedTelegram = personToEdit.getTelegram();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedTags, updatedModules,
                updatedTutorials, updatedStudentNumber, updatedTelegram);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private Person createEditedPersonDeletingTutorial(Person personToEdit, Tutorial toDelete) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Set<Tag> updatedTags = personToEdit.getTags();
        Set<Module> updatedModules = new HashSet<>(personToEdit.getModules());

        Set<Tutorial> updatedTutorials = new HashSet<>();
        for (Tutorial tutorial : personToEdit.getTutorials()) {
            updatedTutorials.add(tutorial);
        }
        updatedTutorials.remove(toDelete);

        StudentNumber updatedStudentNumber = personToEdit.getStudentNumber();
        Telegram updatedTelegram = personToEdit.getTelegram();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedTags, updatedModules,
                updatedTutorials, updatedStudentNumber, updatedTelegram);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteModuleCommand)) {
            return false;
        }

        DeleteModuleCommand otherDeleteModuleCommand = (DeleteModuleCommand) other;
        return targetIndex.equals(otherDeleteModuleCommand.targetIndex);
    }
}
