package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMING;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

/**
 * Command to handle adding interviews to the address book.
 * Adapted from AB3's "AddCommand" class
 */
public class AddInterviewCommand extends Command {

    public static final String COMMAND_WORD = "add-interview";

    /* TODO Update format with intended final format accepted*/
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to the address book. "
            + "Parameters: "
            + PREFIX_APPLICANT + "APPLICANT_ID "
            + PREFIX_JOB_ROLE + "ROLE "
            + PREFIX_TIMING + "TIMING"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_APPLICANT + "18 "
            + PREFIX_JOB_ROLE + "Junior Software Engineer "
            + PREFIX_TIMING + "2023-10-24 18:00";

    public static final String MESSAGE_SUCCESS = "New interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "Error: This is a duplicate interview";

    private final Interview toAdd;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public AddInterviewCommand(Interview interview) {
        requireNonNull(interview);
        toAdd = interview;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInterview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addInterview(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatInterview(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddInterviewCommand)) {
            return false;
        }

        AddInterviewCommand otherAddICommand = (AddInterviewCommand) other;
        return toAdd.equals(otherAddICommand.toAdd);
    }
}
