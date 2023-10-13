package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;

import java.util.Date;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.OriginalWord;
import seedu.flashlingo.model.flashcard.Translation;

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
    private OriginalWord original;
    private Translation translated;

    /**
     * Creates an AddCommand to add the specified {@code FlashCard}
     */
    public AddCommand(OriginalWord original, Translation translated) {
        this.original = original;
        this.translated = translated;
        this.toAdd = new FlashCard(original, translated, new Date(), 1);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFlashCard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.original, this.translated));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
