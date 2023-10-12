package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Helper methods for parsing secondary commands
 */
public abstract class SecondaryCommandSelector {

    /**
     * Get the secondary command word. For example, the command word {@code event} in the comman {@code add event ...}
     * @param str The command arguments after the primary command word
     * @return The secondary command word
     */
    public static String getSecondaryCommandWord(String str) throws ParseException {
        try {
            return str.split(" ")[1];
        } catch (Exception e) {
            throw new ParseException("Can not get secondary command");
        }

    }

    /**
     * Get the arguments after secondary command word
     * param str The command arguments after the primary command word
     * @return The arguments after secondary command word
     */
    public static String getArguments(String secondaryCommandWord, String str) {
        return str.replaceFirst(secondaryCommandWord, "");
    }
}
