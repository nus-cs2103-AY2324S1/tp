package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Group;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class GroupCommand extends Command {

	public static final String COMMAND_WORD = "group";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to a group in the address book. "
			+ "Parameters: "
			+ PREFIX_NAME + "NAME "
			+ PREFIX_GROUPTAG + "GROUP ";

	public static final String MESSAGE_SUCCESS = "%1$s is now a part of %2$s";
	public static final String MESSAGE_DUPLICATE_PERSON_IN_GROUP = "Error, invalid input entered, unable to put the person into group";

	private final Person person;

	private final Group group;

	/**
	 * Creates an AddCommand to add the specified {@code Person}
	 */
	public GroupCommand(Person person, Group group) {
		requireNonNull(person);
		requireNonNull(group);
		this.person = person;
		this.group = group;

	}

	@Override
	public CommandResult execute(Model model) throws CommandException {
		requireNonNull(model);

		if(model.hasGroup(group)) {
			throw new CommandException(MESSAGE_DUPLICATE_PERSON_IN_GROUP);
		}

		if (model.hasPerson(this.person)) {
			throw new CommandException(MESSAGE_DUPLICATE_PERSON_IN_GROUP);
		}

		model.addPerson(this.person);
//		return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
		return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(person), Messages.format(group)));
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		// instanceof handles nulls
		if (!(other instanceof GroupCommand)) {
			return false;
		}

		GroupCommand otherAddCommand = (GroupCommand) other;
		return person.equals(otherAddCommand.person);

	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.add("toAddToGroup", person)
				.toString();
	}
}

