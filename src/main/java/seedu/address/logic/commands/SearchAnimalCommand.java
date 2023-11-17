package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliAnimalSyntax.BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PET_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.SPECIES;

import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.KeywordPredicate;

/**
 * Searches for animals in the catalog based on keyword given (case-insensitive).
 */
public class SearchAnimalCommand extends AnimalCommand {
    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches for animals in the catalog based on keyword given (case insensitive). "
            + "Parameters: "
            + NAME.getUsage() + " "
            + PET_ID.getUsage() + " "
            + DATE_OF_BIRTH.getUsage() + " "
            + DATE_OF_ADMISSION.getUsage() + " "
            + SPECIES.getUsage() + " "
            + SEX.getUsage() + " "
            + BREED.getUsage();

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD + " "
            + NAME.getPrefix() + "Pookie "
            + PET_ID.getPrefix() + "1234 "
            + SEX.getPrefix() + "MALE "
            + DATE_OF_BIRTH.getPrefix() + "2019-01-01 "
            + DATE_OF_ADMISSION.getPrefix() + "2019-01-01 "
            + SPECIES.getPrefix() + "Dog "
            + BREED.getPrefix() + "Poodle";

    public static final String MESSAGE_SUCCESS = "%1$s result(s) found.";
    private final KeywordPredicate predicate;

    /**
     * Creates a SearchAnimalCommand to search for animals in the catalog based on keyword given (case-insensitive).
     */
    public SearchAnimalCommand(KeywordPredicate predicate) {
        requireAllNonNull(predicate);

        this.predicate = predicate;
    }

    /**
     * Returns the help message of this command.
     *
     * @return a string containing the help message of this command.
     */
    public static String getHelp() {
        return String.format(AnimalMessages.MESSAGE_HELP_FORMAT, MESSAGE_USAGE, EXAMPLE_USAGE);
    }

    @Override
    public CommandResult execute(AnimalModel model) throws CommandException {
        model.updateFilteredAnimalList(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredAnimalList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchAnimalCommand // instanceof handles nulls
                && predicate.equals(((SearchAnimalCommand) other).predicate)); // state check
    }
}
