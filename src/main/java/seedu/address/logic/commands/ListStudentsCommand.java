package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListStudentsCommand extends ListCommand {

    public static final String COMMAND_WORD = "students";
    public static final String MESSAGE_USAGE = "list " + COMMAND_WORD
            + ": Lists all students.\n"
            + "Example: list " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Listed all students from %s.";

    @Override
    public boolean equals(Object other) {
        return other instanceof ListStudentsCommand;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearFilters();
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getAddressBook().getCourseCode()));
    }
}
