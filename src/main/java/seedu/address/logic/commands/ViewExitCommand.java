package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Exits the view mode and prompts to the original fosterer list view.
 */
public class ViewExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting view mode as requested ...";
    public static final String MESSAGE_CONFIRM_EXIT = "You did not save your changes. Are you sure you want to exit?\n" +
            "Yes: [Enter] No: [Esc]";
    public static final String MESSAGE_CONFIRM_EXIT_WITHOUT_DETAILS = "You did not fill in every detail."
    + " Are you sure you want to exit?\n" +
            "Yes: [Enter] No: [Esc]";

    private Index index;
    private Person newFosterer;

    public ViewExitCommand(Person newFosterer) {
        super();
        this.index = null;
        this.newFosterer = newFosterer;
    }

    public ViewExitCommand(Index index, Person newFosterer) {
        super();
        this.index = index;
        this.newFosterer = newFosterer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        // for exiting from empty profile page with not every field filled out
        if (index == null && newFosterer == null) {
            System.out.println("fosterer is null.");
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


        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToCompare = lastShownList.get(index.getZeroBased());
        Person editedPerson = newFosterer;

        if (editedPerson.equals(personToCompare)) {
            return new CommandResult(
                    MESSAGE_EXIT_ACKNOWLEDGEMENT,
                    newFosterer,
                    null,
                    CommandType.VIEW_EXIT,
                    true);

        } else {
            return new CommandResult(
                    MESSAGE_CONFIRM_EXIT,
                    newFosterer,
                    null,
                    CommandType.VIEW_EXIT,
                    false);
        }
    }
}
