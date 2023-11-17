package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AnimalModel.PREDICATE_SHOW_ALL_ANIMALS;

import seedu.address.logic.AnimalMessages;
import seedu.address.model.AnimalModel;

/**
 * Lists all animals in the catalog to the user.
 */
public class ListAnimalCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all animals in the Animal Catalog, with a "
        + "unique index that can be referenced by commands like 'delete' and 'edit'.\n"
        + "Parameters: any extraneous parameters to 'list' will be ignored.";

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all animals";

    /**
     * Returns the help message of this command.
     *
     * @return a string containing the help message of this command.
     */
    public static String getHelp() {
        return String.format(AnimalMessages.MESSAGE_HELP_FORMAT, MESSAGE_USAGE, EXAMPLE_USAGE);
    }

    @Override
    public CommandResult execute(AnimalModel model) {
        requireNonNull(model);
        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
