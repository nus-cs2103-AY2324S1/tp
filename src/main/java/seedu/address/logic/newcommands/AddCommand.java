package seedu.address.logic.command;

import java.util.Date;

import seedu.address.cardslist.CardList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.newcommands.Command;
import seedu.address.model.flashcard.FlashCard;

import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;


/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book.\n"
            + "Parameters: "
            + PREFIX_ORIGINAL_WORD + "ORIGINAL WORD "
            + PREFIX_TRANSLATED_WORD + "TRANSLATION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ORIGINAL_WORD + "sorry "
            + PREFIX_TRANSLATED_WORD + "entschuldigung ";

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
