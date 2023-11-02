package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.card.Card;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CARDS;

/**
 * Lists all Cards in the Deck to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists Card from Deck. "
            + "Parameters: \n"
            + "and/or " + PREFIX_QUESTION + "WORD(s) questions start with (Cannot be left blank. Markdown syntax" +
            " should be included.)\n"
            + "and/or " + PREFIX_TAG + "Tag (Cannot be left blank. Multiple tags can be included.)";

    public static final String MESSAGE_SUCCESS = "Listed all cards";

    /** A list of {@code predicates} to filter the {@code Deck} with */
    private final List<Predicate<Card>> predicates;

    /**
     * Creates a ListCommand to filter for {@code Card} fulfilling the specified {@code Predicate}
     */
    public ListCommand(List<Predicate<Card>> predicates) {
        requireNonNull(predicates);
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Default filter is to show all cards
        // All other filters specified by user are reduced to one single filter using the boolean AND
        // The final list of Cards in the Deck must fulfill all predicates specified
        Predicate<Card> predicate = predicates.stream().reduce(PREDICATE_SHOW_ALL_CARDS, Predicate::and);

        model.updateFilteredCardList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
