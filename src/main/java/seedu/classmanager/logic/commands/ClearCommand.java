//@@author Cikguseven-reused
//Refactored and modified from AddressBook-Level 3 (https://github.com/se-edu/addressbook-level3)
// Not supposed to own code in file.
package seedu.classmanager.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.classmanager.logic.CommandHistory;
import seedu.classmanager.model.ClassManager;
import seedu.classmanager.model.Model;

/**
 * Clears Class Manager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Class Manager has been cleared!";

    /**
     * Clears all students from {@code ClassManager}.
     * @param model {@code Model} which the command should operate on.
     * @param commandHistory The command history to record this command.
     * @return A {@code CommandResult} with the feedback message of the operation result.
     */
    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        requireNonNull(model);
        model.setClassManager(new ClassManager());
        model.resetSelectedStudent();
        model.commitClassManager();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
//@@author
