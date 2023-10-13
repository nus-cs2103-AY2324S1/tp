package seedu.flashlingo.logic.commands;

import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.WordContainsKeywordsPredicate;

/**
 * Finds and lists all flashcards in flashlingo whose original words contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards whose words contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " shark";

    private final WordContainsKeywordsPredicate predicate;

    public FindCommand(WordContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
