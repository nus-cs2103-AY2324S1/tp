package swe.context.logic.parser;



/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple
 * commands.
 */
public class CliSyntax {
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_NOTE = new Prefix("o/");
    public static final Prefix PREFIX_ALTERNATE = new Prefix("a/");
}
