package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

/**
 * Represents a default command keyword.
 * Guarantees: immutable; is valid as declared in {@link #isValidCommandWord(String)}
 */
public class CommandWord {

    public static final String MESSAGE_CONSTRAINTS =
            "Command word has to be a valid default command word!\n"
                    + "Input 'help' to view our UserGuide and a list of commands available.";

    private static final String[] VALID_COMMAND_WORDS = {
        AddCommand.COMMAND_WORD, AddShortcutCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD,
        DeleteCommand.COMMAND_WORD, DeleteShortcutCommand.COMMAND_WORD, EditCommand.COMMAND_WORD,
        ExitCommand.COMMAND_WORD, FindCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD,
        ListCommand.COMMAND_WORD, UndoCommand.COMMAND_WORD, RedoCommand.COMMAND_WORD,
        ThemeCommand.COMMAND_WORD, ViewCommand.COMMAND_WORD
    };

    public final String keyword;

    /**
     * Constructs a {@code CommandWord}.
     *
     * @param word A valid command keyword.
     */
    public CommandWord(String word) {
        requireNonNull(word);
        keyword = word;
    }

    /**
     * Returns true if a given string is a valid command word.
     */
    public static boolean isValidCommandWord(String test) {
        return Arrays.asList(VALID_COMMAND_WORDS).contains(test);
    }

    @Override
    public String toString() {
        return keyword;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandWord)) {
            return false;
        }

        CommandWord otherName = (CommandWord) other;
        return keyword.equals(otherName.keyword);
    }

    @Override
    public int hashCode() {
        return keyword.hashCode();
    }
}
