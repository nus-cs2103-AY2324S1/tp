package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DEVELOPERS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.Model;
import seedu.address.model.Password;

/**
 * Represents a command to unlock access to all data within the application
 * using a password. Unlocking provides access to restricted data.
 */
public class UnlockCommand extends Command {
    public static final String COMMAND_WORD = "unlock";

    public static final String MESSAGE_SUCCESS = "Unlocked all data";
    public static final String MESSAGE_FAILURE = "Incorrect Password!\n";
    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Please unlock using correct format\n"
            + "Example: " + COMMAND_WORD + " pw/Password123!";
    private String input;

    /**
     * Creates an UnlockCommand to unlock data with the given password.
     *
     * @param input The password input provided by the user.
     */
    public UnlockCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the unlock operation, which grants access to restricted data
     * when the provided password is correct.
     *
     * @param model The model containing the data to be unlocked.
     * @return A CommandResult indicating the success or failure of the unlock operation.
     */
    @Override
    public CommandResult execute(Model model) {
        assert model != null : "Model cannot be null";
        requireNonNull(model);
        if (Password.verifyPassword(input)) {
            model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
            model.updateFilteredDeveloperList(PREDICATE_SHOW_ALL_DEVELOPERS);
            model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
            AddressBookParser.unlock();
            return new CommandResult(MESSAGE_SUCCESS, TabIndex.Developer);
        }
        return new CommandResult(String.format(MESSAGE_FAILURE, Password.MESSAGE_CONSTRAINTS), TabIndex.Developer);
    }
}
