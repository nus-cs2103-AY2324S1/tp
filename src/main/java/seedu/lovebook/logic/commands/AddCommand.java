package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.person.Date;

/**
 * Adds a date to the lovebook book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a date to the LoveBook. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_AGE + "AGE "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_HEIGHT + "HEIGHT "
            + PREFIX_INCOME + "INCOME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_AGE + "21 "
            + PREFIX_GENDER + "M "
            + PREFIX_HEIGHT + "23124 "
            + PREFIX_INCOME + "3000 ";

    public static final String MESSAGE_SUCCESS = "New date added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This date already exists in the love book";

    private final Date toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Date}
     */
    public AddCommand(Date date) {
        requireNonNull(date);
        toAdd = date;
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
