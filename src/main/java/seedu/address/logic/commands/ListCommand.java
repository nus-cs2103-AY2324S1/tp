package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Objects;

import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static String MESSAGE_SUCCESS = "Showing list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the specified list, which can be a "
            + "student list, schedule list, task list. Default command without "
            + "keywords displays the student list.\n"
            + "Parameters: [KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " tasks";

    private final String panel;
    private final String[] displayParams;

    public ListCommand(String panel, String[] displayParams) {
        this.panel = panel;
        this.displayParams = displayParams;
    }

    public ListCommand() {
        this("", new String[0]);
    }

    public ListCommand(String panel) {
        this(panel, new String[0]);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (panel.equals("")) {
            return new CommandResult(MESSAGE_SUCCESS, displayParams);
        } else {
            return new CommandResult(MESSAGE_SUCCESS, panel, displayParams);
        }
    }
}
