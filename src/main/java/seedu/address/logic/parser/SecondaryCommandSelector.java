package seedu.address.logic.parser;

/**
 * Helper methods for parsing secondary commands
 */
public abstract class SecondaryCommandSelector {

    /**
     * Get the secondary command word. For example, the command word {@code event} in the comman {@code add event ...}
     * @param str The command arguments after the primary command word
     * @return The secondary command word
     */
    public static String getSecondaryCommandWord(String str) {
        return str.split(" ")[1];
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
