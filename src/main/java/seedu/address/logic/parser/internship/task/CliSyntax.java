package seedu.address.logic.parser.internship.task;

import seedu.address.logic.parser.common.Prefix;

/**
 * Syntax for creating as InternshipTask
 */
public class CliSyntax {
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_TASK_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("e/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_OUTCOME = new Prefix("o/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
}
