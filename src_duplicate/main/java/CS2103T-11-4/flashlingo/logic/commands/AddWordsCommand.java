package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddWordsCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a word to the app. "
      + "Parameters: "
      + PREFIX_WORD + "WORD "
      + PREFIX_TRANSLATION + "TRANSLATION "
      + "Example: " + COMMAND_WORD + " "
      + PREFIX_WORD + "shark "
      + PREFIX_TRANSLATION + "鲨鱼 ";

    public static final String MESSAGE_SUCCESS = "New word added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This word already exists in the dictionary";

    private final FlashCard toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddWordsCommand(FlashCard card) {
        requireNonNull(card);
        toAdd = card;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasWord(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addWord(toAdd);
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
