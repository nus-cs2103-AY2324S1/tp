package seedu.address.logic.commands.musician;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.musician.Musician;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all musicians whose names, tags, "
            + "instruments, and genres contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: At least one of the following prefixes with at least one argument must be provided: "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_INSTRUMENT + "INSTRUMENT]..."
            + "[" + PREFIX_GENRE + "GENRE]...\n"
            + "Example: " + COMMAND_WORD + " n/Alice i/piano";

    private final HashSet<Predicate<Musician>> predicates;

    /**
     * Creates a FindCommand to find the specified {@code Musician}.
     *
     * @param predicates the list of predicates to filter the list of musicians.
     */
    public FindCommand(HashSet<Predicate<Musician>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // predicates is guaranteed to have at least one element because we cannot pass
        // empty arguments to the FindCommand. Hence, we can safely pass the identity as x -> true
        // since it will always be overridden by the first predicate.
        Predicate<Musician> combinedPredicate = predicates.stream().reduce(x -> true, Predicate::and);
        model.updateFilteredMusicianList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MUSICIANS_LISTED_OVERVIEW, model.getFilteredMusicianList().size()));
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
        return predicates.equals(otherFindCommand.predicates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicates)
                .toString();
    }
}
