package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TeachingModPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all teaching assistants whose names contain "
            + "any "
            + "of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

//    private final Predicate<Person> predicate;
    private final ArrayList<Predicate<Person>> predicateList = new ArrayList<>();

//    public FindCommand(NameContainsKeywordsPredicate predicate) {
//        this.predicate = predicate;
//    }
//
//    public FindCommand(TeachingModPredicate predicate) {
//        this.predicate = predicate;
//    }

    public FindCommand(ArrayList<Predicate<Person>> predicates) {
        this.predicateList.addAll(predicates);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> combinedPredicate = predicateList.stream().reduce(x -> true, Predicate::and);
        model.updateFilteredPersonList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicateList.equals(otherFindCommand.predicateList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicateList)
                .toString();
    }
}
