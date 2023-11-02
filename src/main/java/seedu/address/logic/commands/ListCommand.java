package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.card.Card;

/**
 * Lists all Cards in the Deck to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists Card from Deck. "
            + "Parameters: "
            + PREFIX_QUESTION + "WORD(s) questions start with (Cannot be left blank)";

    public static final String MESSAGE_SUCCESS = "Listed all cards";

    private final List<Predicate<Card>> predicates;

    /**
     * Creates an ListCommand to list {@code Card} containing a prefix
     */
    public ListCommand(List<Predicate<Card>> predicates) {
        requireNonNull(predicates);
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Card> predicate = predicates.stream().reduce((card -> true), (Predicate::and));
        model.updateFilteredCardList(predicate);
        model.resetRandomIndex();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
