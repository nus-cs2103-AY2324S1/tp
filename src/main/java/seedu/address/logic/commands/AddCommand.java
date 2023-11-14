package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_MEDIA_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to StudentConnect.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to StudentConnect. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MAJOR + "MAJOR "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TUTORIAL + "TUTORIAL]... "
            + "[" + PREFIX_SOCIAL_MEDIA_LINK + "SOCIAL_MEDIA_LINK]... "
            + PREFIX_NATIONALITY + "NATIONALITY "
            + PREFIX_GENDER + "GENDER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_MAJOR + "Computer Science "
            + PREFIX_YEAR + "2 "
            + PREFIX_EMAIL + "johnd@u.nus.edu "
            + PREFIX_DESCRIPTION + "I love programming in my free time "
            + PREFIX_TUTORIAL + "02 "
            + PREFIX_SOCIAL_MEDIA_LINK + "https://www.linkedin.com/in/john-doe-123456789 "
            + PREFIX_NATIONALITY + "local "
            + PREFIX_GENDER + "M";

    public static final String MESSAGE_SUCCESS = "Details added successfully! New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student is already on StudentConnect as this "
            + "email has already been used.";

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
