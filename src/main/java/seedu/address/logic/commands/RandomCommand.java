package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Random;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Card;

/**
 * Randomly Practises a question.
 */
public class RandomCommand extends Command {

    public static final String COMMAND_WORD = "random";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": similar to practice command, but chooses a random index.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Constructs a {@code RandomCommand}.
     */
    public RandomCommand() {}

    /**
     * Generates a random index based on the size of the deck in the model. This index value is one based.
     */
    private int generateRandomIndex(List<Card> lastShownList) {
        Random r = new Random();
        return r.nextInt(lastShownList.size()) + 1;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Card> lastShownList = model.getFilteredCardList();

        // generate a targetIndex from the card list length
        Index targetIndex = Index.fromOneBased(generateRandomIndex(lastShownList));

        // save the random index in the model
        model.setRandomIndex(targetIndex);

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        Card cardToPractise = lastShownList.get(targetIndex.getZeroBased());

        // same as practise
        return new CommandResult(
                String.format(Messages.MESSAGE_CARDS_PRACTISE_VIEW,
                        Messages.formatPractise(cardToPractise, targetIndex)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RandomCommand) || other == null) {
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
