package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. \n"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_PHONE + "PHONE "
        + PREFIX_EMAIL + "EMAIL "
        + PREFIX_GROUPTAG + "GROUPNAME \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_EMAIL + "johnd@example.com "
        + PREFIX_GROUPTAG + "CS2103T";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "%1$s is already in the contact list";
    public static final String MESSAGE_DUPLICATE_EMAIL = "This email: %1$s already belongs to some "
        + "one in the contact list";
    public static final String MESSAGE_DUPLICATE_PHONE = "This phone number: %1$s belongs to some one "
        + "in the contact list";

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
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, toAdd.getName().toString()));
        }

        if (model.hasEmail(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EMAIL, toAdd.getEmail().toString()));
        }

        if (model.hasPhone(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PHONE, toAdd.getPhone().toString()));
        }

        model.addPerson(toAdd);
        toAdd.getGroups().toStream().findFirst().ifPresent(group -> {
            if (!model.hasGroup(group)) {
                model.addGroup(group);
            }
            try {
                model.findGroup(group.getGroupName()).addPerson(toAdd);
            } catch (CommandException e) {
                throw new RuntimeException(e);
            }
        });
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
