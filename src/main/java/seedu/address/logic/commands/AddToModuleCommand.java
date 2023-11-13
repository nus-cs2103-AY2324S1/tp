package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
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
 * Adds a person to a module.
 */
public class AddToModuleCommand extends Command {

    public static final String COMMAND_WORD = "addToModule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a user to a given module "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULE + "MODULE]\n "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE + "CS1000 ";

    public static final String MESSAGE_SUCCESS = "Added person to module: %1$s";
    private final Index index;
    private final Module moduleToAddTo;
    private final Logger logger = LogsCenter.getLogger(AddToModuleCommand.class);

    /**
     * @param index of the person in the filtered person list to add tag to
     */
    public AddToModuleCommand(Index index, Module moduleToAddTo) {
        requireNonNull(index);
        requireNonNull(moduleToAddTo);
        this.index = index;
        this.moduleToAddTo = moduleToAddTo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList();

        if (index.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!model.hasModule(moduleToAddTo)) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE);
        }

        Person personToEdit = personList.get(index.getZeroBased());
        logger.info("Person to be added to module " + personList);
        Person editedPerson = createEditedPerson(personToEdit);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private Person createEditedPerson(Person personToEdit) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Set<Tag> updatedTags = personToEdit.getTags();
        Set<Module> updatedModules = new HashSet<>(personToEdit.getModules());
        updatedModules.add(moduleToAddTo);
        Set<Tutorial> updatedTutorials = personToEdit.getTutorials();
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
        if (!(other instanceof AddToModuleCommand)) {
            return false;
        }

        return this.index.equals(((AddToModuleCommand) other).index)
                && this.moduleToAddTo.equals(((AddToModuleCommand) other).moduleToAddTo);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }
}
