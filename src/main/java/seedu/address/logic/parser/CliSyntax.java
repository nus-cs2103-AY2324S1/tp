package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_WILDCARD = new Prefix("/");
    public static final Prefix PREFIX_STUDENT_NUMBER = new Prefix("s/");
    public static final Prefix PREFIX_CLASS_NUMBER = new Prefix("c/");
    public static final Prefix PREFIX_FILE = new Prefix("f/");
    public static final Prefix PREFIX_GRADE = new Prefix("g/");
    public static final Prefix PREFIX_ASSIGNMENT = new Prefix("a/");
}
