package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GPA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUS_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a applicant to the applicant list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an applicant to the list. "
            // Parameters
            + "Parameters: "
            + PREFIX_STUDENT_NUMBER + "STUDENT NUMBER "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GPA + "GPA "
            + PREFIX_PREVIOUS_GRADE + "PREV GRADE "
            + "[" + PREFIX_INTERVIEW_SCORE + "INTERVIEW SCORE] " // optional
            + "[" + PREFIX_COMMENT + "COMMENT] " // optional
            + "[" + PREFIX_INTERVIEW_SCORE + "INTERVIEW SCORE] " // optional
            + "[" + PREFIX_TAG + "TAG]...\n" // optional
            // Example
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_NUMBER + "A0343434C "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_GPA + "4.9 "
            + PREFIX_PREVIOUS_GRADE + "A "
            + PREFIX_INTERVIEW_SCORE + "9.1 "
            + PREFIX_COMMENT + "Hardworking and diligent "
            + PREFIX_TAG + "pastTA ";

    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This applicant already exists in the applicant list.";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
