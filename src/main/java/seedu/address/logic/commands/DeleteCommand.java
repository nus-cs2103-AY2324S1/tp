package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a person."
            + "Parameters: "
            + PREFIX_STUDENT_NUMBER + "STUDENT NUMBER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_NUMBER + "A0245234A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final StudentNumber targetStudentNumber;

    public DeleteCommand(StudentNumber targetStudentNumber) {
        this.targetStudentNumber = targetStudentNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(new Person(targetStudentNumber))) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
        }

        Person personToDelete = model.getPerson(targetStudentNumber);

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetStudentNumber.equals(otherDeleteCommand.targetStudentNumber);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetStudentNumber", targetStudentNumber)
                .toString();
    }
}
