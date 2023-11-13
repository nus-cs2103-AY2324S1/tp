package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
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
 * Deletes a tutorial identified using its index from the address book.
 */
public class DeleteTutorialCommand extends Command {

    public static final String COMMAND_WORD = "deleteTutorial";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tutorial identified by the index number used in the displayed tutorial list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted tutorial: %s";

    public static final String MESSAGE_NO_TUTORIAL_FOUND = "Tutorial not found.";

    private Index targetIndex;

    /**
     * Creates an DeleteTutorialCommand to delete the specified {@code Index}
     */
    public DeleteTutorialCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(targetIndex);
        List<Tutorial> lastShownList = model.getTutorialList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_NO_TUTORIAL_FOUND);
        }

        Tutorial toDelete = lastShownList.get(targetIndex.getZeroBased());
        deleteTutorialFromAllPeople(model, toDelete);
        model.deleteTutorial(toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    private void deleteTutorialFromAllPeople(Model model, Tutorial tutorial) {
        ObservableList<Person> people = model.getFilteredPersonList();
        for (Person person : people) {
            if (person.getTutorials().contains(tutorial)) {
                Name updatedName = person.getName();
                Phone updatedPhone = person.getPhone();
                Email updatedEmail = person.getEmail();
                Set<Tag> updatedTags = person.getTags();
                Set<Module> updatedModules = person.getModules();
                Set<Tutorial> updatedTutorials = new HashSet<>(person.getTutorials());
                updatedTutorials.remove(tutorial);
                StudentNumber updatedStudentNumber = person.getStudentNumber();
                Telegram updatedTelegram = person.getTelegram();
                Person personWithoutTutorial = new Person(updatedName, updatedPhone, updatedEmail, updatedTags,
                        updatedModules, updatedTutorials, updatedStudentNumber, updatedTelegram);
                model.setPerson(person, personWithoutTutorial);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTutorialCommand)) {
            return false;
        }

        DeleteTutorialCommand otherDeleteTutorialCommand = (DeleteTutorialCommand) other;
        return targetIndex.equals(otherDeleteTutorialCommand.targetIndex);
    }
}
