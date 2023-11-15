package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("mn/");
    public static final Prefix PREFIX_CONFLICTING_DRUGS = new Prefix("cfdg/");
    public static final Prefix PREFIX_DOSAGE = new Prefix("d/");
    public static final Prefix PREFIX_FREQUENCY = new Prefix("f/");
    public static final Prefix PREFIX_START_DATE = new Prefix("sd/");
    public static final Prefix PREFIX_END_DATE = new Prefix("ed/");
    public static final Prefix PREFIX_EXPIRY_DATE = new Prefix("exd/");
    public static final Prefix PREFIX_TOTAL_STOCK = new Prefix("ts/");
    public static final Prefix PREFIX_NOTE = new Prefix("n/");
}
