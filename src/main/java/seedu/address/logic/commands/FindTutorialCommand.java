package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.TutorialContainsKeywordsPredicate;

/**
 * Command that filters out the people based on their tutorial
 */
public class FindTutorialCommand extends Command {
    /**
     * Keyword to trigger the searchtutorial command.
     */
    public static final String COMMAND_WORD = "searchtutorial";

    /**
     * Usage instructions for the 'searchtutorial' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tutorials contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2100/G07";

    private final TutorialContainsKeywordsPredicate predicate;

    /**
     * Constructs a FindTutorialCommand with a specific keyword predicate.
     *
     * @param predicate The predicate used to filter persons by tutorial.
     */
    public FindTutorialCommand(TutorialContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the FindTutorialCommand to filter and list persons whose tutorials contain the specified keywords.
     *
     * @param model The model that this command should operate on.
     * @return The CommandResult containing a summary of the filtered persons.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Checks equality of the FindTutorialCommand with another object.
     *
     * @param other The object to compare for equality.
     * @return True if the objects are equal, otherwise returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindTutorialCommand)) {
            return false;
        }

        FindTutorialCommand otherFindCommand = (FindTutorialCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    /**
     * Returns a string representation of the FindTutorialCommand.
     *
     * @return A string representing the FindTutorialCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
