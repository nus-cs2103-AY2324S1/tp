package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_TIME;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.tutorial.Tutorial;

/**
 * Adds a tutorial to the address book.
 */
public class AddTutorialCommand extends Command {
    public static final String COMMAND_WORD = "addTutorial";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial to the list\n"
            + "Parameters: "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_TUTORIAL_NAME + "TUTORIAL_NAME "
            + PREFIX_TUTORIAL_TIME + "TUTORIAL_TIME\n"
            + "Example: " + COMMAND_WORD + " m/CS1000 tn/T12 tt/Mon 6PM";

    public static final String MESSAGE_SUCCESS = "Added tutorial: %s";

    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This tutorial already exists in the address book";

    private final Tutorial toAdd;

    /**
     * Creates an AddTutorialCommand to add the specified {@code Tutorial}
     */
    public AddTutorialCommand(Tutorial tutorial) {
        requireNonNull(tutorial);
        toAdd = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        /* Enforces the constraint that a tutorial must be linked to a (valid) module. */
        if (!model.hasModule(new Module(toAdd.getModuleCode()))) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE);
        }

        /* Enforces the constraint that there cannot be two tutorials with the same name. Requires the equality of
        tutorials to depend on tutorial name. */
        if (model.hasTutorial(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        model.addTutorial(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTutorialCommand)) {
            return false;
        }

        AddTutorialCommand otherAddTutorialCommand = (AddTutorialCommand) other;
        return toAdd.equals(otherAddTutorialCommand.toAdd);
    }
}
