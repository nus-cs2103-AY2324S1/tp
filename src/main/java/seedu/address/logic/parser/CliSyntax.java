package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_ANSWER = new Prefix("a/");
    public static final Prefix PREFIX_QUESTION = new Prefix("q/");
    public static final Prefix PREFIX_DIFFICULTY = new Prefix("d/");
    public static final Prefix PREFIX_HINT = new Prefix("h/");
    public static final Prefix PREFIX_INVALID = new Prefix("i/");
}
