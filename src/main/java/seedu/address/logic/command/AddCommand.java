package seedu.address.logic.command;


import seedu.address.cardslist.CardList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.command.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.pojo.FlashCard;

import java.util.Date;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book.";

    public static final String MESSAGE_SUCCESS = "New flashcard added: %s - %s";
    public static final String MESSAGE_DUPLICATE_CARD = "This flashcard already exists";

    private final FlashCard toAdd;
    private CardList cardList;
    private String original;
    private String translated;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(String original, String translated, CardList cardList) {
        this.cardList = cardList;
        this.original = original;
        this.translated = translated;
        this.toAdd = new FlashCard(original, translated, new Date(), 0);
    }

    @Override
    public CommandResult execute() throws CommandException {
        if (cardList.hasCard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.original, this.translated));
    }
}
