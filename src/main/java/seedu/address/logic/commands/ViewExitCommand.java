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

    private Index index;
    private Person newFosterer;

    public ViewExitCommand() {
        super();
        this.index = null;
        this.newFosterer = null;
    }

    public ViewExitCommand(Index index, Person newFosterer) {
        super();
        this.index = index;
        this.newFosterer = newFosterer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        boolean isFostererEdited = false;

        // for adding from empty profile page
        if (index == null && !lastShownList.contains(newFosterer)) {
            return new CommandResult(
                    MESSAGE_EXIT_ACKNOWLEDGEMENT,
                    newFosterer,
                    null,
                    CommandType.VIEW_EXIT,
                    false);
        }

//        //
//        if (index == null && lastShownList.contains(newFosterer)) {
//            return new CommandResult(
//                    MESSAGE_EXIT_ACKNOWLEDGEMENT,
//                    newFosterer,
//                    null,
//                    true,
//                    null,
//                    false);
//        }


        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToCompare = lastShownList.get(index.getZeroBased());
        Person editedPerson = newFosterer;

        if (editedPerson.equals(personToCompare)) {
            isFostererEdited = true;
        }

        return new CommandResult(
                MESSAGE_EXIT_ACKNOWLEDGEMENT,
                newFosterer,
                null,
                CommandType.VIEW_EXIT,
                isFostererEdited);
    }
}
