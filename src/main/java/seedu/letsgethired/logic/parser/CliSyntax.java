package seedu.letsgethired.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_COMPANY = new Prefix("n/");
    public static final Prefix PREFIX_ROLE = new Prefix("r/");
    public static final Prefix PREFIX_CYCLE = new Prefix("c/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_NOTE_INSERT = new Prefix("i/");
    public static final Prefix PREFIX_NOTE_DELETE = new Prefix("o/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
}
