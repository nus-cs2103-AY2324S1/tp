package seedu.address.logic.commands.personcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.IllnessContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose illness contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindIllnessCommand extends Command {

    public static final String COMMAND_WORD = "find-illness";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients with the illness that matches "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers. Note: The"
            + " keyword must match the illness to find a valid match! \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " fever";

    private final IllnessContainsKeywordsPredicate predicate;

    public FindIllnessCommand(IllnessContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the FindIllnessCommand to filter and list persons in the model based on the specified illness keywords.
     *
     * @param model The model in which to execute the command.
     * @return A CommandResult containing the message summarising the number of persons listed.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindIllnessCommand)) {
            return false;
        }

        FindIllnessCommand otherFindIllnessCommand = (FindIllnessCommand) other;
        return predicate.equals(otherFindIllnessCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
