package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Exits the view mode and prompts to the original fosterer list view.
 */
public class ViewExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting view mode as requested ...";
    public static final String MESSAGE_CONFIRM_EXIT = "You did not save your changes. Are you sure you want to exit?\n"
            + "Yes: [Enter] No: [Esc]";
    public static final String MESSAGE_CONFIRM_EXIT_WITHOUT_DETAILS = "You did not fill in every detail."
            + " Are you sure you want to exit?\n"
            + "Yes: [Enter] No: [Esc]";

    private Index index;
    private Person newFosterer;

    /**
     * Represents a ViewExitCommand constructor that takes in only a Person object that is used to exit
     * a profile view page when adding a new fosterer.
     *
     * @param newFosterer to be added to the storage.
     */
    public ViewExitCommand(Person newFosterer) {
        super();
        this.index = null;
        this.newFosterer = newFosterer;
    }

    /**
     * Represents a ViewExitCommand constructor that takes in both the index of the fosterer to be edited and
     * the fosterer object that includes the edited details on the currently opened PersonProfile.
     *
     * @param index       of the fosterer to be edited.
     * @param newFosterer to be compared with the original fosterer to see if there is any change in the details.
     */
    public ViewExitCommand(Index index, Person newFosterer) {
        super();
        this.index = index;
        this.newFosterer = newFosterer;
    }

    /**
     * Returns different CommandResult objects for different cases.
     * If a new fosterer is being created from PersonProfile, the command checks if the person can be created with
     * the details currently on the page. If can, the CommandResult asks the user if they want to save before exit.
     * Otherwise, the user will be asked if they want to continue adding details before exit.
     * If a fosterer's details were edited but not saved, the CommandResult asks the user
     * if they want to save before exit.
     * If a fosterer's details were edited and saved, the CommandResult would not ask the user to save.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult that carries the necessary details used to check
     *     if the person is saved or needs to be saved.
     * @throws CommandException when the index of the fosterer is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        // for exiting from empty profile page with not every field filled out
        if (index == null && newFosterer == null) {
            return new CommandResult(
                    MESSAGE_CONFIRM_EXIT_WITHOUT_DETAILS,
                    null,
                    null,
                    CommandType.VIEW_EXIT,
                    false);
        }

        // for exiting from profile page with every field filled out
        if (index == null && newFosterer != null) {
            return new CommandResult(
                    MESSAGE_CONFIRM_EXIT,
                    newFosterer,
                    null,
                    CommandType.VIEW_EXIT,
                    false);
        }

        Person personToCompare = lastShownList.get(index.getZeroBased());
        Person editedPerson = newFosterer;

        // fosterer is edited and saved, exit without any message.
        if (editedPerson.equals(personToCompare)) {
            return new CommandResult(
                    MESSAGE_EXIT_ACKNOWLEDGEMENT,
                    newFosterer,
                    null,
                    CommandType.VIEW_EXIT,
                    true);
        } else {
            // fosterer is edited but not saved, exit with message.
            return new CommandResult(
                    MESSAGE_CONFIRM_EXIT,
                    newFosterer,
                    null,
                    CommandType.VIEW_EXIT,
                    false);
        }
    }
}
