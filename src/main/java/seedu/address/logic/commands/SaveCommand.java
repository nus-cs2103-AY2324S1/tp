package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Represents a SaveCommand that saves the edited fosterer data and update the fosterer information in the application.
 */
public class SaveCommand extends Command {
    public static final String SAVE_COMMAND_WORD = "save";
    public static final String MESSAGE_ADD_SUCCESS = "New fosterer added: %1$s";
    public static final String MESSAGE_DETAILS_NOT_FILLED = "Fosterer details must be filled out.";
    public static final String MESSAGE_FOSTERER_NOT_EDITED = "No details are edited.";

    private Person newFosterer;
    private Index index;

    /**
     * Represents a SaveCommand constructor that takes in the index of the fosterer to edit
     * and the new fosterer that replaces the already existing fosterer in the index.
     * @param index is the index of the fosterer stored in the list of fosterers.
     * @param newFosterer is the fosterer details to be updated to the already existing fosterer.
     */
    public SaveCommand(Index index, Person newFosterer) {
        super();
        this.newFosterer = newFosterer;
        this.index = index;
    }

    /**
     * Represents a SaveCommand constructor used when adding a fosterer from PersonProfile.
     * @param newFosterer is the new fosterer to be added in the program.
     */
    public SaveCommand(Person newFosterer) {
        super();
        this.newFosterer = newFosterer;
        this.index = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();


        // While on add ProfilePage.
        if (index == null) {
            // If the details are not all filled, throw an exception
            if (newFosterer == null) {
                throw new CommandException(MESSAGE_DETAILS_NOT_FILLED);
            }

            // If the model already has the newly adding fosterer, throw an exception
            if (model.hasPerson(newFosterer)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.addPerson(newFosterer);
            return new CommandResult(
                    String.format(MESSAGE_ADD_SUCCESS, Messages.format(newFosterer)),
                    null,
                    null,
                    CommandType.VIEW_EXIT,
                    true
                    );
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = newFosterer;

        // Edits a name but the person with the name already exists, then throw an exception
        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        // None of the details have changed, then throw an exception
        if (personToEdit.equals(editedPerson)) {
            throw new CommandException(MESSAGE_FOSTERER_NOT_EDITED);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)),
                CommandType.SAVE
        );
    }
}
