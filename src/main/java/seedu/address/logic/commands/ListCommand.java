package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.address.model.Model;
import seedu.address.model.card.Card;

import java.util.function.Predicate;

/**
 * Lists all Cards in the Deck to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists Card from Deck. "
            + "Parameters: "
            + PREFIX_QUESTION + "WORD(s) questions start with (Cannot be left blank)";

    public static final String MESSAGE_SUCCESS = "Listed all cards";

    private final Predicate<Card> predicate;

    /**
     * Creates an ListCommand to list {@code Card} containing a prefix
     */
    public ListCommand(Predicate<Card> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCardList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
